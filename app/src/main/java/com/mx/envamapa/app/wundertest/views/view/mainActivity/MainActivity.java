package com.mx.envamapa.app.wundertest.views.view.mainActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.LinearLayout;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.adapters.PhotoAdapter;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photos;
import com.mx.envamapa.app.wundertest.views.presenter.mainPresenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements RowClickListener<Photo>,
        SwipeRefreshLayout.OnRefreshListener,
        NavigationView.OnNavigationItemSelectedListener,
        MainActivityInterface{

    //UI elements
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipe;
    private LinearLayout linear;
    private RecyclerView recycler;

    //Adapter
    private PhotoAdapter photoAdapter;

    //Presenter
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application.setInstance(getApplicationContext());
        presenter = new MainPresenter(this, ((Application)getApplicationContext()));

        initView();
        initElements();
        presenter.downloadData(15);
    }

    private void initView(){
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        swipe = findViewById(R.id.swipe);
        linear = findViewById(R.id.linear);
        recycler = findViewById(R.id.recycler);
    }

    private void initElements(){
        photoAdapter = new PhotoAdapter();
        photoAdapter.setRowClickListener(this);
        recycler.setAdapter(photoAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void reloadList(Photos photos){
        photoAdapter.addAll(photos.getPhoto());
    }

    @Override
    public void onRowClicked(int row, Photo item) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onRefresh() {

    }
}
