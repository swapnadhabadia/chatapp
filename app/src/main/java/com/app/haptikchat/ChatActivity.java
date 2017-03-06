package com.app.haptikchat;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.app.haptikchat.adapter.ChatFragmentPagerAdapter;
import com.app.haptikchat.chatdetail.summary.RefreshFragmentInterface;


public class ChatActivity extends FragmentActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChatFragmentPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        adapter = new ChatFragmentPagerAdapter(getSupportFragmentManager(), ChatActivity.this);
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RefreshFragmentInterface fragment = (RefreshFragmentInterface) adapter.instantiateItem(viewPager, position);
                if (fragment != null) {
                    fragment.refreshData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabLayout.setupWithViewPager(viewPager);
    }


}

