package com.example.a86155.day01text01.base;

public abstract class BaseMvpActivity<V extends BaseView,P extends BasePresenter> extends BaseActivity{
    public P presenter;

    @Override
    protected void initMvp() {
        super.initMvp();
        presenter=initMvpPresenter();
        if(presenter!=null){
            presenter.bindView(initMvpView());
        }
    }

    protected abstract V initMvpView();

    protected abstract P initMvpPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.onDestroy();
            presenter=null;
        }
    }
}
