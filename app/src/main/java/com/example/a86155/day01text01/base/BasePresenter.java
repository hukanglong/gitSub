package com.example.a86155.day01text01.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView,M extends BaseModel> {
    public V view;
    private ArrayList<M> mModels=new ArrayList<>();
    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bindView(V view) {
        this.view = view;
    }

    public void addModel(M m){
        mModels.add(m);
    }
    public void onDestroy(){
        view=null;
        if(mModels.size()>0){
            for(M m:mModels){
                m.onDestroy();
            }
            mModels.clear();
        }
    }
}
