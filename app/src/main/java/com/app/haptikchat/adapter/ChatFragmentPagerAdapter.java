package com.app.haptikchat.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.app.haptikchat.chatdetail.history.HistoryFragment;
import com.app.haptikchat.chatdetail.summary.SummaryFragment;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Tab1", "Tab2" };
     Context context;

    public ChatFragmentPagerAdapter(FragmentManager fm, Context context) {
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
                //ChatHistoryFragment tab1 = new ChatHistoryFragment();
            return HistoryFragment.newInstance("one");
            case 1:
               // ChatSummeryFragment tab2 = new ChatSummeryFragment();
                return SummaryFragment.newInstance("two");
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
