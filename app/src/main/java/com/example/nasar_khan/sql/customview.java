package com.example.nasar_khan.sql;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import static android.R.attr.bitmap;
import static android.R.attr.value;
import static com.example.nasar_khan.sql.R.layout.customview;

/**
 * Created by Nasar_Khan on 9/4/2017.
 */

public class  customview extends ArrayAdapter{
    private String[] val,val2,val3,val4;
    Bitmap[] val5;

    //add by me
//    private  String val4;
    //end add by me
     customview(@NonNull Context context, String[] ph, String[] values, String[] values2, String[] values3) {
        super(context, customview, values);
        this.val2=values;
        this.val3=values2;
        this.val4=values3;
        //this.val5=values4;



    }
    customview(@NonNull Context context, Bitmap[] values4) {
        super(context, customview, values4);
        this.val5=values4;



    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View CustomView=inflater.inflate(customview,parent,false);
        TextView phone= (TextView) CustomView.findViewById(R.id.phone);
        TextView price= (TextView) CustomView.findViewById(R.id.pr);


        TextView address= (TextView) CustomView.findViewById(R.id.add);
        TextView imagename= (TextView) CustomView.findViewById(R.id.txt);
        ImageView img=(ImageView) CustomView.findViewById(R.id.imgu);
         
        String n=getItem(position).toString();
        phone.setText("     "+n);
        price.setText("     Rs: "+this.val2[position]+ "/-");
        address.setText(this.val3[position]);
        imagename.setText(this.val4[position]);
        img.setImageBitmap(this.val5[position]);
        return CustomView;
    }



}

