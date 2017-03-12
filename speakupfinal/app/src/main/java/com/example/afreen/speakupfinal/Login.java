
package com.example.afreen.speakupfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Login extends AppCompatActivity
        {
            String myJSON;
            JSONArray peoples=null;
            ArrayList<HashMap<String,String>> personList;
            SessionManager session;
            ListView list;public static String Recent_Token2 ="hiiiii";
            EditText Name,etpass;
            Button insert; private static final String reg_token="REG_TOKEN";

ImageButton fb,google;
                                                                                                                                          @SuppressLint("NewApi")
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                setContentView(R.layout.second);
               Name=(EditText)findViewById(R.id.name1);
fb=(ImageButton)findViewById(R.id.fb);
google=(ImageButton)findViewById(R.id.google);

                session = new SessionManager(getApplicationContext());


                etpass=(EditText)findViewById(R.id.pass1);
TextView admin=(TextView) findViewById(R.id.admin);
                insert=(Button)findViewById(R.id.but1);
                if(session.isLoggedIn())
                {
                    Intent intent = new Intent(this, ph.class);
                    this.startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
                insert.setOnClickListener(new OnClickListener(){
                    InputStream is=null;
                    int i=0;
                    @Override
                    public void onClick(View ar)
                    {
                        Recent_Token2= FirebaseInstanceId.getInstance().getToken();
                        String name=""+Name.getText().toString();
                        String pass=""+etpass.getText().toString();

                        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
                        nameValuePairs.add(new BasicNameValuePair("name",name));
                        nameValuePairs.add(new BasicNameValuePair("Token",Recent_Token2));
                        nameValuePairs.add(new BasicNameValuePair("pass",pass));

                        String result=null;
                        try
                        {
                            HttpClient httpClient=new DefaultHttpClient(new BasicHttpParams());
                            HttpPost httpPost=new HttpPost("http://seju.esy.es/Login2.php");
                            InputStream inputStream=null;

                            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                            HttpResponse response=httpClient.execute(httpPost);
                            HttpEntity entity=response.getEntity();
                            is=entity.getContent();
                            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                            StringBuilder sb=new StringBuilder();
                            String line=null;
                            while((line=reader.readLine())!=null)
                            {
                                sb.append(line+"\n");
                            }
                            result=sb.toString();

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
                        finally {
                            try{if(is!=null)is.close();}
                            catch(Exception squish){}
                        }

                        gett(result,name,pass);

                    }
                });
                   fb.setOnClickListener(new OnClickListener(){

                       @Override
                       public void onClick(View v) {
                           Intent intent=new Intent(Login.this,AndroidFacebookActivity.class);
                           startActivity(intent);
                       }
                   });
google.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(Login.this,ChooserActivity.class);
        startActivity(intent);
    }
});
            }

public void gett(String result,String name,String pass){
    myJSON=result;


    int p;

    myJSON = myJSON.replace("\n", "").replace("\r", "");
    if(!myJSON.equals("error")) {

        session.createLoginSession(name,myJSON,Recent_Token2);

        Toast.makeText(getApplicationContext(),myJSON, Toast.LENGTH_LONG).show();
       Intent intent = new Intent(this, ph.class);
        this.startActivity(intent);
    }
else
    {
        Toast.makeText(getApplicationContext(), myJSON, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Login.class);
        this.startActivity(intent);
    }

}
            public void onClick(View v)
            {
                if(v.getId()==R.id.text6)
                {
                    Intent intent=new Intent(this,ThirdActivity.class);
                    this.startActivity(intent);

                }
            }
            public void onClick3(View v)
            {
                if(v.getId()==R.id.admin)
                {
                    Intent intent=new Intent(this,Login2.class);
                    this.startActivity(intent);

                }
            }
        }
