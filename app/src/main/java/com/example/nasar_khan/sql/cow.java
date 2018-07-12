package com.example.nasar_khan.sql;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import static com.example.nasar_khan.sql.R.id.camera;

public class cow extends AppCompatActivity {
    Button dod, save;
    Button cam;
    String name;
    ImageView imgView;
    TextView ob;
    //for camera
    String image_name, encoded_string;
    Bitmap bitmap;
    Bitmap bmp;
    File file;
    Uri file_uri;
    //
    // it is also new
    EditText phone, price, address;
    String em = "";
    String pass = "";
    String add = "";
    //for 2nd hash map

    String addr = "";
    //end of 2nd
    //final String url = "http://192.168.56.1/andro/image.php";
    final String url = "http://192.168.1.2/andro/image.php";
    // end of it is also new
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow);
        dod = (Button) findViewById(R.id.bbt);
        ob = (TextView) findViewById(R.id.hhh);
        //for new operions that is to server
        save = (Button) findViewById(R.id.button2);
        phone = (EditText) findViewById(R.id.editText3);
        price = (EditText) findViewById(R.id.editText4);
        address = (EditText) findViewById(R.id.editText5);
        //for 2nd hash map
        address = (EditText) findViewById(R.id.editText5);
        //end of 2nd hash map

//camera coding starts form here
        cam = (Button) findViewById(R.id.pti);
        imgView = (ImageView) findViewById(R.id.imgView);

        //for button to set camera
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getFileUri();
                i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                startActivityForResult(i, 10);
            }
        });




//end for camera coding

    // end of new oeprtion that is to server
        dod.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){

        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);


    }
    });

    //save button
        save.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){

        em = phone.getText().toString();
        pass = price.getText().toString();
        addr = address.getText().toString();
        //for 2nd hash map
        addr = address.getText().toString();
        //end of 2nd hash map
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String msg = object.getString("message");
                    Boolean err = object.getBoolean("error");

                    if (err) {
                        Toast.makeText(cow.this, "Unable To Log,Try Again.", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(cow.this, msg, Toast.LENGTH_LONG).show();
                        //try  for gallery image to upload with save button
//                        bmp=((BitmapDrawable) imgView.getDrawable()).getBitmap();
//                        Date date = new Date();
//                       String d= date.toString();
//                        name=d.replace(" ","");
//                       name=name.replace(":","");
//                        name=name.replace("+","");
//                        image_name=name+"t.jpg";
//                        Toast.makeText(cow.this,image_name,Toast.LENGTH_LONG).show();
//                        new  Encodeg_image().execute();

                       // new Encode_image().execute();
                        //end for try on gallery image to upload with save button
                        Toast.makeText(cow.this, "You Clicked the button save", Toast.LENGTH_LONG).show();



                    }
                } catch (JSONException e) {
                    Toast.makeText(cow.this, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(cow.this, "jason exception", Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(cow.this, error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(cow.this, "error of response", Toast.LENGTH_LONG).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("text", em);
                params.put("text2", pass);
                params.put("text3", addr);
                params.put("text4",image_name);
                params.put("encoded",encoded_string);
                return params;
                // for 2nd hash map


                //end of 2nd hash map
            }

        };
        RequestQueue r = Volley.newRequestQueue(cow.this);
        request.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        r.add(request);

        //end of onclick scope
            //for gallery image to upload

            //end for gallery image to upload

    }
    });
    // end of save button


}

    //for camera coding
    private void getFileUri() {
        Date date = new Date();
        String name= (date.toString())+"j.jpg";
        image_name = name.replace(" ","");
        image_name = image_name.replace(":","");
        image_name = image_name.replace("+","");
        Toast.makeText(this,image_name,Toast.LENGTH_LONG).show();
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + image_name);
        file_uri = Uri.fromFile(file);
    }

    //end for camera coding

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //for camera coding
            if(requestCode == 10 && resultCode == RESULT_OK)
            {

                new  Encode_image().execute();

            }


            //end of camra coding

            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                //by me Cursor
                // Get the Image from data
                Uri selectedImage = data.getData();
                //by new
                imgView.setImageURI(selectedImage);
                //then agin try
                bmp=((BitmapDrawable) imgView.getDrawable()).getBitmap();
                Date date = new Date();
                String d= date.toString();
                name=d.replace(" ","");
                name=name.replace(":","");
                name=name.replace("+","");
                image_name=name+"t.jpg";
                Toast.makeText(cow.this,image_name,Toast.LENGTH_LONG).show();
                new  Encodeg_image().execute();
                //end of then aign try
                //end by new
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                // Get the cursor
//                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                // Move to first row assert by me
//
//                       cursor.moveToFirst();
//                       int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                       imgDecodableString = cursor.getString(columnIndex);
//
//                       //for new name of image
//                        File n=new File(imgDecodableString);
//                        String imagename= n.getName();
//                        Date date = new Date();
//                        String d= date.toString();
//                        name=(d+imagename).replace(" ","");
//                        name=name.replace(":","");
//                        image_name=name.replace("+","");
//                        Toast.makeText(this,image_name,Toast.LENGTH_LONG).show();
//                        //end of name of image
//                        cursor.close();
//
//                        // Set the Image in ImageView after decoding the String
//                        imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));








            }  
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        //for camra activity result



    }
    //for camera coding
   private class Encode_image extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {

            bitmap = BitmapFactory.decodeFile(file_uri.getPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array,0);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            imgView.setImageBitmap(bitmap);
            Toast.makeText(cow.this,"Your image",Toast.LENGTH_LONG).show();

               }

    }
    //end of for camera coding
    //asyncroneous coding for gallery uploading image
    private class Encodeg_image extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array,0);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

    }
    //end of asyncroneous coding for gallery uploading image



}

