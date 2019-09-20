package com.example.a86155.day01text01.mvp.presenter;

import com.example.a86155.day01text01.base.BasePresenter;
import com.example.a86155.day01text01.mvp.modle.MainModle;
import com.example.a86155.day01text01.mvp.view.MainView;

import java.util.List;

import beans.FuliBean;

public class MainPresenter extends BasePresenter implements MainModle.getResult {

    private MainView mMainView;
    private final MainModle mMainModle;

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
        mMainModle = new MainModle();
    }

    public void getDatas(int page){
        mMainModle.getDatas(page,this);
    }

    @Override
    public void onSuccess(List<FuliBean.ResultsBean> list) {
        mMainView.getFuliBean(list);
    }

    @Override
    public void onDefault(String msg) {
        mMainView.setMsg(msg);
    }

    @Override
    protected void initModel() {

    }
}
