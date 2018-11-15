package com.mx.envamapa.app.wundertest.domain.task;

import android.content.Context;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.DataInteractor;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photos;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.RespImages;
import com.mx.envamapa.app.wundertest.domain.taskInterface.Task;

import java.util.List;

/**
 * Created by enya on 11/15/18.
 */

public class GetImagesTask implements Task {

    private Context context;
    private GetImagesTaskListener listener;
    private DataInteractor dataInteractor;
    private int initSearchValue;

    public interface GetImagesTaskListener{
        void onFinishTask(RespImages respImages);
        void onErrorTask(String code, String message);
    }

    public GetImagesTask(int initSearchValue, Context context, GetImagesTaskListener listener){
        this.context = context;
        this.listener = listener;
        this.initSearchValue = initSearchValue;
    }

    @Override
    public void execute(Object object) {
        dataInteractor = new DataInteractor();
        dataInteractor.getImagesRequest(initSearchValue, context, new DataInteractor.DataInteractorListener() {
            @Override
            public void onSuccess(Object object) {
                listener.onFinishTask((RespImages) object);
            }

            @Override
            public void onError(String message) {
                Utils.printLogError(message, true, false);
                listener.onErrorTask(null, message == null ? context.getString(R.string.system_error) : message);
            }
        });
    }
    
}
