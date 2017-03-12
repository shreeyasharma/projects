package com.example.afreen.speakupfinal;

/**
 * Created by sejal on 27/11/16.
 */


        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListView;
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

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

/**
 * Created by AFREEN on 11/16/2016.
 */


public class seju extends AppCompatActivity {
    String myJSON;
    boolean selected = false;
    MyCustomAdapter dataAdapter = null;


    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    JSONArray peoples = null;
    static String pp[] = new String[10];
    String kk[] = new String[20];
    static Boolean ll[]=new Boolean[10];
    static int n=0;
    int jj=0;
    String no[]=new String[1000];
    ArrayList<HashMap<String, String>> personList;
    static int t;
    ListView list;
    EditText mEdit;
    EditText etFName;
    //    TextView result;
    Button insert;
    Button insert1;
    EditText uname;
    EditText etemail;
    Button bb;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.list_item);
        bb = (Button) findViewById(R.id.but3);
        bb.setOnClickListener(new View.OnClickListener() {
            InputStream is = null;
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                getSelectedContacts();
            }
        });
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://seju.esy.es/grp.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;

                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();

                } catch (Exception e) {
                    // Oops
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                    } catch (Exception squish) {
                    }
                }

                return result;

            }

            @Override
            protected void onPostExecute(String result) {

                myJSON = result;

                Toast.makeText(getApplicationContext(), myJSON, Toast.LENGTH_LONG).show();
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute();

    }
    int e=0;
    public void  getSelectedContacts(){

        // etemail=(EditText)findViewById(R.id.e_id);

        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        for (int o=0;o<ll.length;o++) {

            if(ll[o]==Boolean.TRUE) {
                //   sb.append(ll[o]);
                //   sb.append(",");
//            no[e] = "" + ll[o];

                mygrp p =new mygrp();
                String email5=p.email1;

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("email",email5));
                nameValuePairs.add(new BasicNameValuePair("gname",pp[o]));

                InputStream is=null;
                try
                {
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://seju.esy.es/insertgrp.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response=httpClient.execute(httpPost);
                    HttpEntity entity=response.getEntity();
                    is=entity.getContent();
                    // Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                    StringBuilder sb1=new StringBuilder();
                    String line=null;
                    while((line=reader.readLine())!=null)
                    {
                        sb1.append(line);
                    }
                    String result2=sb1.toString();
                    Toast.makeText(getApplicationContext(),result2,Toast.LENGTH_LONG).show();
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







                // Toast.makeText(getApplicationContext(), "Selected Contacts : " + o + "is" + ll[o],
                //Toast.LENGTH_LONG).show();
                //  e = e + 1;

            }

            String s = sb.toString().trim();
            /**     if (TextUtils.isEmpty(s)) {
             Toast.makeText(getApplicationContext(), "Select atleast one Contact",
             Toast.LENGTH_SHORT).show();
             } else {
             // s = s.substring(0, s.length() - 1);
             /**/

            // session.createLoginSession(no,i);
            //    Toast.makeText(getApplicationContext(), "Selected Contacts : " +no,
            //   Toast.LENGTH_LONG).show();

        }
    }
    void showList() {


        try {


            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);


            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);

                String name = c.getString(TAG_NAME);
                pp[i] = name;
            }
            //Array list of countries
            ArrayList<String> countryList = new ArrayList<String>();
            for (int i = 0; i < pp.length; i++) {


                countryList.add(pp[i]);

            }
        /*country = new Country("ALB","Albania",true);
        countryList.add(country);
        country = new Country("DZA",add_post.products[0],false);
        countryList.add(country);
        country = new Country("ASM",add_post.products[1],true);
        countryList.add(country);
        country = new Country("AND",add_post.products[2],true);
        countryList.add(country);
        country = new Country("AGO",add_post.products[3],false);
        countryList.add(country);
        country = new Country("AIA",add_post.products[4],false);
        countryList.add(country);*/

            //create an ArrayAdaptar from the String Array
            dataAdapter = new MyCustomAdapter(this,
                    R.layout.tt, countryList);
            ListView listView = (ListView) findViewById(R.id.listView1);
            // Assign adapter to ListView
            listView.setAdapter(dataAdapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // When clicked, show a toast with the TextView text
                    String country = (String) parent.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(),
                            "Clicked on Row: " + country,
                            Toast.LENGTH_LONG).show();
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class MyCustomAdapter extends ArrayAdapter<String> {

        private ArrayList<String> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<String> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<String>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
            ImageView im;
            TextView tx;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.tt, null);

                holder = new ViewHolder();

                holder.name = (CheckBox) convertView.findViewById(R.id.contactcheck);

                holder.tx = (TextView) convertView.findViewById(R.id.contactname);

                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        String country = (String) cb.getTag();
                        //  Toast.makeText(getApplicationContext(),
                        // "Clicked on Checkbox: " + cb.getText() +
                        //       " is " + cb.isChecked(),
                        //  Toast.LENGTH_LONG).show();
                        jj=1;
                        ;
                        selected = cb.isChecked();
                        ll[position]=selected;
                        //   Toast.makeText(getApplicationContext(), ""+ll[position], Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String country = countryList.get(position);


            holder.tx.setText(country);
            holder.name.setChecked(selected);
//pp[n]=country;
            //ll[n]=jj;
            //  ll[position]=selected;
            pp[position]=country;



            return convertView;

        }


    }
}


