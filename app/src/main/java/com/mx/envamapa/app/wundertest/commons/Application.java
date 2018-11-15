package com.mx.envamapa.app.wundertest.commons;

import android.content.Context;

import com.mx.envamapa.app.wundertest.commons.di.component.AppComponent;
import com.mx.envamapa.app.wundertest.commons.di.component.DaggerAppComponent;
import com.mx.envamapa.app.wundertest.commons.di.module.AppModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {

    private AppComponent mAppComponent;
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        createDaggerInjections();
        Logger.addLogAdapter(new AndroidLogAdapter());

    }

    /**
     * CREAR EL MODULO DE INYECCION DE DAGGER2
     */
    private void createDaggerInjections(){
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);

    }

    public AppComponent getAppComponent(){
        return this.mAppComponent;
    }

    public static Context getContext() {
        return instance;
    }

    public static void setInstance(Context instance) {
        Application.instance = instance;
    }
}
