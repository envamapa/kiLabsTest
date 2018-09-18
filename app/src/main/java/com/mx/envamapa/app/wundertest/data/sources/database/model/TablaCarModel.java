package com.mx.envamapa.app.wundertest.data.sources.database.model;

import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.data.sources.database.object.TablaCarObject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by enya on 17/09/18.
 */

public class TablaCarModel {

    private String _ID = "id";
    private String _ADDRESS = "address";
    private String _COORDINATES = "coordinates";
    private String _ENGINETYPE = "engineType";
    private String _EXTERIOR = "exterior";
    private String _FUEL = "fuel";
    private String _INTERIOR = "interior";
    private String _NAME = "name";
    private String _VIN = "vin";

    TablaCarObject tablaCarObject;
    TablaCarObject[] tablaCarObjects;
    Realm realm;

    public TablaCarModel(TablaCarObject tablaCarObject) {
        this.tablaCarObject = tablaCarObject;
        realm = Realm.getDefaultInstance();
    }

    public TablaCarModel(TablaCarObject[] tablaCarObjects) {
        this.tablaCarObjects = tablaCarObjects;
        realm = Realm.getDefaultInstance();
    }

    /**
     * Inserts an object into the database
     *
     * @param car
     * */
    public void insert(TablaCarObject car){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(car);
        realm.commitTransaction();
    }

    /**
     * Get specific count of cars
     * */
    public RealmResults<TablaCarObject> getSpecificAmount(int amount){
        return realm.where(TablaCarObject.class)
                .between(_ID, amount, (amount+ Constants.ITEM_COUNT)).findAll();
    }

    /**
     * Get all results
     * */
    public RealmResults<TablaCarObject> getAll(){
        return realm.where(TablaCarObject.class).findAll();
    }
}
