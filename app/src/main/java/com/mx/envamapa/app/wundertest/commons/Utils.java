package com.mx.envamapa.app.wundertest.commons;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
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

    /**
     * @param ic_image = R.drawable.ic_image_24dp
     * @param ic_broke = R.drawable.ic_broken_image_24dp
     * */
    public static void bindImage(String url, ImageView target, boolean centerCrop, int ic_image, int ic_broke) {
        Drawable drawable = ContextCompat.getDrawable(target.getContext(), ic_image);
        DrawableRequestBuilder<String> builder = Glide.with(Application.getContext())
                .load(url)
                .error(ic_broke)
                .placeholder(drawable)
                .crossFade();
        if (centerCrop) builder.centerCrop();
        builder.into(target);
    }
}
