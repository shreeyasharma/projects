package com.example.afreen.speakupfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class selection extends Activity {
    EditText edtSearch = null;boolean selected = false;static Country  pp[] = new Country[1000];
    MyCustomAdapter dataAdapter = null;int jj=0;static Boolean ll[]=new Boolean[1000];
static String str;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        edtSearch = (EditText) findViewById(R.id.input_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                      int arg3) {
                // When user changed the Text
                String text = edtSearch.getText().toString()
                        .toLowerCase(Locale.getDefault());

            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        //Generate list View from ArrayList
        displayListView();

        checkButtonClick();
       Button bb = (Button) findViewById(R.id.findSelected);
        bb.setOnClickListener(new OnClickListener() {
            InputStream is = null;
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                getSelectedContacts();
            }
        });

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<Country> countryList = new ArrayList<Country>();
        for(int i=0;i<add_post.l;i++){
            Country country = new Country(add_post.product[i],add_post.second[i],add_post.products[i],false);
            countryList.add(country);}


        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.list_row, countryList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + country.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    public void  getSelectedContacts(){

      //  etemail=(EditText)findViewById(R.id.e_id);

        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        for (int o=0;o<ll.length;o++) {
            if(ll[o]==Boolean.TRUE) {
                Toast.makeText(getApplicationContext(),""+pp[o],Toast.LENGTH_LONG).show();
             /*   nameValuePairs.add(new BasicNameValuePair("gname",pp[o]));

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
               /* catch(ClientProtocolException e)
                {
                    Log.e("ClientProtocol","Log_tag");
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    Log.e("Log_tag","IOException");
                    e.printStackTrace();
                }


            }*/




        }}

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        private ArrayList<Country> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Country> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Country>();
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
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_row, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.contactno);
                holder.name = (CheckBox) convertView.findViewById(R.id.contactcheck);
                holder.im=(ImageView) convertView.findViewById(R.id.contactimage);
                holder.tx=(TextView) convertView.findViewById(R.id.contactname);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Country country = (Country) cb.getTag();

                       // Toast.makeText(getApplicationContext(),
                         //       "Clicked on Checkbox: " +
                           //             " is " + cb.isChecked(),
                             //   Toast.LENGTH_LONG).show();
                        country.setSelected(cb.isChecked());
                        jj=1;

                        selected = cb.isChecked();
                        ll[position]=selected;
                        Toast.makeText(getApplicationContext(), ""+ll[position], Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Country country = countryList.get(position);
            holder.code.setText(" (" +  country.getCode() + ")");
            holder.tx.setText(country.getName());
            holder.name.setChecked(country.isSelected());
            holder.name.setTag(country);
            Picasso.with(getApplicationContext()).load(country.geturl()).into(holder.im);
            String ss=""+holder.tx.getText();
            Toast.makeText(getApplicationContext(),ss,Toast.LENGTH_LONG).show();
           // pp[position]=holder.tx.getText();
            return convertView;

        }

    }

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Country> countryList = dataAdapter.countryList;
                for(int i=0;i<countryList.size();i++){
                    Country country = countryList.get(i);
                    if(country.isSelected()){
                        responseText.append("\n" + country.getName());
                    }
                }


                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }

}