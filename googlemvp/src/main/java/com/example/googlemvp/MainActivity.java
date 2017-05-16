package com.example.googlemvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.googlemvp.contract.WeatherContract;
import com.example.googlemvp.model.WeatherEntity;
import com.example.googlemvp.presenter.WeatherPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        WeatherContract.View {

    private TextView mTextView;
    private EditText mEditText;
    private ProgressBar mProgressBar;
    private Button mButton;

    //private WeatherContract.Presenter mPresenter;
    @Inject
    WeatherContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mButton = (Button) findViewById(R.id.button);

        //new WeatherPresenter(this);
      //  DaggerMainComponent.builder().build().inject(this);



        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mPresenter.start();
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayWeather(WeatherEntity weatherEntity) {
        mTextView.setText(weatherEntity.getData().getGanmao());
    }

    @Override
    public String getCityname() {
        return "上海";
    }
}
