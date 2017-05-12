package com.example.googlemvp.presenter;

import android.os.Handler;

import com.example.googlemvp.contract.WeatherContract;
import com.example.googlemvp.model.WeatherEntity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WEI JUNHAO on 2017/5/11.
 */

public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View mWeatherView;
    private Handler mHandler = new Handler();

    public WeatherPresenter(WeatherContract.View view) {
        mWeatherView = view;
        mWeatherView.setPresenter(this);
    }

    @Override
    public void start() {
        loadWeatherData();
    }

    @Override
    public void loadWeatherData() {
        mWeatherView.showLoading();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://wthrcdn.etouch.cn/weather_mini?city=" + mWeatherView.getCityname())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWeatherView.dismissLoading();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final WeatherEntity weatherEntity = new Gson().fromJson(response.body().string(),
                        WeatherEntity.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWeatherView.displayWeather(weatherEntity);
                        mWeatherView.dismissLoading();
                    }
                });
            }
        });

    }
}
