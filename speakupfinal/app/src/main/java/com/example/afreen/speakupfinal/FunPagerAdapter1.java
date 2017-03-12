package com.example.afreen.speakupfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


/********************
 * In general an Adapter is an object that is used to create Views
 * to represent some data that comes from sone sort of datasource.
 * This one is pulling the data from the http://numbersapi.com web API.
 * Other adapters might use a local datasource such as a file, or an SQL database.
 *
 * This adapter will dynamically create each "page" View for us
 * as they are needed. It handles choosing and setting a random color

 2 * for the background and the card, it also fires of the AsyncTask that will
 * pull the data from the web api and set it into our TextView
 ********************/


public class FunPagerAdapter1 extends PagerAdapter{
    public static int kk1=0;
    public static int ll=0;
    static String f;
    static String f2;
    static String f3;


    static String f1;
    private int[] colors = {Color.parseColor("#eee9e9"),
            Color.parseColor("#eee9e9"),
            Color.parseColor("#eee9e9"),
            Color.parseColor("#eee9e9"),
            Color.parseColor("#eee9e9")};


    private Random rnd;

    public FunPagerAdapter1(){


        super();
        rnd = new Random();
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(View collection, int pos) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = null
        View page = inflater.inflate(R.layout.page_fun2, null);

        RelativeLayout back = (RelativeLayout)page.findViewById(R.id.background);


        int randomColor = rnd.nextInt(5);

        final TextView tv = (TextView)page.findViewById(R.id.lblTxt1);
        final TextView tv1 = (TextView)page.findViewById(R.id.pb1);
        ImageView url=(ImageView)page.findViewById(R.id.lblTxt21);

        tv.setBackgroundResource(R.drawable.generic_back);
        back.setBackgroundColor(colors[randomColor]);

        FetchFactTask1 fft = new FetchFactTask1(tv,tv1,url);
        fft.execute(pos);

        ((ViewPager) collection).addView(page, 0);



        return page;
    }


    @Override
    public void destroyItem(View collection, int position, Object view) {
        //Util.Log("destroying" + position);
        ((ViewPager) collection).removeView((View) view);
    }

    public void post()
    {

    }

}
