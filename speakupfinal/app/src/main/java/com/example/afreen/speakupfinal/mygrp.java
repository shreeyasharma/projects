package com.example.afreen.speakupfinal;

/**
 * Created by sejal on 27/11/16.
 */


        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.StrictMode;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.TextView;
        import android.widget.Button;
        import android.view.View.OnClickListener;
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

public class mygrp extends AppCompatActivity {
    String myJSON;
    boolean selected = false;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    JSONArray peoples = null;
    String pp[]=new String[20];
    ArrayList<HashMap<String, String>> personList;

    ListView list;
    EditText mEdit;
    EditText etFName;
    //    TextView result;
    Button insert;
    Button insert1;
    Button insert2;
    EditText uname;
    String email;
    static  String email1;
    EditText etemail;
    EditText etemail1;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.grp_main);

        //list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();



        etemail=(EditText)findViewById(R.id.e_id);
        etemail1=(EditText)findViewById(R.id.e_id1);





        insert1=(Button)findViewById(R.id.but2);
        insert1.setOnClickListener(new OnClickListener()     {


            InputStream is=null;
            @Override
            public void onClick(View ar)
            {

                email=etemail.getText().toString();

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);


                nameValuePairs.add(new BasicNameValuePair("email",email));

                try
                {
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://seju.esy.es/variable.php");
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


            }
        });














        // View btnClick = findViewById(R.id.but1);
        //btnClick.setOnClickListener(this);
        insert = (Button) findViewById(R.id.but1);
        insert.setOnClickListener(new OnClickListener() {
            InputStream is = null;


            @Override
            public void onClick(View v) {
                email1=etemail1.getText().toString();
                Toast.makeText(getApplicationContext(),email1,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(mygrp.this, seju.class);
                startActivity(intent);
            }
        });


        insert2 = (Button) findViewById(R.id.but9);
        insert2.setOnClickListener(new OnClickListener() {
            InputStream is = null;


            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mygrp.this, viewgrp.class);
                startActivity(intent);
            }
        });





    }






}











/**           HashMap<String, String> persons = new HashMap<String, String>();


 persons.put(TAG_NAME, name);

 // TextView tv = (TextView) findViewById(R.id.text1);
 //  tv.setText(name);
 //  tv.setText("nnsnsn");
 personList.add(persons);
 }

 ListAdapter adapter = new SimpleAdapter(
 MainActivity.this, personList, R.layout.list_item,
 new String[]{TAG_NAME},
 new int[]{R.id.name}
 );

 list.setAdapter(adapter);

 }

 **/












