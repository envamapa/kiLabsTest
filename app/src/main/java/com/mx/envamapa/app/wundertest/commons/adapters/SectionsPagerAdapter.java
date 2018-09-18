package com.mx.envamapa.app.wundertest.commons.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mx.envamapa.app.wundertest.views.view.mainActivity.MainActivity;

import java.util.List;

/**
 * Created by enya on 17/09/18.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //Fragments list
    private List<Fragment> fragments;

    public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return fragments.size();
    }
}