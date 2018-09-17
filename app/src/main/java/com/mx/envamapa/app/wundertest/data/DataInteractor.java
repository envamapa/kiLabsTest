package com.mx.envamapa.app.wundertest.data;

import android.content.Context;

import com.google.gson.Gson;
import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.data.sources.database.model.TablaCarModel;
import com.mx.envamapa.app.wundertest.data.sources.database.object.TablaCarObject;
import com.mx.envamapa.app.wundertest.data.sources.service.Request;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.RespCars;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by enya on 17/09/18.
 */

public class DataInteractor {

    public DataInteractor() {
    }

    public interface DataInteractorListener {
        void onSuccess(Object object);

        void onError(String message);
    }

    /**
     * Get list of cars from the service
     *
     * @param context
     * @param listener
     */
    public void getCars(final Context context, final DataInteractorListener listener) {
        Request.getRequestInstance().requestCarts(context, new Request.serviceCallStatus() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        RespCars respCars = new Gson().fromJson(jsonObject.toString(), RespCars.class); // SERVICE
                        saveCars(respCars.getPlacemarks(), listener, context);
                    }

                    @Override
                    public void onFailed(String mensaje) {
                        listener.onError(mensaje);
                    }
                });
    }

    /**
     * Save cars received by the service
     *
     * @param placemarkers
     * */
    private void saveCars(List<Car> placemarkers, DataInteractorListener listener, Context context){
        if(placemarkers != null && placemarkers.size() > 0){
            for(int i = 0 ; i < placemarkers.size() ; i++){
                Car car = placemarkers.get(i);

                TablaCarObject carObject = new TablaCarObject();
                carObject.setId(i);
                carObject.setAddress(car.getAddress());
                carObject.setCoordinates(Arrays.toString(car.getCoordinates()));
                carObject.setEngineType(car.getEngineType());
                carObject.setExterior(car.getExterior());
                carObject.setFuel(car.getFuel());
                carObject.setInterior(car.getInterior());
                carObject.setName(car.getName());
                carObject.setVin(car.getVin());

                TablaCarModel carModel = new TablaCarModel(carObject);
                carModel.insert(carObject);
            }
            listener.onSuccess(context.getResources().getString(R.string.saved));
        }else{
            listener.onError(context.getResources().getString(R.string.no_elements));
        }
    }

}
