package com.app.haptikchat.adapeter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.haptikchat.chatmain.ChatHistoryFragment;
import com.app.haptikchat.chatmain.ChatSummeryFragment;

/**
 * Created by swapna on 4/3/17.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Tab1", "Tab2" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                ChatHistoryFragment tab1 = new ChatHistoryFragment();
            return tab1;
            case 1:
                ChatSummeryFragment tab2 = new ChatSummeryFragment();
                return tab2;
            default :
                return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
