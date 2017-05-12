package com.example.weathermvp.model;

import com.example.weathermvp.presenter.IMainPresenter;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by weijunhao on 2017/5/10.
 */

public class MainModel {
    IMainPresenter mIMainPresenter;
    WeatherEntity mWeatherEntity;

    public MainModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://wthrcdn.etouch.cn/weather_mini?city=上海")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mIMainPresenter.loadDataFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    mWeatherEntity = new Gson().fromJson(response.body().string(), WeatherEntity.class);
                    mIMainPresenter.LoadDataSuccess(mWeatherEntity);
                }
            }
        });
    }
}
