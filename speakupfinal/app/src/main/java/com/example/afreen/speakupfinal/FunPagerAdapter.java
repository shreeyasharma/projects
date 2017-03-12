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


public class FunPagerAdapter extends PagerAdapter{
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

    public FunPagerAdapter(){


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
        View page = inflater.inflate(R.layout.page_fun, null);

        RelativeLayout back = (RelativeLayout)page.findViewById(R.id.background);


        int randomColor = rnd.nextInt(5);

        final TextView tv = (TextView)page.findViewById(R.id.lblTxt);
        final TextView tv1 = (TextView)page.findViewById(R.id.pb);
        ImageView url=(ImageView)page.findViewById(R.id.lblTxt2);
        Button but=(Button)page.findViewById(R.id.but11);
        final Button like2=(Button)page.findViewById(R.id.like);

        final TextView l=(TextView)page.findViewById(R.id.txt61);
        final TextView lol=(TextView)page.findViewById(R.id.txt166);
        final Button dislike2=(Button)page.findViewById(R.id.dislike);
        Button comm=(Button) page.findViewById(R.id.comment);
        tv.setBackgroundResource(R.drawable.generic_back);
        back.setBackgroundColor(colors[randomColor]);

        FetchFactTask fft = new FetchFactTask(tv,tv1,url,comm,like2,dislike2);
        fft.execute(pos);

        ((ViewPager) collection).addView(page, 0);
        like2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f=FetchFactTask.d;
                f1=  ph.name;

                String pro[]= new String[10];
                int i=0;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                    nameValuePairs.add(new BasicNameValuePair("email1", f));
                    nameValuePairs.add(new BasicNameValuePair("email2", f1));


                    InputStream is = null;
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost("http://seju.esy.es/lik.php");
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        HttpResponse response = httpClient.execute(httpPost);
                        HttpEntity entity = response.getEntity();
                        is = entity.getContent();
                        // Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                        StringBuilder sb2 = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            pro[i]=line;
                            i++;



                        }
                        Toast.makeText(v.getContext(), pro[0]+"", Toast.LENGTH_LONG).show();
                        String result3 = sb2.toString();
                        l.setText(pro[0]);
                        lol.setText(pro[1]);

                     //   Toast.makeText(v.getContext(), result3, Toast.LENGTH_LONG).show();
                        /** String result1=sb.toString();

                         Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
                         if(result1.equals("success")){
                         Intent intent=new Intent(ar.getContext(),Login.class);
                         ar.getContext().startActivity(intent);}
                         **/
                    } catch (ClientProtocolException e) {
                        Log.e("ClientProtocol", "Log_tag");
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.e("Log_tag", "IOException");
                        e.printStackTrace();
                    }
                }
                // l.setText(kk);


        });
        dislike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    f2 = FetchFactTask.d;
                    f3 = ph.name;
String pro[]=new String[10];
                int i=0;
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                    nameValuePairs.add(new BasicNameValuePair("email1", f2));
                    nameValuePairs.add(new BasicNameValuePair("email2", f3));


                    InputStream is = null;
                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost("http://seju.esy.es/dlik.php");
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        HttpResponse response = httpClient.execute(httpPost);
                        HttpEntity entity = response.getEntity();
                        is = entity.getContent();
                        // Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                        StringBuilder sb2 = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb2.append(line);
                            pro[i]=line;
                            i++;
                        }
                        Toast.makeText(v.getContext(), pro[0]+"", Toast.LENGTH_LONG).show();
                        l.setText(pro[0]);
                        lol.setText(pro[1]);

                     //   Toast.makeText(v.getContext(), result3, Toast.LENGTH_LONG).show();
                        /** String result1=sb.toString();

                         Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
                         if(result1.equals("success")){
                         Intent intent=new Intent(ar.getContext(),Login.class);
                         ar.getContext().startActivity(intent);}
                         **/
                    } catch (ClientProtocolException e) {
                        Log.e("ClientProtocol", "Log_tag");
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.e("Log_tag", "IOException");
                        e.printStackTrace();
                    }

                }

        });
        comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=tv.getText().toString();
                //  Toast.makeText(_mContext(),"We are in page " + position,
                //        Toast.LENGTH_SHORT).show();
                Intent readMore = new Intent(v.getContext(), other_comment.class);
                v.getContext().startActivity(readMore);


            }
        });


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
