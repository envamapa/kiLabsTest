package com.mx.envamapa.app.wundertest.views.presenter.mapPresenter;

import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.domain.manager.TaskManager;
import com.mx.envamapa.app.wundertest.domain.task.GetCarsTask;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.list.CarListInterface;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.maps.MapInterface;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by enya on 17/09/18.
 */

public class MapPresenter implements MapPresenterInterface {

    private MapInterface viewInterface;
    private Application application;

    @Inject
    TaskManager taskManager;

    public MapPresenter(MapInterface viewInterface, Application application) {
        this.viewInterface = viewInterface;
        this.application = application;
        application.getAppComponent().inject(this);
    }

    /**
     * Get elements of the list
     */
    @Override
    public void getCars(){
        taskManager.execute(new GetCarsTask(application.getApplicationContext(), new GetCarsTask.GetCarsTaskListener() {
                @Override
                public void onFinishTask(List<Car> carList) {
                    viewInterface.initMap(carList);
                }

                @Override
                public void onErrorTask(String code, String message) {
                    /*viewInterface.showMessage(message);
                    viewInterface.error();*/
                }
            }), null);
    }


}
