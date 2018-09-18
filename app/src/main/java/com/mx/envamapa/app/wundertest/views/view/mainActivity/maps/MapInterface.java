package com.mx.envamapa.app.wundertest.views.view.mainActivity.maps;

import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;

import java.util.List;

/**
 * Created by enya on 18/09/18.
 */

public interface MapInterface {
    void initMap(List<Car> carList);
}
