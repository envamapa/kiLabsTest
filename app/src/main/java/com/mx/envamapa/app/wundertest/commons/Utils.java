package com.mx.envamapa.app.wundertest.commons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

/**
 * Common functions
 * */
public class Utils {

    private static final String MIME_TYPE_SHARE = "text/plain";

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

    public static Snackbar createSnackbar(Activity activity, @StringRes int resId) {
        View root = activity.findViewById(android.R.id.content);
        return Snackbar.make(root, resId, Snackbar.LENGTH_LONG);
    }

    public static void setVectorBg(View target, @DrawableRes int drRes, @ColorRes int normalRes,
                                   @ColorRes int pressedRes) {
        int[][] states = new int[][]{
                new int[]{
                        android.R.attr.state_pressed},
                new int[]{

                }
        };
        int[] colors = new int[]{
                ContextCompat.getColor(target.getContext(), pressedRes),
                ContextCompat.getColor(target.getContext(), normalRes)
        };
        ColorStateList cl = new ColorStateList(states, colors);

        Drawable drawable = ContextCompat.getDrawable(target.getContext(), drRes);
        Drawable wrapped = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrapped, cl);
        target.setBackground(wrapped);
    }

    public static Intent createShareIntent(String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND)
                .setType(MIME_TYPE_SHARE)
                .putExtra(Intent.EXTRA_SUBJECT, subject)
                .putExtra(Intent.EXTRA_TEXT, text);
        return Intent.createChooser(intent, "Choose app");
    }
}
