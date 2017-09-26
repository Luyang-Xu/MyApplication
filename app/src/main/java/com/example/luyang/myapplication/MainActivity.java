package com.example.luyang.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("main_activity",this.toString());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                显示声明intent
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                //隐式声明intent
//                Intent intent=new Intent("com.example.luyang.ActionStart");
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivityForResult(intent,1);
            }
        });
    }
//活动间的数据回传
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Log.d("second_activity",data.getStringExtra("second_activity"));
                }
                break;
            default:
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
