package com.example.afreen.speakupfinal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;


public class commnt2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        ViewPager mPager1 = (ViewPager)findViewById(R.id.pager1);

        FunPagerAdapter1 mAdapter1 = new FunPagerAdapter1();

        mPager1.setAdapter(mAdapter1);

    }




}

