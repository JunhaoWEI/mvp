package com.example.weathermvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.weathermvp.model.WeatherEntity;
import com.example.weathermvp.presenter.MainPresenter;
import com.example.weathermvp.view.WeatherView;

public class MainActivity extends AppCompatActivity implements WeatherView, View.OnClickListener {

    private ProgressBar mProgressBar;
    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mButton = (Button) findViewById(R.id.btn_search);
        mEditText = (EditText) findViewById(R.id.et_city_name);
        mTextView = (TextView) findViewById(R.id.textView);
        mMainPresenter = new MainPresenter(this);

        mButton.setOnClickListener(this);

    }


    //防止presenter引起内存泄露
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void showLoadingView() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void showData(final WeatherEntity weatherEntity) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(weatherEntity.getData().getGanmao());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                mMainPresenter.loadData();
                break;
            default:
                break;
        }
    }
}
