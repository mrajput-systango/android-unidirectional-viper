package com.systango.viperboilerplate.common.configurator;

import com.systango.viperboilerplate.presentation.base.BaseView;

/**
 * Created by Mohit Rajput on 17/3/19.
 * Parent configurator of VIPER layers
 */
public interface BaseConfigurator<T extends BaseView> {
    public void configure(T view);
}
