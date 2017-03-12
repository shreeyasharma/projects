package com.example.afreen.speakupfinal;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.DatePicker;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;

/**
 * Created by sejal on 15/8/16.
 */
public class ThirdActivity extends AppCompatActivity {
    EditText etFName,etLName,etemail,etmm,etyy,etpass,etphone,etcode,uname;
    TextView result;
    static EditText etDD;
    Button insert;
    static int year;
static int month,day;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.main);
        etFName=(EditText)findViewById(R.id.f_name);
        etLName=(EditText)findViewById(R.id.l_name);
        etDD=(EditText)findViewById(R.id.dd);

        etDD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                showcalender(v);
            }

        });
        uname=(EditText)findViewById(R.id.uname);

        etemail=(EditText)findViewById(R.id.e_id);

        etpass=(EditText)findViewById(R.id.pass);
        etphone=(EditText)findViewById(R.id.phone);
        etcode=(EditText)findViewById(R.id.code);
        insert=(Button)findViewById(R.id.but1);
        insert.setOnClickListener(new View.OnClickListener(){
            InputStream is=null;
            int i=0;
            @Override
            public void onClick(View ar)
            { String gname=""+uname.getText().toString();
                String name=""+etFName.getText().toString();
                String lname=""+etLName.getText().toString();
                String dd=""+day;
                String mm=""+month;
                String yy=""+year;

                String pass=""+etpass.getText().toString();
                String phone=""+etphone.getText().toString();
                String code=""+etcode.getText().toString();
                String email=""+etemail.getText().toString();

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("name",name));
                nameValuePairs.add(new BasicNameValuePair("dd",dd));
                nameValuePairs.add(new BasicNameValuePair("mm",mm));
                nameValuePairs.add(new BasicNameValuePair("yy",yy));
                nameValuePairs.add(new BasicNameValuePair("pass",pass));
                nameValuePairs.add(new BasicNameValuePair("phone",phone));
                nameValuePairs.add(new BasicNameValuePair("lname",lname));
                nameValuePairs.add(new BasicNameValuePair("code",code));
                nameValuePairs.add(new BasicNameValuePair("email",email));
                nameValuePairs.add(new BasicNameValuePair("uname",gname));
                try
                {
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://seju.esy.es/tutorial.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response=httpClient.execute(httpPost);
                    HttpEntity entity=response.getEntity();
                    is=entity.getContent();
                   // Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                    StringBuilder sb=new StringBuilder();
                    String line=null;
                    while((line=reader.readLine())!=null)
                    {
                        sb.append(line);
                    }
                   String result1=sb.toString();

                    Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
                    if(result1.equals("success")){
                    Intent intent=new Intent(ar.getContext(),Login.class);
                    ar.getContext().startActivity(intent);}

                                   }
                catch(ClientProtocolException e)
                {
                    Log.e("ClientProtocol","Log_tag");
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    Log.e("Log_tag","IOException");
                     e.printStackTrace();
                }

            }
        });






    }

public void showcalender(View v)
{

    DialogFragment newFragment = new DatePickerFragment();
    newFragment.show(getSupportFragmentManager(), "datePicker");
}
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
           year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            etDD.setText(day + "/" + (month + 1) + "/" + year);
        }

    }}

