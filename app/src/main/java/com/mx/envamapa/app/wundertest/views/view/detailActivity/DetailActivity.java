package com.mx.envamapa.app.wundertest.views.view.detailActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.commons.ImageSize;
import com.mx.envamapa.app.wundertest.commons.ParallaxPageTransformer;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.commons.adapters.DetailPagerAdapter;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;

import java.io.Serializable;
import java.util.List;

import static com.mx.envamapa.app.wundertest.commons.Constants.EXTRA_INDEX;
import static com.mx.envamapa.app.wundertest.commons.Constants.EXTRA_ITEMS;


/**
 * Created by enya on 11/15/18.
 */

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface, View.OnClickListener {
    
    //UI Elements
    private ViewPager pager;
    private TextView tvOwner;
    private TextView tvTitle;
    private View lnrFooter;
    private View ivClose;
    private View ivInfo;
    private View ivShare;
    
    private List<Photo> items;
    
    
    public static Intent createIntent(Context context, int index, List<Photo> items) {
        return new Intent(context, DetailActivity.class)
                .putExtra(EXTRA_INDEX, index)
                .putExtra(EXTRA_ITEMS, (Serializable) items);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        initView();
        setViewPager();
    }

    private void initView(){
        pager = findViewById(R.id.pager);
        tvOwner = findViewById(R.id.tvOwner);
        tvTitle = findViewById(R.id.tvTitle);
        lnrFooter = findViewById(R.id.lnrFooter);
        ivClose = findViewById(R.id.ivClose);
        ivInfo = findViewById(R.id.ivInfo);
        ivShare = findViewById(R.id.ivShare);

        ivClose.setOnClickListener(this);
        ivInfo.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    private void setViewPager(){
        int index = getIntent().getIntExtra(EXTRA_INDEX, -1);
        items = (List<Photo>) getIntent().getSerializableExtra(EXTRA_ITEMS);
        if (index == -1) {
            finish();
        } else if (!Utils.isOnline(getApplicationContext())) {
            showConnectionError();
        } else {
            pager.setPageTransformer(false, new ParallaxPageTransformer(R.id.image));
            pager.setAdapter(new DetailPagerAdapter(getSupportFragmentManager(), items));
            pager.setCurrentItem(index);
            onPageSelected(index);
        }

        Utils.setVectorBg(ivClose, R.drawable.ic_close_24dp, android.R.color.white, R.color.gray2);
        Utils.setVectorBg(ivInfo, R.drawable.ic_info_outline_24dp, android.R.color.white, R.color.gray2);
        Utils.setVectorBg(ivShare, R.drawable.ic_share_24dp, android.R.color.white, R.color.gray2);
    }

    @Override
    public void showConnectionError() {
        Utils.createSnackbar(this, R.string.connectionErrorMessage)
                .setAction(R.string.settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                }).show();
    }

    public void onPageSelected(int position) {
        //showLoadingDialog();
        tvOwner.setText(items.get(position).getOwner());
        tvTitle.setText(items.get(position).getTitle());
        //flickrService.getDetailAsync(items.get(position).getId());
    }

    private void showInfoDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setIcon(R.drawable.ic_info_outline_24dp)
                .setMessage(message)
                .setNegativeButton(R.string.close, null)
                .show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivClose:
                finish();
                break;
            case R.id.ivInfo:
                int index = getIntent().getIntExtra(EXTRA_INDEX, -1);
                showInfoDialog(getString(R.string.description), items.get(index).getTitle());
                break;
            case R.id.ivShare:
                String subject = items.get(pager.getCurrentItem()).getTitle();
                String text = items.get(pager.getCurrentItem()).getImageUrl(ImageSize.LARGE);
                startActivity(Utils.createShareIntent(subject, text));
                break;
        }
    }
}
