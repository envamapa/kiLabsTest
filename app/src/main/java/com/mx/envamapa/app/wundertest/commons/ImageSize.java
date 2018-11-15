package com.mx.envamapa.app.wundertest.commons;

/**
 * Created by enya on 11/15/18.
 */

public enum ImageSize {

    MEDIUM,
    LARGE;

    public String getValue() {
        if (this == MEDIUM) return "z";
        return "h";
    }

}