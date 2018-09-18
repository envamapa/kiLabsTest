package com.mx.envamapa.app.wundertest.views.view.mainActivity;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.adapters.SectionsPagerAdapter;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.list.CarListFragment;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.maps.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityInterface{

    //UI elements
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        configItems();
    }

    private void initView(){
        mViewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabs);
    }

    private void configItems(){
        //List of fragments
        fragments = new ArrayList<>();
        CarListFragment carListFragment = new CarListFragment();
        fragments.add(carListFragment);
        MapFragment mapFragment = new MapFragment();
        fragments.add(mapFragment);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}
