package com.mx.envamapa.app.wundertest.views.presenter.listPresenter;

import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.domain.manager.TaskManager;
import com.mx.envamapa.app.wundertest.domain.task.Get15CarsTask;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.list.CarListInterface;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by enya on 17/09/18.
 */

public class CarListPresenter implements CarListPresenterInterface {

    private CarListInterface viewInterface;
    private Application application;

    @Inject
    TaskManager taskManager;

    public CarListPresenter(CarListInterface viewInterface, Application application) {
        this.viewInterface = viewInterface;
        this.application = application;
        application.getAppComponent().inject(this);
    }

    /**
     * Get 15 elements of the list
     * @param initSearchValue
     */
    @Override
    public void get15Cars(int initSearchValue){
        taskManager.execute(new Get15CarsTask(initSearchValue, application.getApplicationContext(), new Get15CarsTask.Get15CarsTaskListener() {
                @Override
                public void onFinishTask(List<Car> carList) {
                    viewInterface.reloadList(carList);
                }

                @Override
                public void onErrorTask(String code, String message) {
                    /*viewInterface.showMessage(message);
                    viewInterface.error();*/
                }
            }), null);
    }


}
