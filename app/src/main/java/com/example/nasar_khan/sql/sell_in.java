package com.example.nasar_khan.sql;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sell_in extends AppCompatActivity {
    Button cow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_in);
        cow= (Button) findViewById(R.id.btn2);
        cow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),cow.class);
                startActivity(intent);
            }
        });

    }
}
