package com.example.luyang.myapplication;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private NetworkChangeReceiver networkreceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("main_activity", this.toString());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                显示声明intent
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                //隐式声明intent
//                Intent intent=new Intent("com.example.luyang.ActionStart");
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivityForResult(intent,1);
            }
        });

        Button listview_button = findViewById(R.id.button_list_view);
        listview_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                String[] fruit = {"apple", "banana", "orange", "grape", "pineapple", "cherry", "blueberry", "pear", "watermelon", "strawberry", "lemon"};
                intent.putExtra("fruitdata", fruit);
                //intent.putExtras(savedInstanceState);
                startActivity(intent);
            }
        });

        Button broadcastbutton=findViewById(R.id.broadcastbutton);
        broadcastbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("mmp");
                //intent.setAction("mmp");
                sendBroadcast(intent);
            }
        });


        //广播接收器注册
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkreceiver = new NetworkChangeReceiver();
        registerReceiver(networkreceiver, intentfilter);


        Button loginbutton=findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }

    //活动间的数据回传
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Log.d("second_activity", data.getStringExtra("second_activity"));
                }
                break;
            default:
        }
    }

    //动态注册的广播接收器一定要动态结束
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkreceiver);
    }

    //内部类继承BroadcastReceiver，接收系统广播,广播接收器
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "network changed", Toast.LENGTH_LONG).show();
            ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_LONG).show();
            }
        }
    }

    //控制menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                //逻辑操作
                Toast.makeText(this, "must add show() method", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                //逻辑操作
                Toast.makeText(this, "REMOVE you clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}
