package com.example.afreen.speakupfinal;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {


            spinner = (ProgressBar) findViewById(R.id.progressBar1);
            spinner.setVisibility(View.GONE);
            spinner.setVisibility(View.VISIBLE);


            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // TODO: Your application init goes here.
                    Intent mInHome = new Intent(MainActivity.this, Login.class);
                    MainActivity.this.startActivity(mInHome);
                    MainActivity.this.finish();
                }
            }, 9000);


        } else {
            Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
        }

    }
}