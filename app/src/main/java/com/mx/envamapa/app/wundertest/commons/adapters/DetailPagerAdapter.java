package com.mx.envamapa.app.wundertest.commons.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;
import com.mx.envamapa.app.wundertest.views.view.detailActivity.DetailFragment;

import java.util.List;

public class DetailPagerAdapter
        extends FragmentStatePagerAdapter {

    private final List<Photo> items;

    public DetailPagerAdapter(FragmentManager fm, List<Photo> items) {
        super(fm);
        this.items = items;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(items.get(position));
    }

    @Override
    public int getCount() {
        return items.size();
    }

}