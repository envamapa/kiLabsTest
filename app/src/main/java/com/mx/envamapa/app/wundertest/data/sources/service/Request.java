package com.mx.envamapa.app.wundertest.data.sources.service;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.commons.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.mx.envamapa.app.wundertest.commons.Constants.GET_IMAGES;
import static com.mx.envamapa.app.wundertest.commons.Constants.URL_SEARCH;

/**
 * Created by enya on 17/09/18.
 */

public class Request {

    private VolleyHelper volley;
    protected RequestQueue fRequestQueue;
    private static Request requestInstance;

    /**
     * Factory de Request
     *
     * @return requestInstance
     */
    public static Request getRequestInstance() {
        if (requestInstance == null) {
            requestInstance = new Request();
        }
        return requestInstance;
    }

    /**
     * INTERFACE DE RESPUESTA DE SERVICIO
     */
    public interface serviceCallStatus {
        void onSuccess(JSONObject jsonObject);

        void onFailed(String mensaje);
    }

    public void requestImages(int page, final Context context, final serviceCallStatus serviceCallStatus) {
        String url = GET_IMAGES + URL_SEARCH + page;
        doRequestGet(context, url, serviceCallStatus);
    }

    private void doRequestGet(final Context context, String url, final serviceCallStatus serviceCallStatus) {
        Utils.printLogInfo("URL: " + url, true, false);
        if (Utils.isOnline(context)) {
            final JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Utils.printLogInfo(jsonObject.toString(), true, true);
                    serviceCallStatus.onSuccess(jsonObject);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Utils.printLogError(error.toString(), true, false);
                    if (error instanceof TimeoutError) {
                        serviceCallStatus.onFailed(context.getString(R.string.exceeded));
                        Utils.printLogError("Number of attempts exceeded: " + error.toString(), true, false);
                    } else if (error instanceof NoConnectionError) {
                        serviceCallStatus.onFailed(context.getString(R.string.check_internet));
                        Utils.printLogError("No internet connection: " + error.toString(), true, false);
                    } else if (error instanceof ServerError) {
                        serviceCallStatus.onFailed(context.getString(R.string.error_de_servidor_reintento));
                        Utils.printLogError("Server error: " + error.toString(), true, false);
                    } else {
                        serviceCallStatus.onFailed(context.getString(R.string.system_error));
                        Utils.printLogError("System error: " + error.toString(), true, false);
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Accept", "application/json");
                    return params;
                }
            };
            volley = VolleyHelper.getInstance(context);
            fRequestQueue = volley.getHttpRequestQueue();
            if (fRequestQueue == null) {
                fRequestQueue = volley.getHttpRequestQueue();
            }
            request.setRetryPolicy(new DefaultRetryPolicy(
                    Constants.TIMEOUT, Constants.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            );
            request.setTag(url);

            fRequestQueue.add(request);
        } else {
            Utils.printLogError("No internet", true, false);
            serviceCallStatus.onFailed(context.getString(R.string.check_internet));
        }
    }
}
