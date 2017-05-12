package com.example.mvp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mvp2.model.WeatherEntity;
import com.example.mvp2.presenter.WeatherQueryPresenter;
import com.example.mvp2.view.IWeatherQueryView;

public class MainActivity extends AppCompatActivity implements IWeatherQueryView, View.OnClickListener {
    private TextView mTextView;
    private EditText mEditText;
    private ProgressBar mProgressBar;
    private Button mButton;

    private WeatherQueryPresenter mWeatherQueryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mButton = (Button) findViewById(R.id.button);

        mWeatherQueryPresenter = new WeatherQueryPresenter(this);

        mButton.setOnClickListener(this);
    }

    @Override
    public void showLoadingView() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public String getCityName() {
        return mEditText.getText().toString();
    }

    @Override
    public void displayWeatherData(WeatherEntity weatherEntity) {
        mTextView.setText(weatherEntity.getData().getGanmao());
    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mWeatherQueryPresenter.loadData();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mWeatherQueryPresenter.detachView();
        super.onDestroy();
    }
}
