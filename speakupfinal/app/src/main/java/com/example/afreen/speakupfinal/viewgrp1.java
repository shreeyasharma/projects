package com.example.afreen.speakupfinal;

/**
 * Created by AFREEN on 12/2/2016.
 */


/**
 * Created by sejal on 2/12/16.
 */

        import android.content.Intent;
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
 * Created by sejal on 27/11/16.
 */


        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.support.v7.app.AppCompatActivity;

/**
 * Created by AFREEN on 11/17/2016.
 */

        import android.os.AsyncTask;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
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
        import org.apache.http.params.BasicHttpParams;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.w3c.dom.Text;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;


public class viewgrp1 extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "id";
    Button insert3;
    Button insert4;
    JSONArray peoples1 = null;

    ArrayList<HashMap<String, String>> personList1;
    EditText xy;
    ListView list1;
    String result1;
    static String jj;
    static String tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listvi);
        list1 = (ListView) findViewById(R.id.listv1);
        personList1 = new ArrayList<HashMap<String,String>>();

        getData();
        insert3 =(Button) findViewById(R.id.but6);
        insert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                xy=(EditText)findViewById(R.id.eid3);
                tt=xy.getText().toString();
                if(tt.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter group name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(viewgrp1.this, kk3.class);
                    startActivity(intent);
                }

            }
        });


    }


    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples1 = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples1.length();i++){
                JSONObject c = peoples1.getJSONObject(i);
                String id = c.getString(TAG_ID);


                HashMap<String,String> persons1 = new HashMap<String,String>();

                persons1.put(TAG_ID,id);


                personList1.add(persons1);
            }

            ListAdapter adapter = new SimpleAdapter(viewgrp1.this, personList1, R.layout.list_item1,
                    new String[]{TAG_ID},
                    new int[]{R.id.id}
            );

            list1.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData() {
        jj=ph2.name;
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);


        nameValuePairs.add(new BasicNameValuePair("email",jj));
        Toast.makeText(getApplicationContext(),jj,Toast.LENGTH_LONG).show();

        InputStream is=null;
        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://seju.esy.es/grpshow1.php");
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








