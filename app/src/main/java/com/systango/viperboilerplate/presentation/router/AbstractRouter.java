package com.systango.viperboilerplate.presentation.router;

import android.app.Activity;

/**
 * Created by Mohit Rajput on 12/3/19.
 * Parent router which mainly initializes activity instance
 */
public abstract class AbstractRouter {
    private Activity activity;

    public AbstractRouter(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
