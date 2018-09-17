package com.mx.envamapa.app.wundertest.domain.task;

import android.content.Context;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.DataInteractor;
import com.mx.envamapa.app.wundertest.domain.taskInterface.Task;

/**
 * Created by enya on 17/09/18.
 */

public class RequestCarsTask implements Task {

    Context context;
    RequestCarsTaskListener listener;
    DataInteractor dataInteractor;

    public interface RequestCarsTaskListener{
        void onFinishTask(String fillComplete);
        void onErrorTask(String code, String message);
    }

    public RequestCarsTask(Context context, RequestCarsTaskListener listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void execute(Object object) {
        dataInteractor = new DataInteractor();
        dataInteractor.getCars(context, new DataInteractor.DataInteractorListener() {
            @Override
            public void onSuccess(Object object) {
                listener.onFinishTask(object.toString());
            }

            @Override
            public void onError(String message) {
                Utils.printLogError(message, true, false);
                listener.onErrorTask(null, message == null ? context.getString(R.string.system_error) : message);
            }
        });
    }
}
