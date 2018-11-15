package com.mx.envamapa.app.wundertest.views.presenter.splashPresenter;

import android.Manifest;
import android.support.annotation.NonNull;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.commons.PermissionUtils;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.domain.manager.TaskManager;
import com.mx.envamapa.app.wundertest.views.view.splash.SplashScreenInterface;

import java.util.ArrayList;

import javax.inject.Inject;

public class SplashPresenter implements SplashPresenterInterface, PermissionUtils.PermissionResultCallback {

    private SplashScreenInterface viewInterface;
    private Application application;
    private PermissionUtils permissionUtils;

    @Inject
    TaskManager taskManager;

    public SplashPresenter(SplashScreenInterface viewInterface, Application application){
        this.viewInterface = viewInterface;
        this.application = application;
        application.getAppComponent().inject(this);
    }

    /**
     * Verify if there are internet and location permission
     * */
    @Override
    public void verifyPermissions(){
        ArrayList<String> permissionList;

        permissionUtils = new PermissionUtils(viewInterface.getMainActivity() ,application.getBaseContext(), this);

        permissionList = new ArrayList<>();
        permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE);

        permissionUtils.checkPermission(permissionList, application.getApplicationContext().getString(R.string.msg_explain_need_permission), Constants.CODE_REQUEST_PERMISSION);
    }


    /** Permission results */
    @Override
    public void permissionGranted(int requestCode) {
        Utils.printLogInfo("Permission Granted", true, false);
        viewInterface.goToMainActivity();
    }

    @Override
    public void partialPermissionGranted(int requestCode, ArrayList<String> grantedPermissions) {
        viewInterface.showMessage(application.getApplicationContext().getString(R.string.msg_explain_need_permission));
        verifyPermissions();
    }

    @Override
    public void permissionDenied(int requestCode) {
        viewInterface.showMessage(application.getApplicationContext().getString(R.string.msg_explain_need_permission));
        verifyPermissions();
    }

    @Override
    public void neverAskAgain(int requestCode) {
        viewInterface.showMessage(application.getApplicationContext().getString(R.string.msg_explain_need_permission));
        verifyPermissions();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
