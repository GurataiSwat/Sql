package com.example.nasar_khan.sql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class browsercategory extends AppCompatActivity {
    Button cow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsercategory);
        cow= (Button) findViewById(R.id.cw);
        cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , browsecow.class);
                startActivity(intent);
            }
        });
    }
}
