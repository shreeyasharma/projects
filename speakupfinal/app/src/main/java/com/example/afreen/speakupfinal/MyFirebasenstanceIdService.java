package com.example.afreen.speakupfinal;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sejal on 13/11/16.
 */
public class MyFirebasenstanceIdService extends FirebaseInstanceIdService {
    private static final String reg_token="REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        String Recent_Token = FirebaseInstanceId.getInstance().getToken();
      //  Log.d(reg_token, Recent_Token);
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
       // nameValuePairs.add(new BasicNameValuePair("name",name));
        nameValuePairs.add(new BasicNameValuePair("Token",Recent_Token));
        nameValuePairs.add(new BasicNameValuePair("reg_token",Login.Recent_Token2));
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


        Toast.makeText(getApplicationContext(),"helloooooooooooooooooo",Toast.LENGTH_LONG).show();
        Login.Recent_Token2=Recent_Token;
        SessionManager sessionManager=new SessionManager(getApplicationContext());
        sessionManager.changeToken(Recent_Token);
    }
}