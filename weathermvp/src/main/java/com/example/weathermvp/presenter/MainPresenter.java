package com.example.weathermvp.presenter;

import com.example.weathermvp.model.MainModel;
import com.example.weathermvp.model.WeatherEntity;
import com.example.weathermvp.view.WeatherView;

/**
 * Created by weijunhao on 2017/5/10.
 */

public class MainPresenter implements Presenter<WeatherView>, IMainPresenter {
    private WeatherView mWeatherView;
    private MainModel mMainModel;

    public MainPresenter(WeatherView weatherView) {
        attachView(weatherView);
        mMainModel = new MainModel(this);
    }

    @Override
    public void attachView(WeatherView view) {
        mWeatherView = view;
    }

    @Override
    public void detachView() {
        mWeatherView = null;
    }

    @Override
    public void LoadDataSuccess(WeatherEntity weatherEntity) {
        mWeatherView.showData(weatherEntity);
        mWeatherView.hideLoadingView();
    }

    @Override
    public void loadDataFailure() {
        mWeatherView.hideLoadingView();
    }

    public void loadData() {
        mWeatherView.showLoadingView();
        mMainModel.loadData();
    }
}
