package com.example.mvp2.presenter;

import com.example.mvp2.model.WeatherEntity;

/**
 * Created by WEI JUNHAO on 2017/5/11.
 */

public interface IWeatherQueryPresenter {
    void loadDataSuccess(WeatherEntity weatherEntity);

    void loadDataFailure();

    String getCityName();
}
