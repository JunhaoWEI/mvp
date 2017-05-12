package com.example.weathermvp.presenter;


/**
 * Created by weijunhao on 2017/5/10.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
