package com.example.googlemvp.contract;

import com.example.googlemvp.model.WeatherEntity;
import com.example.googlemvp.presenter.BasePresenter;

/**
 * Created by WEI JUNHAO on 2017/5/11.
 */

public interface WeatherContract {
    interface Presenter extends BasePresenter {
        void loadWeatherData();
    }

    interface View extends BaseView<Presenter> {
        void showLoading();

        void dismissLoading();

        void displayWeather(WeatherEntity weatherEntity);

        String getCityname();

    }
}
