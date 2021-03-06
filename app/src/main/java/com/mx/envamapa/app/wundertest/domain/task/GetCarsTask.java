package com.mx.envamapa.app.wundertest.domain.task;

import android.content.Context;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.DataInteractor;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.domain.taskInterface.Task;

import java.util.List;

/**
 * Created by enya on 17/09/18.
 */

public class GetCarsTask implements Task {

    private Context context;
    private GetCarsTaskListener listener;
    private DataInteractor dataInteractor;
    private int initSearchValue;

    public interface GetCarsTaskListener{
        void onFinishTask(List<Car> listCars);
        void onErrorTask(String code, String message);
    }

    public GetCarsTask(Context context, GetCarsTaskListener listener){
        this.context = context;
        this.listener = listener;
        this.initSearchValue = initSearchValue;
    }

    @Override
    public void execute(Object object) {
        dataInteractor = new DataInteractor();
        dataInteractor.getCarsDB(context, new DataInteractor.DataInteractorListener() {
            @Override
            public void onSuccess(Object object) {
                listener.onFinishTask((List<Car>) object);
            }

            @Override
            public void onError(String message) {
                Utils.printLogError(message, true, false);
                listener.onErrorTask(null, message == null ? context.getString(R.string.system_error) : message);
            }
        });
    }
}
