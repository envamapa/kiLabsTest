package com.mx.envamapa.app.wundertest.data.sources.service;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by enya on 17/09/18.
 */

public class VolleyHelper {

    private static VolleyHelper mVolleyS = null;

    //Este objeto es la cola que usará la aplicación
    private RequestQueue mRequestQueue;
    private RequestQueue httpsmRequestQueue;

    private VolleyHelper(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        SSLFactory sslFactory = new SSLFactory(context);
        HttpStack httpStack = sslFactory.newSslSocketFactory();
        httpsmRequestQueue = Volley.newRequestQueue(context,httpStack);
    }
    public static VolleyHelper getInstance(Context context) {
        if (mVolleyS == null) {
            mVolleyS = new VolleyHelper(context);
        }
        return mVolleyS;
    }

    public RequestQueue getRequestQueue(String url) {
        return mRequestQueue;
    }

    public RequestQueue getHttpRequestQueue() {
        return httpsmRequestQueue;
    }

}
