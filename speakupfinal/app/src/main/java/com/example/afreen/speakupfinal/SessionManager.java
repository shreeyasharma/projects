package com.example.afreen.speakupfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
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
import org.apache.http.params.BasicHttpParams;

public class SessionManager {

    SharedPreferences pref;

    Editor editor;


    Context _context;


    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AndroidHivePref";


    private static final String IS_LOGIN = "IsLoggedIn";


    public static final String KEY_NAME = "name";


    public static final String KEY_EMAIL = "email";
    public static final String KEY_token = "token";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(String name, String email,String token){

        editor.putBoolean(IS_LOGIN, true);


        editor.putString(KEY_NAME, name);


        editor.putString(KEY_EMAIL, email);
editor.putString(KEY_token,token);

        editor.commit();
    }
    public void createLoginSession(String name, String email){

        editor.putBoolean(IS_LOGIN, true);


        editor.putString(KEY_NAME, name);


        editor.putString(KEY_EMAIL, email);


        editor.commit();
    }
    public void changeToken(String token){

        editor.putBoolean(IS_LOGIN, true);



        editor.putString(KEY_token,token);

        editor.commit();
    }


    public void checkLogin(){

        if(!this.isLoggedIn()){

            Intent i = new Intent(_context, Login.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
        }

    }



    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
user.put(KEY_token,pref.getString(KEY_token,null));

        return user;
    }


    public void logoutUser(){
SessionManager session=new SessionManager(_context);
        HashMap<String, String> user = session.getUserDetails();
      String  name = user.get(SessionManager.KEY_NAME);
String token=user.get(SessionManager.KEY_token);
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("name",name));
        nameValuePairs.add(new BasicNameValuePair("token",token));
        InputStream is=null;

        String result=null;
        try
        {
            HttpClient httpClient=new DefaultHttpClient(new BasicHttpParams());
            HttpPost httpPost=new HttpPost("http://seju.esy.es/Logout.php");
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
            Toast.makeText(_context,result,Toast.LENGTH_LONG).show();

//Toast.makeText(_context,result,Toast.LENGTH_LONG).show();
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
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, Login.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        _context.startActivity(i);
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}