package com.example.a86155.day01text01.mvp.modle;

import android.util.Log;

import com.example.a86155.day01text01.ApiService;
import com.example.a86155.day01text01.base.BaseModel;

import java.util.List;

import beans.FuliBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModle extends BaseModel {

    public void getDatas(int page, final getResult getResult){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FuliBean> datas = apiService.getDatas(page);
        datas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        if(fuliBean!= null){
                            getResult.onSuccess(fuliBean.getResults());
                            Log.i("tag", "onNext"+fuliBean.getResults());
                        }else {
                            getResult.onDefault("无法获得数据");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getResult.onDefault(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
