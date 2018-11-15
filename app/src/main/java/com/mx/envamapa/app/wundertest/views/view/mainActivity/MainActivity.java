package com.mx.envamapa.app.wundertest.views.view.mainActivity;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;

import android.widget.LinearLayout;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.commons.adapters.PhotoAdapter;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photos;
import com.mx.envamapa.app.wundertest.views.presenter.mainPresenter.MainPresenter;
import com.mx.envamapa.app.wundertest.views.view.detailActivity.DetailActivity;

public class MainActivity extends AppCompatActivity implements RowClickListener<Photo>,
        SwipeRefreshLayout.OnRefreshListener,
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

    private boolean isLoading;
    private int max = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application.setInstance(getApplicationContext());
        presenter = new MainPresenter(this, ((Application)getApplicationContext()));

        initView();
        initElements();
        presenter.downloadData(max);
        isLoading = true;
    }

    private void initView(){
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        swipe = findViewById(R.id.swipe);
        linear = findViewById(R.id.linear);
        recycler = findViewById(R.id.recycler);
    }

    private void initElements(){
        swipe.setOnRefreshListener(this);
        photoAdapter = new PhotoAdapter();
        photoAdapter.setRowClickListener(this);
        recycler.setAdapter(photoAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0 && totalItemCount >= max) {
                    presenter.downloadData(max++);
                    Utils.printLogInfo("asdfkjhsdakjfhasdlkf", false, true);
                }
            }
        });
    }

    @Override
    public void reloadList(Photos photos){
        isLoading = false;
        if (swipe.isRefreshing()) {
            swipe.setRefreshing(false);
            photoAdapter.clear();
        }
        photoAdapter.addAll(photos.getPhoto());
    }

    @Override
    public void onRowClicked(int row, Photo item) {
        startActivity(DetailActivity.createIntent(this, row, photoAdapter.getAll()));
    }

    @Override
    public void onRefresh() {
        max = 1;
        presenter.downloadData(max);
    }
}
