package com.example.mvp2.view;

import com.example.mvp2.model.WeatherEntity;

/**
 * Created by WEI JUNHAO on 2017/5/11.
 */

public interface IWeatherQueryView {
    void showLoadingView();

    void hideLoadingView();

    String getCityName();

    void displayWeatherData(WeatherEntity weatherEntity);

    void showFailedError();
}
