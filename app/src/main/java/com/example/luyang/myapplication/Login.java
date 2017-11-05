package com.example.luyang.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by luyang on 2017/10/10.
 */

public class Login extends BaseActivity {
    private EditText username;

    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_layout);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        Button login = findViewById(R.id.loginconfirm);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un=username.getText().toString();
                String pw=password.getText().toString();
                if(un.equals("admin") && pw.equals("123456")){
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Login.this,"wrong name or pwd",Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                }
            }
        });

    }
}
