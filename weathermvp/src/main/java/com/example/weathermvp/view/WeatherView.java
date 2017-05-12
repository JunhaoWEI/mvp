package com.example.weathermvp.view;

import com.example.weathermvp.model.WeatherEntity;

/**
 * Created by weijunhao on 2017/5/10.
 * 输入城市名获取天气信息业务逻辑
 */

public interface WeatherView {

    //显示loading view
    void showLoadingView();

    //关闭loading view
    void hideLoadingView();

    //显示数据
    void showData(WeatherEntity weatherEntity);

}
