package com.systango.viperboilerplate.presentation.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {

    private final static String TAG_SCREEN = "ScreenEvent";
    protected Context context;
    protected String TAG = getClass().getSimpleName();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_SCREEN, TAG + " started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_SCREEN, TAG + " stopped");
    }

    protected void showProgress(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    protected void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}