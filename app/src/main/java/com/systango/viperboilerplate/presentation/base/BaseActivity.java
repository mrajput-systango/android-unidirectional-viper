package com.systango.viperboilerplate.presentation.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected String TAG = getClass().getSimpleName();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
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