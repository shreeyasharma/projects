package com.example.afreen.speakupfinal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;


public class commnt1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        ViewPager mPager = (ViewPager)findViewById(R.id.pager);

        FunPagerAdapter mAdapter = new FunPagerAdapter();

        mPager.setAdapter(mAdapter);

    }




}

