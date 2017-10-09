package com.example.luyang.myapplication.layout;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.luyang.myapplication.R;

/**
 * Created by luyang on 2017/9/27.
 */

public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button titleback=(Button)findViewById(R.id.back);
        Button titleedit=(Button)findViewById(R.id.edit);

        titleback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

        titleedit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"this is edit button",Toast.LENGTH_LONG).show();
            }
        });

    }
}
