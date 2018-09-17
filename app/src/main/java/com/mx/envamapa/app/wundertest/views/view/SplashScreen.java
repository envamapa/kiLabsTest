package com.mx.envamapa.app.wundertest.views.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.views.presenter.SplashPresenter;

import javax.inject.Inject;

public class SplashScreen extends AppCompatActivity implements SplashScreenInterface {

    SplashPresenter presenter;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ((Application) getApplication()).getAppComponent().inject(this);

        presenter = new SplashPresenter(this, ((Application) getApplication()));
        downloadInformation();
    }

    private void downloadInformation(){
        presenter.verifyPermissions();
    }

    @Override
    public void goToMainActivity(){

    }

    @Override
    public void showMessage(String message){

    }

    @Override
    public Activity getMainActivity(){
        return this;
    }

    @Override
    public void error(){
        this.finish();
    }
}
