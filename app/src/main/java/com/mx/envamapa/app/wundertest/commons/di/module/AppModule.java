package com.mx.envamapa.app.wundertest.commons.di.module;

import android.content.Context;

import com.mx.envamapa.app.wundertest.commons.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Context context(){
        return this.context;
    }

    @Provides
    @Singleton
    public Application application(){
        return (Application) context;
    }

    /*@Provides
    @Singleton
    public TaskManager taskManager(){
        return new TaskManager();
    }*/

}
