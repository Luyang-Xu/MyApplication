package com.example.luyang.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by luyang on 2017/10/10.
 */

public class Login extends BaseActivity {
    private EditText username;

    private EditText password;

    private CheckBox remember;

    private SharedPreferences pref;

    private SharedPreferences.Editor ed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_layout);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        ed = pref.edit();
        remember = findViewById(R.id.rememberPass);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        boolean isremember = pref.getBoolean("isremember", false);
        if (isremember) {
            username.setText(pref.getString("username", ""));
            password.setText(pref.getString("password", ""));
            remember.setChecked(true);
        }

        Button login = findViewById(R.id.loginconfirm);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = username.getText().toString();
                String pw = password.getText().toString();

                if (remember.isChecked()) {
                    ed.putBoolean("isremember", true);
                    ed.putString("username", un);
                    ed.putString("password", pw);
                    ed.commit();
                } else {
                    ed.clear();
                }
                if (un.equals("admin") && pw.equals("123456")) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "wrong name or pwd", Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                }
            }
        });

    }
}
