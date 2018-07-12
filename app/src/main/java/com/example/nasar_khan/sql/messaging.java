package com.example.nasar_khan.sql;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class messaging extends AppCompatActivity {
    EditText phone;
    EditText message;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        phone=(EditText) findViewById(R.id.p);
        message=(EditText) findViewById(R.id.b);
        button=(Button) findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
//                    Intent i=new Intent(Intent.ACTION_SEND);
//                    i.putExtra(Intent.EXTRA_PHONE_NUMBER,phone.getText().toString());
//
//                    i.putExtra(Intent.EXTRA_TEXT,message.getText().toString());
//                    i.setType("text/plain");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("smsto:"));
                    i.setType("vnd.android-dir/mms-sms");
                    i.putExtra("address"  , new String (phone.getText().toString()));
                    i.putExtra("sms_body"  , message.getText().toString());

                    try{
                         startActivity(i);
                    }catch (Exception e)
                    {
                        Toast.makeText(messaging.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                   Toast.makeText(messaging.this,e.toString(),Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
