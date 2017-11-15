package com.example.luyang.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luyang.myapplication.dbutil.MyDatabaseHelper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by luyang on 2017/9/23.
 */

public class SecondActivity extends BaseActivity {

    LocalBroadcastManager localmanager;

    LocalReceiver lr;

    MyDatabaseHelper myHelper;

    SQLiteDatabase db;

    private Button add;
    private Button delete;
    private Button update;
    private Button query;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);

        localmanager = LocalBroadcastManager.getInstance(this);

        myHelper = new MyDatabaseHelper(SecondActivity.this, "bookstore.db", null, 2);

        Intent intent = getIntent();
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(passIntent);
            }
        });


        Button localbutton = findViewById(R.id.localbroadcastbutton);
        localbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("mmpshit");
                localmanager.sendBroadcast(intent);
            }
        });


        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction("mmpshit");
        lr = new LocalReceiver();
        localmanager.registerReceiver(lr, ifilter);

        //文本框内容存文件
        Button savebutton = findViewById(R.id.savetofile);
        EditText et = findViewById(R.id.textToFile);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveToFile(et.getText().toString());
                    File f = getFilesDir();
                    Log.e("PATH", f.getAbsolutePath());
                    Log.e("Path", "hello world");
                } catch (FileNotFoundException e) {
                    Log.e("Path", "hello world");
                    e.printStackTrace();
                }
            }
        });

        //读文件
        Button readbutton = findViewById(R.id.readfile);
        readbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String content = readFile();
                    Toast.makeText(SecondActivity.this, content, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button writesharedbutton = findViewById(R.id.writeSharedPreference);
        writesharedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeSharedPreference("hello");
            }
        });

        Button readsharedbutton = findViewById(R.id.readSharedPreference);
        readsharedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSharedPreference();
            }
        });

        //建立数据库

        Button sqllitebutton = findViewById(R.id.writesqllite);
        sqllitebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * create databae
                 */
                myHelper.getWritableDatabase();
            }
        });

        //the CRUD of database

        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        query = findViewById(R.id.query);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = myHelper.getWritableDatabase();
                db.execSQL("insert into Book values(null,?,?,?,?) ", new String[]{"xuluyang", "23.33", "400", "hello world"});
                db.execSQL("insert into Book values(null,?,?,?,?) ", new String[]{"yulinye", "51.51", "500", "new world"});
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = myHelper.getWritableDatabase();
                db.execSQL("delete from Book where pages>?", new String[]{"420"});
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=myHelper.getWritableDatabase();
                db.execSQL("update Book set price=? where author=?",new String[]{"100.0","xuluyang"});
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=myHelper.getWritableDatabase();

                Cursor cur=db.rawQuery("select * from Book",null);
                if(cur.moveToFirst()){
                    do{
                        String author=cur.getString(cur.getColumnIndex("author"));
                        int pages=cur.getInt(cur.getColumnIndex("pages"));
                        Log.e("paged",String.valueOf(pages));
                        Log.e("author",author);
                    }while(cur.moveToNext());
                }
                cur.close();

            }
        });

    }

    /**
     * write into sharedPreference
     *
     * @param content
     */
    public void writeSharedPreference(String content) {
        SharedPreferences.Editor ed = getSharedPreferences("shareddata", 0).edit();
        ed.putString("name", "xly");
        ed.putInt("age", 18);
        ed.commit();
    }

    public void readSharedPreference() {
        SharedPreferences pre = getSharedPreferences("shareddata", 0);
        int age = pre.getInt("age", 0);
        String name = pre.getString("name", "");
        Toast.makeText(SecondActivity.this, name + ":" + age, Toast.LENGTH_LONG).show();

    }


    /**
     * 储文件函数默认存在于data/data下，在DDMS下打开
     *
     * @param content
     * @throws FileNotFoundException
     */
    public void saveToFile(String content) throws FileNotFoundException {
        FileOutputStream fos = openFileOutput("data.txt", MODE_APPEND);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        try {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //读data/data文件夹下面的文件数据
    public String readFile() throws IOException {
        FileInputStream fis = openFileInput("data.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("second_activity", "hello i am from second activity data");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的广播接收器需要手动注销
        localmanager.unregisterReceiver(lr);
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Second Receiver", Toast.LENGTH_LONG).show();
        }
    }
}
