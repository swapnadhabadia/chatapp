package com.app.haptikchat;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.app.haptikchat.adapeter.SampleFragmentPagerAdapter;
import com.app.haptikchat.chatmain.ChatHistoryFragment;

public class MainActivity extends FragmentActivity implements ChatHistoryFragment.OnFragmentInteractionListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tabs);




        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onHomeItemClick(String name, int position, boolean b) {

    }
}

