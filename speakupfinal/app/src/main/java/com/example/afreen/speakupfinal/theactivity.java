package com.example.afreen.speakupfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by AFREEN on 12/2/2016.
 */

public class theactivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saved)
    {
        super.onCreate(saved);
        setContentView(R.layout.read);
        Button button=(Button)findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(theactivity.this,news.class);
                startActivity(intent);
            }
        });
    }
}
