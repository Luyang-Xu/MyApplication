package com.example.luyang.myapplication.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by luyang on 2017/10/9.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"receive in mybroadcastReceiver",Toast.LENGTH_LONG).show();
    }
}
