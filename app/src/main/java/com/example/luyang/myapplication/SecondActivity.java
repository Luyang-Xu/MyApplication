package com.example.luyang.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by luyang on 2017/9/23.
 */

public class SecondActivity extends BaseActivity {

    LocalBroadcastManager localmanager;

    LocalReceiver lr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);

        localmanager=LocalBroadcastManager.getInstance(this);

        Intent intent = getIntent();
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(passIntent);
            }
        });


        Button localbutton=findViewById(R.id.localbroadcastbutton);
        localbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("mmpshit");
                localmanager.sendBroadcast(intent);
            }
        });


        IntentFilter ifilter=new IntentFilter();
        ifilter.addAction("mmpshit");
        lr=new LocalReceiver();
        localmanager.registerReceiver(lr,ifilter);

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
        localmanager.unregisterReceiver(lr);
    }

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"Second Receiver",Toast.LENGTH_LONG).show();
        }
    }
}
