package com.example.user.tabapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Tab3PagerAdapter extends FragmentStatePagerAdapter {
    public int mNumOfTabs;
    public Tab3Fragment parent;

    public Tab3PagerAdapter(FragmentManager fm, int NumOfTabs, Tab3Fragment _parent) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.parent = _parent;
    }

    @Override
    public Fragment getItem(int position) {

        if (0 <= position && position < parent.all_tags.size()) {
            return FilteredJsonListViewFragment.newInstance(parent.all_tags.get(position), position + 1);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}