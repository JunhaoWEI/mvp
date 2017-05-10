package com.example.weathermvp.presenter;

import com.example.weathermvp.model.WeatherEntity;

/**
 * Created by weijunhao on 2017/5/10.
 * 此接口作用是连接Model
 */

public interface IMainPresenter {

    void LoadDataSuccess(WeatherEntity weatherEntity);

    void loadDataFailure();
}
