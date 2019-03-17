package com.systango.viperboilerplate.presentation.base;

/**
 * Created by Mohit Rajput on 12/3/19.
 * Parent view of all other views of VIPER
 */
public interface BaseView {
    void showProgress();

    void hideProgress();

    void showError(String errorMessage);
}
