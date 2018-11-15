package com.mx.envamapa.app.wundertest.views.view.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.views.presenter.splashPresenter.SplashPresenter;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.MainActivity;

import javax.inject.Inject;

public class SplashScreen extends AppCompatActivity implements SplashScreenInterface {

    SplashPresenter presenter;

    @Inject
    Context context;

    //UI Variables
    private LottieAnimationView animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ((Application) getApplication()).getAppComponent().inject(this);

        presenter = new SplashPresenter(this, ((Application) getApplication()));
        initAnimation();
    }

    private void initAnimation(){
        animation = findViewById(R.id.animation);
        new CountDownTimer(Constants.SPLASH_TIME, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                animation.playAnimation();
            }

            @Override
            public void onFinish() {
                downloadInformation();
            }
        }.start();
    }

    private void downloadInformation(){
        presenter.verifyPermissions();
    }

    @Override
    public void goToMainActivity(){
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
        this.finish();
    }

    @Override
    public void showMessage(String message){
        Utils.printLogInfo(message, true, false);
    }

    @Override
    public Activity getMainActivity(){
        return this;
    }

    @Override
    public void error(){
        Utils.printLogError("No permissions", true, false);
        this.finish();
    }
}
