package com.example.afreen.speakupfinal;

/**
 * Created by sejal on 27/11/16.
 */


        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
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
 * Created by AFREEN on 11/20/2016.
 */

public class kk extends AppCompatActivity {
    ListView list2;
    String myJSON;
    String result1;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_ID1 = "id1";
    Button insert3;
    Button l1;
    EditText et;
    String qq;
    String com;
    String email3;
    JSONArray peoples1 = null;
EditText con1;

    static String jj;
    ArrayList<HashMap<String, String>> personList2;
    ;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.showcommnt);
        list2 = (ListView) findViewById(R.id.listv2);
        personList2 = new ArrayList<HashMap<String, String>>();
        viewgrp p = new viewgrp();
        jj = p.tt;

        getData();

        et=(EditText)findViewById(R.id.email4);


        l1=(Button) findViewById(R.id.commentu1);

        l1.setOnClickListener(new View.OnClickListener()
        {

            InputStream is=null;
            int i=0;
            @Override

            public void onClick(View v)
            {
                viewgrp n = new viewgrp();
                email3 = n.tt;
                qq=ph.name;
                com=et.getText().toString();
                Toast.makeText(getApplicationContext(), email3, Toast.LENGTH_LONG).show();
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("email8", com));
                nameValuePairs.add(new BasicNameValuePair("email", email3));
                nameValuePairs.add(new BasicNameValuePair("email9", qq));
                try
                {
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://seju.esy.es/addgrpcommnt.php");
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

                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);}

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

            ListAdapter adapter = new SimpleAdapter(kk.this, personList2, R.layout.list_item3,
                    new String[]{TAG_ID,TAG_ID1},
                    new int[]{R.id.id,R.id.id1}
            );

            list2.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData() {

        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);


        nameValuePairs.add(new BasicNameValuePair("email",jj));
        Toast.makeText(getApplicationContext(),jj,Toast.LENGTH_LONG).show();

        InputStream is=null;
        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://seju.esy.es/commshow1.php");
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



