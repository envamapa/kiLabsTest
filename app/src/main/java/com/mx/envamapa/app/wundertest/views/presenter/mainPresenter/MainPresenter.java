package com.mx.envamapa.app.wundertest.views.presenter.mainPresenter;

import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photos;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.RespImages;
import com.mx.envamapa.app.wundertest.domain.manager.TaskManager;
import com.mx.envamapa.app.wundertest.domain.task.GetImagesTask;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.MainActivityInterface;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by enya on 11/15/18.
 */

public class MainPresenter implements MainPresenterInterface {

    private MainActivityInterface viewInterface;
    private Application application;

    private boolean isLoading;

    @Inject
    TaskManager taskManager;

    public MainPresenter(MainActivityInterface viewInterface, Application application){
        this.viewInterface = viewInterface;
        this.application = application;
        application.getAppComponent().inject(this);
    }

    /**
     * Download images
     * */
    @Override
    public void downloadData(int page){
        if (Utils.isOnline(application.getBaseContext())) {
            isLoading = true;

            taskManager.execute(new GetImagesTask(page, application.getApplicationContext(), new GetImagesTask.GetImagesTaskListener() {
                @Override
                public void onFinishTask(RespImages respImages) {
                    viewInterface.reloadList(respImages.getPhotos());
                }

                @Override
                public void onErrorTask(String code, String message) {
                    /*viewInterface.showMessage(message);
                    viewInterface.error();*/
                }
            }), null);

            /*flickrService.searchAsync(page++);
            if (isScreenEmpty()) screenStateManager.showLoading();
            else adapter.addAll(null); // add null , so the adapter will check view_type and show progress bar at bottom*/
        } else {
            /*swipe.setRefreshing(false);
            if (isScreenEmpty()) screenStateManager.showConnectionError();
            else showConnectionError();*/
        }
    }

}
