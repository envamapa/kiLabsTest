package com.mx.envamapa.app.wundertest.views.presenter.splashPresenter;

import android.Manifest;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.commons.PermissionUtils;
import com.mx.envamapa.app.wundertest.data.sources.MyRealm;
import com.mx.envamapa.app.wundertest.domain.manager.TaskManager;
import com.mx.envamapa.app.wundertest.domain.task.RequestCarsTask;
import com.mx.envamapa.app.wundertest.views.view.splash.SplashScreenInterface;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by enya on 17/09/18.
 */

public class SplashPresenter implements SplashPresenterInterface, PermissionUtils.PermissionResultCallback {

    SplashScreenInterface viewInterface;
    Application application;

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
        PermissionUtils permissionUtils;
        ArrayList<String> permissionList;

        permissionUtils = new PermissionUtils(viewInterface.getMainActivity() ,application.getBaseContext(), this);

        permissionList = new ArrayList<>();
        permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE);
        permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        permissionUtils.checkPermission(permissionList, application.getApplicationContext().getString(R.string.msg_explain_need_permission), Constants.CODE_REQUEST_PERMISSION);
    }

    /**
     * Verify if database exists
     */
    @Override
    public void databaBaseExist() {
        if(!MyRealm.existDB()){
            taskManager.execute(new RequestCarsTask(application.getApplicationContext(), new RequestCarsTask.RequestCarsTaskListener() {
                @Override
                public void onFinishTask(String fillComplete) {
                    viewInterface.showMessage(fillComplete);
                    viewInterface.goToMainActivity();
                }

                @Override
                public void onErrorTask(String code, String message) {
                    viewInterface.showMessage(message);
                    viewInterface.error();
                }
            }), null);
        }else{
            viewInterface.showMessage(application.getApplicationContext().getResources().getString(R.string.data_exists));
            viewInterface.goToMainActivity();
        }
    }


    /** Permission results */
    @Override
    public void permissionGranted(int requestCode) {
        databaBaseExist();
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
}
