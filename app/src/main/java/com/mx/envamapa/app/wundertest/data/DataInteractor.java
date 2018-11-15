package com.mx.envamapa.app.wundertest.data;

import android.content.Context;

import com.google.gson.Gson;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.sources.service.Request;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.RespImages;

import org.json.JSONObject;

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
