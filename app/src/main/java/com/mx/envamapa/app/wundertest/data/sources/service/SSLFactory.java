package com.mx.envamapa.app.wundertest.data.sources.service;

import android.content.Context;

import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.mx.envamapa.app.wundertest.commons.Utils;

import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by enya on 17/09/18.
 */

public class SSLFactory {

    private StringBuilder camposConError;
    private Context context;
    private HurlStack hurlStack;
    //private Common common;

    public SSLFactory(Context context) {
        this.context = context;
    }

    public HttpStack newSslSocketFactory() {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpStack httpStack = new HttpClientStack(httpClient);

            return httpStack;


        } catch (Exception e) {
            Utils.printLogError(e.getMessage(), true, false);
            throw new AssertionError(e);
        }
    }

}
