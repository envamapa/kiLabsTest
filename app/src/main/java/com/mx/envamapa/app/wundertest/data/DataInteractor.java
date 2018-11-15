package com.mx.envamapa.app.wundertest.data;

import android.content.Context;

import com.google.gson.Gson;
import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.sources.database.model.TablaCarModel;
import com.mx.envamapa.app.wundertest.data.sources.database.object.TablaCarObject;
import com.mx.envamapa.app.wundertest.data.sources.service.Request;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.RespCars;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.RespImages;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.RealmResults;

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
     * @param context
     * @param listener
     */
    public void getImagesRequest(int page, final Context context, final DataInteractorListener listener) {
        Request.getRequestInstance().requestImages(page, context, new Request.serviceCallStatus() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Utils.printLogInfo(jsonObject.toString(), true, true);
                        RespImages respCars = new Gson().fromJson(jsonObject.toString(), RespImages.class); // SERVICE
                        listener.onSuccess(respCars);
                    }

                    @Override
                    public void onFailed(String mensaje) {
                        listener.onError(mensaje);
                    }
                });
    }

}
