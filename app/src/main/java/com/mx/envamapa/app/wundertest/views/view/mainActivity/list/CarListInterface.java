package com.mx.envamapa.app.wundertest.views.view.mainActivity.list;

import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;

import java.util.List;

/**
 * Created by enya on 17/09/18.
 */

public interface CarListInterface {
    void reloadList(List<Car> carList);
}
