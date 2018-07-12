package com.example.nasar_khan.sql;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SQL extends AppCompatActivity {
    Button button,button2,button3;

    EditText text,text2;
    String em="";
    String pass="";
    final String URL="http://192.168.1.2/andro/reg.php";
    final String LURL="http://192.168.1.2/andro/login.php";
    public MediaPlayer mp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        button= (Button) findViewById(R.id.reg);
        text= (EditText) findViewById(R.id.temail);
        text2= (EditText) findViewById(R.id.tpassword);
        button2= (Button) findViewById(R.id.btn);
        button3= (Button) findViewById(R.id.but);

        //sounds usage

        mp1 = MediaPlayer.create(this, R.raw.mix);
        mp1.start();
        //mp1.setLooping(true);

        //for button3 that is goes directly
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , select.class);
                startActivity(intent);
            }
        });

        // for login i use button2

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = text.getText().toString();
                pass = text2.getText().toString();
                StringRequest request = new StringRequest(Request.Method.POST,LURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object= new JSONObject(response);
                            String msg = object.getString("message");
                            Boolean err = object.getBoolean("error");

                            if (err) {
                                Toast.makeText(SQL.this, "Unable To Log,Try Again.", Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(SQL.this,msg , Toast.LENGTH_LONG).show();
                                Intent intent = new Intent( getApplicationContext() , select.class);
                                startActivity(intent);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(SQL.this, e.toString(), Toast.LENGTH_LONG).show();


                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SQL.this, error.toString(), Toast.LENGTH_LONG).show();

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("text",em);
                        params.put("text2",pass);
                        return params;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(SQL.this);
                rq.add(request);
            }
        });

//for registration i use button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em = text.getText().toString();
                pass = text2.getText().toString();
                StringRequest request = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object= new JSONObject(response);
                            String msg = object.getString("message");
                            Boolean err = object.getBoolean("error");

                            if (err) {
                                Toast.makeText(SQL.this, "Error Has Occured", Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(SQL.this,msg , Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            Toast.makeText(SQL.this, e.toString(), Toast.LENGTH_LONG).show();


                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SQL.this, error.toString(), Toast.LENGTH_LONG).show();

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("text",em);
                        params.put("text2",pass);
                        return params;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(SQL.this);
                request.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                rq.add(request);
                  }
        });

    }
}
