package com.example.nasar_khan.sql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select extends AppCompatActivity {
    Button btnn,btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        btnn= (Button) findViewById(R.id.bb);
        btn=(Button) findViewById(R.id.b);
         btnn.setOnClickListener(new View.OnClickListener() {
           @Override
             public void onClick(View view) {
               Intent intent = new Intent( getApplicationContext() , sell_in.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , browsercategory.class);
                startActivity(intent);
            }
        });

    }
}
