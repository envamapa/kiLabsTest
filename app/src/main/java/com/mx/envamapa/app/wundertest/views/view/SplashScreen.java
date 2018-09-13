package com.mx.envamapa.app.wundertest.views.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mx.envamapa.app.wundertest.R;

public class SplashScreen extends AppCompatActivity implements SplashScreenInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
}
