package com.example.a86155.day01text01;

import beans.FuliBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    //https://gank.io/api/data/福利/20/1
    public static final String Url = "http://gank.io/";
    @GET("api/data/福利/20/{page}")
    Observable<FuliBean> getDatas(@Path("page")int page);

}
