package com.systango.viperboilerplate.presentation.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mohit Rajput on 13/3/19.
 * TODO: Insert javadoc information here
 */
public abstract class BasePresenter {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void clearDisposables() {
        compositeDisposable.clear();
    }

    void onDestroy() {
        clearDisposables();
    }
}
