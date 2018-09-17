package com.mx.envamapa.app.wundertest.views.view;

import android.app.Activity;

/**
 * Created by enya on 13/09/18.
 */

public interface SplashScreenInterface {
    void goToMainActivity();

    void showMessage(String message);

    Activity getMainActivity();

    void error();
}
