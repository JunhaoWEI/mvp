package com.example.mvp2.presenter;

import com.example.mvp2.model.WeatherEntity;
import com.example.mvp2.model.biz.WeatherBiz;
import com.example.mvp2.view.IWeatherQueryView;

/**
 * Created by WEI JUNHAO on 2017/5/11.
 */

public class WeatherQueryPresenter implements IWeatherQueryPresenter, Presenter<IWeatherQueryView> {
    private IWeatherQueryView mIWeatherQueryView;
    private WeatherBiz mWeatherBiz;

    public WeatherQueryPresenter(IWeatherQueryView iWeatherQueryView) {
        attachView(iWeatherQueryView);
        mWeatherBiz = new WeatherBiz(this);
    }

    @Override
    public void loadDataSuccess(WeatherEntity weatherEntity) {
        mIWeatherQueryView.displayWeatherData(weatherEntity);
        mIWeatherQueryView.hideLoadingView();
    }

    @Override
    public void loadDataFailure() {
        mIWeatherQueryView.hideLoadingView();
    }

    @Override
    public String getCityName() {
        return mIWeatherQueryView.getCityName();
    }

    @Override
    public void attachView(IWeatherQueryView view) {
        mIWeatherQueryView = view;
    }

    @Override
    public void detachView() {
        mIWeatherQueryView = null;
    }

    public void loadData() {
        mIWeatherQueryView.showLoadingView();
        mWeatherBiz.loadData();
    }
}
