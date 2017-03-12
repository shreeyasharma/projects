package com.example.afreen.speakupfinal;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import com.squareup.picasso.Picasso;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class FetchFactTask extends AsyncTask<Integer,Void,HashMap> {

    TextView outTxt;
    TextView outTxt1;
    public static int kk1=0;
    public static int ll=0;
    public static String d="";
    public static String k="";
    ImageView urlImage;Button button;Button like1;Button dislike1;
    public FetchFactTask(TextView outputTxt,TextView outputTxt1, ImageView outurl, Button but, Button like,Button dislike){
        super();
        outTxt = outputTxt;
        outTxt1 = outputTxt1;
        urlImage=outurl;
        button=but;
        like1=like;
        dislike1=dislike;


    }


    @Override
    protected HashMap<Integer[], String> doInBackground(Integer... uri) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httppost = new HttpPost("http://seju.esy.es/final.php");

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

        HashMap<Integer[],String>hashMap=new HashMap<>();
        hashMap.put(uri,result);
        return hashMap;










        /*HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
*/    }

    @Override
    protected void onPostExecute(HashMap hashMap) {super.onPostExecute(hashMap);
        Iterator myVeryOwnIterator = hashMap.keySet().iterator();
        //String []result=null;

        final Integer[] key=(Integer[])myVeryOwnIterator.next();
        String  result=(String)hashMap.get(key);



        String TAG_RESULTS = "result";
        final String TAG_NAME = "name";String[] ids={"hii","hlo"};
        final String TAG_URL="url";String[] url={"hii","hlo"};
        final String TAG_nme="nme";
        String url1[]=new String[100];
        //outTxt.setText(Arrays.toString(key));
        JSONArray peoples = null;
        String rr=null;
        try {



            JSONObject jsonObj = new JSONObject(result);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            ids = new String[peoples.length()];
            url=new String[peoples.length()];
            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                ids[i] = c.getString(TAG_NAME);
                url[i]=c.getString(TAG_URL);
                url1[i]=c.getString(TAG_nme);


            }


            outTxt.setText(ids[key[0]]);Picasso.with(urlImage.getContext()).load(url[key[0]]).into(urlImage);
            outTxt1.setText(url1[key[0]]);Picasso.with(urlImage.getContext()).load(url[key[0]]).into(urlImage);
           // final String[] finalUrl = url;


            final String[] finalUrl = url;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s=outTxt.getText().toString();
                    d= finalUrl[key[0]];
                //    String d= TAG_URL;
                    Toast.makeText(v.getContext(),s+d,Toast.LENGTH_LONG).show();
                    Intent readMore = new Intent(v.getContext(), other_comment.class);
                    v.getContext().startActivity(readMore);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();outTxt.setText(result);
        }




        //  outTxt.setText("hii"+rr);

    }

}
