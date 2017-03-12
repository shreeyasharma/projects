package com.example.afreen.speakupfinal;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afreen.speakupfinal.GPSTracker;
import com.example.afreen.speakupfinal.SessionManager;

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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by sejal on 30/9/16.
 */
public class add_post1 extends AppCompatActivity  {
    TextView but;
    EditText name;
    public static String product[]=new String[100];
    public static String result;
    private Bitmap bitmap;
    public static int l;
    EditText content2,email2,tagged;static double latitude;
    static double longitude;static String stringThisAddress = "";
    public static String distance[];
    Geocoder geocoder;
    GPSTracker gps;
    Button submit1;
    ImageView image;
    Button check1;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    SessionManager session;
    ChipsMultiAutoCompleteTextview mu;
    static String  products[]=new String[100];
    static String second[]=new String[100];
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";

    private Uri fileUri; // file url to store image/video
    public TextView gps2;EditText text;

    Button submit;
    String content,uname,email,tag_person;
    ImageView camera;String rad;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpost3);
        but = (TextView)findViewById(R.id.sel_name);
        submit=(Button)findViewById(R.id.submit);
        submit1=(Button)findViewById(R.id.grp1);

        content2=(EditText)findViewById(R.id.problem);
        email2=(EditText)findViewById(R.id.email);
        tagged=(EditText)findViewById(R.id.multiAutoCompleteTextView1);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        uname = user.get(SessionManager.KEY_NAME);



        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name.setVisibility(View.VISIBLE);

            }
        });
        camera=(ImageView)findViewById(R.id.camera);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                // start the image capture Intent
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

            }

        });






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> data = new HashMap<>();
                content=content2.getText().toString();
                email=email2.getText().toString();
                tag_person=tagged.getText().toString();

                StringBuilder sb=new StringBuilder();
                sb.append(tag_person+",");

                //tag_person=tagged.getText().toString();
                uploadImage();

            }});
        list();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // video successfully recorded
                // preview the recorded video
                previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if(requestCode==1)
        {
            String message=data.getStringExtra("MESSAGE");
            text.setText(message);
        }
    }
    private void previewCapturedImage() {
        try {
            // hide video preview
            // videoPreview.setVisibility(View.GONE);

            camera.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            camera.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private void previewVideo() {
        try {
            // hide image preview
            // imgPreview.setVisibility(View.GONE);

            //videoPreview.setVisibility(View.VISIBLE);
            //videoPreview.setVideoPath(fileUri.getPath());
            // start playing
            //videoPreview.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    public void uploadImage()
    {
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(add_post1.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];

                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                data.put("content",content);
                data.put("uname",uname);
                data.put("tag_person",tag_person);
                data.put("email",email);
                data.put("image",uploadImage);
                String result = rh.sendPostRequest("http://seju.esy.es/posting.php",data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
    void list(){
        String Name;HashMap<String,String>hash=new HashMap<>();
        mu = (ChipsMultiAutoCompleteTextview) findViewById(R.id.multiAutoCompleteTextView1);
        Name="sejal_goyal";
        String name=""+Name;
        int i=0,k=0;

        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("name",name));
        String result=null;InputStream is=null;
        try
        {
            HttpClient httpClient=new DefaultHttpClient(new BasicHttpParams());
            HttpPost httpPost=new HttpPost("http://seju.esy.es/choose.php");
            InputStream inputStream=null;

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();
            is=entity.getContent();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
            StringBuilder sb=new StringBuilder();
            String line=null;int j=0;
            while((line=reader.readLine())!=null)
            {
                if(j%2==0){
                    products[i]=line;i=i+1;
                }
                if(j%2!=0)
                {
                    hash.put(products[i-1],line);

                }

                sb.append(line+"\n");
                j=j+1;
            }
            //  Toast.makeText(getApplicationContext(),products[0],Toast.LENGTH_LONG).show();
            //          result=sb.toString();
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

        // String[] item = getResources().getStringArray(R.array.country);

        Log.i("", "Country Count : " + products.length);
        mu.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, products));
        mu.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        tag_person=mu.getText().toString();
    }

    public void get() {
        geocoder = new Geocoder(add_post1.this);
        gps = new GPSTracker(add_post1.this);


        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        }
    }
}

