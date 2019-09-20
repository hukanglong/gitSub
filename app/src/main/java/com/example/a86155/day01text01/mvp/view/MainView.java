package com.example.a86155.day01text01.mvp.view;

import com.example.a86155.day01text01.base.BaseView;

import java.util.List;

import beans.FuliBean;

public interface MainView extends BaseView<List<FuliBean.ResultsBean> ,String > {
    @Override
    void onFile(String s);

    @Override
    void onSuccess(List<FuliBean.ResultsBean> resultsBeans);
}
