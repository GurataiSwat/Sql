package com.example.nasar_khan.sql;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class browsecow extends AppCompatActivity {
    final String furl="http://192.168.56.1/andro/fetchdata.php";
    ListView objec;
    ImageView imgv;
    String[] imm;
    int l;
//    String[] imm;
byte[] byteArray;

    Bitmap[] n;
    Bitmap[] DownloadImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsecow);
         objec = (ListView) findViewById(R.id.yaha);
         imgv=(ImageView) findViewById(R.id.yeh);
        final AutoCompleteTextView autoo= (AutoCompleteTextView) findViewById(R.id.auto);

        StringRequest request= new StringRequest(Request.Method.POST, furl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Bitmap[] b;

                    JSONObject object= new JSONObject(response);
                    JSONArray image=object.getJSONArray("image");
                    JSONArray phone= object.getJSONArray("phone");
                    JSONArray price= object.getJSONArray("price");
                    JSONArray address= object.getJSONArray("address");
                    imm= new String[image.length()];
                    String[] ph= new String[phone.length()];
                    String[] pr= new String[price.length()];
                    String[] ad= new String[address.length()];
                    b = new Bitmap[image.length()];

                    for (l=0; l<image.length(); l++)
                    {

                        imm[l]= image.getString(l);
                        new download().execute();
                       // b[l] = DownloadImageUrl;




                    }

                    for(int i=0; i<phone.length();i++)
                    {
                        ph[i]= phone.getString(i);


                    }
                    for (int j=0; j<price.length(); j++)
                    {
                        pr[j]= price.getString(j);
                    }

                    for (int k=0; k<address.length(); k++)
                    {

                        ad[k]= address.getString(k);
                    }

                  // ArrayAdapter<String> adapter= new ArrayAdapter<String>(browsecow.this,android.R.layout.simple_list_item_1,ph);

                    //here ta  new download().execute();
                    //new download().execute();

                    customview adapter=new customview(browsecow.this,ph,pr,ad,imm);

                   objec.setAdapter(adapter);
                    autoo.setAdapter(adapter);
                    autoo.setThreshold(0);
//                    new download().execute();
                    //for me to test


                    //end of for me to test
                } catch (JSONException e) {
                    Toast.makeText(browsecow.this,e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(browsecow.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameter= new HashMap<>();
                parameter.put("id","1");

                return parameter;
            }

        };


        RequestQueue rq= Volley.newRequestQueue(this);

        request.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(request);

    }
    //for donwload image to try
   // new download().execute();

    private class download extends AsyncTask<Object, Object, Bitmap[]>
    {
         Bitmap dimage;
        @Override
        protected Bitmap[] doInBackground(Object... voids) {
            this.makeRequest();
            return DownloadImageUrl[];
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(browsecow.this,"Image downloading..",Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap[] bitmap) {
            super.onPostExecute(bitmap[]);
        }
        private void makeRequest()
        {

            final ImageRequest reques= new ImageRequest("http://192.168.56.1/andro/images/"+imm[l],new Response.Listener<Bitmap>()
            {

                @Override
                public void onResponse(Bitmap bitmap) {
                    try {

                        // imgv.setImageBitmap(bitmap);
                     //  imgv.setImageBitmap(bitmap);
                      //dimage = bitmap;
                      // DownloadImageUrl = bitmap;
d
                        //below one statement mine
                        adapter=new customview(browsecow.this,bitmap);
                        objec.setAdapter(adapter);
                    } catch (Exception e) {

                        Toast.makeText(browsecow.this, "here is the problem in response", Toast.LENGTH_LONG).show();
                        Toast.makeText(browsecow.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            },200,100,  Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(browsecow.this, error.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(browsecow.this, "here is the problem in response", Toast.LENGTH_LONG).show();

                }

            });
            RequestQueue r = Volley.newRequestQueue(browsecow.this);
            r.add(reques);

        }

    }

    //end of downlaod image to try
}
