package com.mx.envamapa.app.wundertest.data.sources.service.respPhotos;

import java.util.List;

/**
 * Created by enya on 11/15/18.
 */

public class RespImages {

    private String stat;

    private Photos photos;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
