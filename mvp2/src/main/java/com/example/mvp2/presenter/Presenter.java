package com.example.mvp2.presenter;

/**
 * Created by WEI JUNHAO on 2017/5/11.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
