package com.example.afreen.speakupfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class contact extends Activity {
    Context context = null;SessionManager session;
    ContactsAdapter objAdapter;
    ListView lv = null;
    EditText edtSearch = null;static String s;
    LinearLayout llContainer = null;
    Button btnOK = null;
    RelativeLayout rlPBContainer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        session = new SessionManager(getApplicationContext());
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.contact);session = new SessionManager(getApplicationContext());
        rlPBContainer = (RelativeLayout) findViewById(R.id.pbcontainer);
        edtSearch = (EditText) findViewById(R.id.input_search);
        llContainer = (LinearLayout) findViewById(R.id.data_container);
        btnOK = (Button) findViewById(R.id.ok_button);
        Button but=(Button)findViewById(R.id.next);
       /* but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(contact.this,second.class);
                startActivity(intent);
            }
        });*/
        btnOK.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                getSelectedContacts();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",s);
                setResult(1,intent);
                finish();
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                      int arg3) {
                // When user changed the Text
                String text = edtSearch.getText().toString()
                        .toLowerCase(Locale.getDefault());
                objAdapter.filter(text);
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
        addContactsInList();
    }
    String no[]=new String[1000];int i=0;
    private void getSelectedContacts() {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        for (ContactObject bean : ContactsListClass.phoneList) {
            if (bean.isSelected()) {
                sb.append(bean.getName());
                sb.append(",");
                no[i]=""+bean.getName();
                i=i+1;

            }
        }
         s = sb.toString().trim();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(context, "Select atleast one Contact",
                    Toast.LENGTH_SHORT).show();
        } else {
            // s = s.substring(0, s.length() - 1);


           // session.createLoginSession(no,i);
            Toast.makeText(context, "Selected Contacts : " +s,
                    Toast.LENGTH_SHORT).show();
        }

/*        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("string",s));

        nameValuePairs.add(new BasicNameValuePair("name",ph.name));
        String result=null;InputStream is=null;
        try
        {
            HttpClient httpClient=new DefaultHttpClient(new BasicHttpParams());
            HttpPost httpPost=new HttpPost("http://seju.esy.es/send_notification.php");


            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();
            is=entity.getContent();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
            StringBuilder sb1=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null)
            {
                sb1.append(line+"\n");
            }
            result=sb1.toString();

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



*/

    }
    private void addContactsInList() {
        // TODO Auto-generated method stub
        Thread thread = new Thread() {
            @Override
            public void run() {
                showPB();
                try {
                    Cursor phones = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, null, null, null);
                    try {
                        ContactsListClass.phoneList.clear();
                    } catch (Exception e) {
                    }
                    for(int i=0;i<add_post.l;i++) {
                        String phoneName = add_post.products[i];
                        String phoneNumber = add_post.second[i];
                        String phoneImage = phones
                                .getString(phones
                                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                        ContactObject cp = new ContactObject();


                        cp.setName(phoneName);
                        cp.setNumber(phoneNumber);
                        cp.setImage(phoneImage);
                        ContactsListClass.phoneList.add(cp);
                    }
                    phones.close();
                    lv = new ListView(context);
                    lv.setLayoutParams(new LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            llContainer.addView(lv);
                        }
                    });
                    Collections.sort(ContactsListClass.phoneList,
                            new Comparator<ContactObject>() {
                                @Override
                                public int compare(ContactObject lhs,
                                                   ContactObject rhs) {
                                    return lhs.getName().compareTo(
                                            rhs.getName());
                                }
                            });
                    objAdapter = new ContactsAdapter(contact.this,
                            ContactsListClass.phoneList);
                    lv.setAdapter(objAdapter);
                    lv.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent,
                                                View view, int position, long id) {
                            CheckBox chk = (CheckBox) view
                                    .findViewById(R.id.contactcheck);
                            ContactObject bean = ContactsListClass.phoneList
                                    .get(position);
                            if (bean.isSelected()) {
                                bean.setSelected(false);
                                chk.setChecked(false);
                            } else {
                                bean.setSelected(true);
                                chk.setChecked(true);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hidePB();
            }
        };
        thread.start();
    }
    void showPB() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                rlPBContainer.setVisibility(View.VISIBLE);
                edtSearch.setVisibility(View.GONE);
                btnOK.setVisibility(View.GONE);
            }
        });
    }
    void hidePB() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                rlPBContainer.setVisibility(View.GONE);
                edtSearch.setVisibility(View.VISIBLE);
                btnOK.setVisibility(View.VISIBLE);
            }
        });
    }
}