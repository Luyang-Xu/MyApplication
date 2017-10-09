package com.example.luyang.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luyang.myapplication.BaseActivity;

/**
 * Created by luyang on 2017/9/29.
 */

public class ListViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listview_layout);
//        savedInstanceState=getIntent().getExtras();
//        String[] fruit=savedInstanceState.getStringArray("fruitdata");
        Intent intent = getIntent();
        String[] fruit = intent.getStringArrayExtra("fruitdata");
        ListView lv = findViewById(R.id.listview);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(ListViewActivity.this, android.R.layout.simple_expandable_list_item_1, fruit);
        lv.setAdapter(aa);
    }
}

