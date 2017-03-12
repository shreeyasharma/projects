package com.example.afreen.speakupfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sejal on 23/11/16.
 */
public class other_comment extends AppCompatActivity {

EditText con1;
    String myJSON;
    String p;
    String k;
    String k2;
    String pp1;
    String result1;
    JSONArray peoples1 = null;
    Button l1;
    ArrayList<HashMap<String, String>> personList2;
    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "id";
    private static final String TAG_ID1 = "id1";

    ListView list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.add_post1);
        list2 = (ListView) findViewById(R.id.listv4);
        personList2 = new ArrayList<HashMap<String,String>>();

        getData();
        con1=(EditText)findViewById(R.id.email1);


        l1=(Button) findViewById(R.id.commentu);

        l1.setOnClickListener(new View.OnClickListener()
        {

            InputStream is=null;
            int i=0;
            @Override

            public void onClick(View v)
            {
                p=con1.getText().toString();
                k2=ph.name;
                k=FetchFactTask.d;
                Toast.makeText(getApplicationContext(),p+k2+k,Toast.LENGTH_LONG).show();

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("name",p));
                nameValuePairs.add(new BasicNameValuePair("dd",k2));
                nameValuePairs.add(new BasicNameValuePair("mm",k));
           try
                {
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://seju.esy.es/reply3.php");
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
                        Intent intent=new Intent(v.getContext(),Login.class);
                        v.getContext().startActivity(intent);}

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
    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples1 = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples1.length(); i++) {
                JSONObject c = peoples1.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String id1 = c.getString(TAG_ID1);



                HashMap<String, String> persons1 = new HashMap<String, String>();

                persons1.put(TAG_ID, id);
                persons1.put(TAG_ID1, id1);



                personList2.add(persons1);
            }

            ListAdapter adapter = new SimpleAdapter(other_comment.this, personList2, R.layout.list_item2,
                    new String[]{TAG_ID,TAG_ID1},
                    new int[]{R.id.id,R.id.id1}
            );

            list2.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData() {
      pp1=  FetchFactTask.d;
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);


        nameValuePairs.add(new BasicNameValuePair("email1",pp1));

       Toast.makeText(getApplicationContext(),pp1,Toast.LENGTH_LONG).show();

        InputStream is=null;
        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://seju.esy.es/rplycom.php");
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
            result1=sb.toString();
            Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
            /** String result1=sb.toString();

             Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
             if(result1.equals("success")){
             Intent intent=new Intent(ar.getContext(),Login.class);
             ar.getContext().startActivity(intent);}
             **/
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



        myJSON = result1;
        Toast.makeText(getApplicationContext(), myJSON, Toast.LENGTH_LONG).show();

        showList();
    }
}
