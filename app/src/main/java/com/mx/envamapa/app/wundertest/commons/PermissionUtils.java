package com.mx.envamapa.app.wundertest.commons;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.mx.envamapa.app.wundertest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by enya on 17/09/18.
 */

public class PermissionUtils {
    private Context context;
    private Activity activity;
    private PermissionResultCallback permissionResultCallback;
    private ArrayList<String> permissionList = new ArrayList<>();
    private ArrayList<String> listPermissionsNeeded = new ArrayList<>();
    private String dialogContent = "";
    private int reqCode;

    public PermissionUtils(final Activity activity, final Context context, final PermissionResultCallback callback) {
        this.context = context;
        this.activity = activity;
        permissionResultCallback = callback;
    }


    /**
     * Check the API Level & Permission
     *
     * @param permissions   Listado de permisos.
     * @param dialogContent Mensaje de explicacion de la necesidad del permiso.
     * @param requestCode   Codigo de respuesta para procesar.
     */

    public void checkPermission(final ArrayList<String> permissions, final String dialogContent, final int requestCode) {
        this.permissionList = permissions;
        this.dialogContent = dialogContent;
        this.reqCode = requestCode;

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkAndRequestPermissions(permissions, requestCode)) {
                permissionResultCallback.permissionGranted(requestCode);
            }
        } else {
            permissionResultCallback.permissionGranted(requestCode);
        }

    }


    /**
     * Check and request the Permissions
     *
     * @param permissions  Listado de permisos.
     * @param request_code Codigo de respuesta para procesar.
     * @return
     */

    private boolean checkAndRequestPermissions(final ArrayList<String> permissions, final int request_code) {

        if (permissions.size() > 0) {
            listPermissionsNeeded = new ArrayList<>();

            for (int i = 0; i < permissions.size(); i++) {
                int hasPermission = ContextCompat.checkSelfPermission(activity, permissions.get(i));

                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permissions.get(i));
                }

            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), request_code);
                return false;
            }
        }

        return true;
    }

    /**
     * @param requestCode  Codigo de respuesta para procesar.
     * @param permissions  Listado de permisos.
     * @param grantResults
     */
    public void onRequestPermissionsResult(final int requestCode, final String permissions[], final int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    Map<String, Integer> perms = new HashMap<>();

                    for (int i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], grantResults[i]);
                    }

                    final ArrayList<String> pending_permissions = new ArrayList<>();

                    for (int i = 0; i < listPermissionsNeeded.size(); i++) {
                        if (perms.get(listPermissionsNeeded.get(i)) != PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, listPermissionsNeeded.get(i)))
                                pending_permissions.add(listPermissionsNeeded.get(i));
                            else {
                                permissionResultCallback.neverAskAgain(reqCode);
                                Toast.makeText(activity, context.getString(R.string.msg_need_permission), Toast.LENGTH_LONG).show();
                                return;
                            }
                        }

                    }

                    if (pending_permissions.size() > 0) {
                        showMessageOKCancel(dialogContent,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, final int which) {

                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                checkPermission(permissionList, dialogContent, reqCode);
                                                break;
                                            case DialogInterface.BUTTON_NEGATIVE:
                                                if (permissionList.size() == pending_permissions.size())
                                                    permissionResultCallback.permissionDenied(reqCode);
                                                else
                                                    permissionResultCallback.partialPermissionGranted(reqCode, pending_permissions);
                                                break;
                                        }


                                    }
                                });

                    } else {
                        permissionResultCallback.permissionGranted(reqCode);
                    }
                }
                break;
        }
    }


    /**
     * Explain why the app needs permissions
     *
     * @param message    Mensaje que aparece si el usuario cancela el dialogo de peticion.
     * @param okListener Listener.
     */
    private void showMessageOKCancel(final String message, final DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.accept), okListener)
                .setNegativeButton(context.getString(R.string.cancel), okListener)
                .create()
                .show();
    }

    public interface PermissionResultCallback {
        void permissionGranted(final int requestCode);

        void partialPermissionGranted(final int requestCode, final ArrayList<String> grantedPermissions);

        void permissionDenied(final int requestCode);

        void neverAskAgain(int requestCode);
    }
}


