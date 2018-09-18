package com.mx.envamapa.app.wundertest.commons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mx.envamapa.app.wundertest.R;
import com.orhanobut.logger.Logger;

/**
 * Common functions
 * */
public class Utils {

    /**
     * PRINT LOG AS DEBUG
     *
     * @param message    message to show on log
     * @param jsonMobe   true: if it's a json <br> false: if it's not a json
     * @param prettyMode show in pretty mode
     */
    public static void printLogDebug(String message, boolean prettyMode, boolean jsonMobe) {
        if (Constants.ALLOWING_LOGS) {
            if (jsonMobe) {
                Logger.json(message);
            } else {
                if (prettyMode) {
                    Logger.d(message);
                } else {
                    Log.d(Constants.TAG_LOG, message);
                }
            }
        }
    }


    /**
     * PRINT LOG AS INFO
     *
     * @param message    message to show on log
     * @param jsonMobe   true: if it's a json <br> false: if it's not a json
     * @param prettyMode show in pretty mode
     */
    public static void printLogInfo(final String message, final boolean prettyMode, final boolean jsonMobe) {
        if (Constants.ALLOWING_LOGS) {
            if (jsonMobe) {
                Logger.json(message);
            } else {
                if (prettyMode) {
                    Logger.i(message);
                } else {
                    Log.i(Constants.TAG_LOG, message);
                }
            }
        }
    }

    /**
     * PRINT LOG AS ERROR
     *
     * @param message    message to show on log
     * @param jsonMobe   true: if it's a json <br> false: if it's not a json
     * @param prettyMode show in pretty mode
     */
    public static void printLogError(final String message, final boolean prettyMode, final boolean jsonMobe) {
        if (Constants.ALLOWING_LOGS) {
            if (jsonMobe) {
                Logger.json(message);
            } else {
                if (prettyMode) {
                    Logger.e(message);
                } else {
                    Log.e(Constants.TAG_LOG, message);
                }
            }
        }
    }

    /**
     * VERIFY IF THERE'S AN INTERNET CONNECTION
     *
     * @param context
     * @return true if is online
     */
    public static boolean isOnline(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static int getAnimation(String result) {
        if(result.equals(Constants.GOOD)){
            return R.raw.good;
        }else if(result.equals(Constants.UNACCEPTABLE)){
            return R.raw.unacceptable;
        }else{
            return R.raw.like;
        }
    }

    public static Double[] stringToDoubleArray(String coordinates) {
        coordinates = coordinates.replace("[","").replace("]","");
        String[] coordinatesArray = coordinates.split(",");

        Double[] coords = new Double[coordinatesArray.length];
        for(int i = 0; i< coordinatesArray.length; i++){
            String coord = coordinatesArray[i];
            coords[i] = Double.parseDouble(coord);
        }

        return coords;
    }

    public static BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
