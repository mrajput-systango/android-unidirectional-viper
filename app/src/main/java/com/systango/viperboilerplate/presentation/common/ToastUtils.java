package com.systango.viperboilerplate.presentation.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mohit Rajput on 5/7/17.
 * This class provides methods to show toast messages
 */

public class ToastUtils {
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(Context context, int resourceId) {
        Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, int resourceId) {
        Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_LONG).show();
    }
}
