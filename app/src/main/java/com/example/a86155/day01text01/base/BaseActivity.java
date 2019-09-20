package com.example.a86155.day01text01.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        initListener();
        initMvp();
    }

    protected void initMvp(){

    };

    protected void initListener(){

    };

    protected void initData(){

    };

    protected void initView(){

    };

    protected abstract int getLayoutId();
}
