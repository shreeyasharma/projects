package com.example.afreen.speakupfinal;
        import android.app.ProgressDialog;
        import android.content.ActivityNotFoundException;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.location.Address;
        import android.location.Geocoder;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.content.Intent;

        import android.util.Base64;
        import android.view.View;

        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.squareup.picasso.Picasso;

        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class ph extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;
    SessionManager session;
    private Button addpost;
    private Button vpost;
    public static String name;
private TextView text;
    private ImageView imageView;
    public static final String UPLOAD_URL = "http://seju.esy.es/img_upload.php";
    public static final String UPLOAD_KEY = "image";
    private Button buttonChoose;
    private Button buttonUpload;
    private Bitmap bitmap;
    public TextView gps2;
    static double latitude;
    static double longitude;
    private int PICK_IMAGE_REQUEST = 1;
    Button logout;
static String stringThisAddress = "";
Button submit3;
    Button submit4;
    ImageButton chrt;
    ImageButton ht;ImageButton fb;
    Geocoder geocoder;
    GPSTracker gps;ImageView iv;TextView tv;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_profile_screen_xml_ui_design);
        imageView = (ImageView) findViewById(R.id.user_profile_photo);
       iv=(ImageView)findViewById(R.id.notify);
        tv=(TextView)findViewById(R.id.notif);
        session = new SessionManager(getApplicationContext());
        addpost=(Button)findViewById(R.id.add);addpost.setOnClickListener(this);
       // Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        buttonChoose = (Button) findViewById(R.id.buttonLoadPicture);
        buttonUpload = (Button) findViewById(R.id.CameraPicture);
        vpost = (Button) findViewById(R.id.com);
        logout=(Button)findViewById(R.id.logout);
        ht=(ImageButton)findViewById(R.id.res);

        chrt=(ImageButton)findViewById(R.id.res1);

        text=(TextView)findViewById(R.id.name);
        submit3=(Button)findViewById(R.id.grp1);
        submit4=(Button)findViewById(R.id.ne);
        session.checkLogin();
        fb=(ImageButton)findViewById(R.id.fb1);
        fb.setOnClickListener(this);
        HashMap<String, String> user = session.getUserDetails();
        name = user.get(SessionManager.KEY_NAME);


        String string = user.get(SessionManager.KEY_EMAIL);
text.setText(name);
        Toast.makeText(getApplicationContext(),string,Toast.LENGTH_LONG).show();
        Picasso.with(getApplicationContext()).load(string).into(imageView);

     //        gps2=(TextView)findViewById(R.id.gps);
        geocoder = new Geocoder(this);
        gps = new GPSTracker(ph.this);


        if (gps.canGetLocation()) {
             latitude = gps.getLatitude();
             longitude = gps.getLongitude();
            boolean parsable = true;

            if (parsable) {


                List<Address> geoResult = findGeocoder(latitude, longitude);
           if (geoResult != null) {
                    List<String> geoStringResult = new ArrayList<String>();
                    for (int i = 0; i < geoResult.size(); i++) {
                        Address thisAddress = geoResult.get(i);
                       // String stringThisAddress = "";
                        for (int a = 0; a < thisAddress.getMaxAddressLineIndex(); a++) {
                            stringThisAddress += thisAddress.getAddressLine(a) + " ";
                        }
                        gps2=(TextView)findViewById(R.id.listResult);
                                gps2.setText(stringThisAddress);



                    }





                }
            }
        }

    buttonUpload.setOnClickListener(this);
logout.setOnClickListener(this);
        buttonChoose.setOnClickListener(this);
        vpost.setOnClickListener(this);
        submit3.setOnClickListener(this);
        submit4.setOnClickListener(this);
        ht.setOnClickListener(this);
        chrt.setOnClickListener(this);
        upload_Image();
   //HashMap<String,String>hash=new HashMap<>();
     //   hash.put("uid",name);
   //RequestHandler rh2=new RequestHandler();
     //   String result2 =rh2.sendPostRequest("https://seju.esy.es/get_image.php",hash);
   //     Toast.makeText(getApplicationContext(),result2,Toast.LENGTH_LONG).show();
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",0);
        intent.putExtra("aspectY",0);
        intent.putExtra("outputX",200);
        intent.putExtra("outputY",150);
        try {

            intent.putExtra("return-data", true);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
        catch(ActivityNotFoundException e)
        {}}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Bundle extras2 = data.getExtras();
            if (extras2 != null) {
                bitmap = extras2.getParcelable("data");
               // imageView.setImageDrawable(null);
                imageView.setImageBitmap(bitmap);



            }

        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ph.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
              //  Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];

                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                data.put(UPLOAD_KEY,uploadImage);
                data.put("name",name);
                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
    private void upload_Image(){
        class Upload_Image extends AsyncTask<String,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ph.this, "Fetching...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                iv.setVisibility(View.VISIBLE);
                tv.setText(s);
                tv.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String bitmap = params[0];

                //String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
String lat=""+latitude;
                String long1=""+longitude;
                //            data.put(UPLOAD_KEY,uploadImage);
                data.put("name",name);
                data.put("latitude",lat);
                data.put("longitude",long1);
                String result = rh.sendPostRequest("http://seju.esy.es/get_notification.php",data);
                session.createLoginSession(name,result,Login.Recent_Token2);
                return result;
            }
        }

        Upload_Image ui = new Upload_Image();
        ui.execute(name);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }

        if(v == buttonUpload){
            uploadImage();
        }
        if(v == addpost)
        {

            Intent intent=new Intent(this,add_post.class);
            this.startActivity(intent);
        }
if(v == logout)
{
   // upload_Image();
    session.logoutUser();
}
        if(v == submit4)
        {
            Intent intent=new Intent(this,theactivity.class);
            this.startActivity(intent);
        }


        if(v == vpost)
        {
            Intent intent=new Intent(this,commnt1.class);
            this.startActivity(intent);
        }
if(v==fb)
{
    Intent intent=new Intent(this,fbupload.class);
    startActivity(intent);
}
        if(v == ht)
        {
            Intent intent=new Intent(this,commnt2.class);
            this.startActivity(intent);
        }
        if(v == chrt)
        {
            Intent intent=new Intent(this,pie.class);
            this.startActivity(intent);
        }




        if(v == submit3)
        {
            Intent intent=new Intent(this,mygrp.class);
            this.startActivity(intent);
        }


       /* if(v == buttonView){
            viewImage();
        }*/
    }





    private List<Address> findGeocoder(Double lat, Double lon) {
        final int maxResults = 1;
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lon, maxResults);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    private void viewImage() {
//        startActivity(new Intent(this, ImageListView.class));
    }
}




















