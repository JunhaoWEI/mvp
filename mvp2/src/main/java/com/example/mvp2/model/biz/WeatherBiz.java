package com.example.mvp2.model.biz;

import android.os.Handler;
import android.util.Log;

import com.example.mvp2.model.WeatherEntity;
import com.example.mvp2.presenter.IWeatherQueryPresenter;
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

public class WeatherBiz {
    IWeatherQueryPresenter mIWeatherQueryPresenter;
    Handler handler = new Handler();

    public WeatherBiz(IWeatherQueryPresenter weatherQueryPresenter) {
        mIWeatherQueryPresenter = weatherQueryPresenter;
    }

    public void loadData() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://wthrcdn.etouch.cn/weather_mini?city=" + mIWeatherQueryPresenter.getCityName())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mIWeatherQueryPresenter.loadDataFailure();
                Log.d("wjh", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("wjh", "onResponse: ");
                Log.d("wjh", "onResponse: " + response.body().string());
                if (response.isSuccessful()) {
                    final WeatherEntity weatherEntity = new Gson()
                            .fromJson(response.body().string(), WeatherEntity.class);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mIWeatherQueryPresenter.loadDataSuccess(weatherEntity);
                        }
                    });
                }

            }
        });
    }
}
