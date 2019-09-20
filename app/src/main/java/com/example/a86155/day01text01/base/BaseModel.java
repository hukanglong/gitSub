package com.example.a86155.day01text01.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    private CompositeDisposable mCompositeDisposable=null;

    public void addDisposable(Disposable disposable){
        if(mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void onDestroy(){
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }
}
