package com.example.luyang.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luyang.myapplication.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyang on 2017/11/7.
 */

public class Contacts extends BaseActivity {

    ArrayAdapter<String> adpt;

    List<String> list=new ArrayList<String>();

    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.contacts_layout);

        lv = findViewById(R.id.contactview);
        adpt = new ArrayAdapter<String>(Contacts.this, R.layout.contacts_layout, list);
        lv.setAdapter(adpt);
        readContacts();

    }


    private void readContacts() {
        Cursor cur = null;
        try {
            cur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cur != null) {
                cur.moveToFirst();
                while (cur.moveToNext()) {
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list.add(name + ":" + number);
                    Log.e("info",name+":"+number);
                    Toast.makeText(Contacts.this,name+":"+number,Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(cur!=null){
                cur.close();
            }
        }
    }
}
