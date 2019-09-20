package com.example.a86155.day01text01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a86155.day01text01.mvp.presenter.MainPresenter;
import com.example.a86155.day01text01.mvp.view.MainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import adapters.MainAdapter;
import adapters.PageAdapter;
import beans.FuliBean;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    @BindView(R.id.vp)
    ViewPager mVp;
    private int mPage = 1;
    private List<FuliBean.ResultsBean> mList = new ArrayList<>();

    @BindView(R.id.rec)
    RecyclerView mRec;
    private MainAdapter mMainAdapter;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getDatas(mPage);
        initRec();
    }

    private void initRec() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRec.setLayoutManager(manager);
        mMainAdapter = new MainAdapter(this, mList);
        mRec.setAdapter(mMainAdapter);

        mSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                mMainPresenter.getDatas(mPage);
            }
        });
        mSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mList.clear();
                mPage = 1;
                mMainPresenter.getDatas(mPage);
            }
        });



        mMainAdapter.setMyOnclick(new MainAdapter.MyOnclick() {
            @Override
            public void onclick(int position) {
                mVp.setVisibility(View.VISIBLE);
                mSmart.setVisibility(View.INVISIBLE);
                mVp.setCurrentItem(position);
            }
        });
    }

    @Override
    public void getFuliBean(List<FuliBean.ResultsBean> list) {
        mList.addAll(list);
        mMainAdapter.notifyDataSetChanged();
        mSmart.finishRefresh();
        mSmart.finishLoadMore();

        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_page, null);
            ImageView img_page = view.findViewById(R.id.img_page);
            TextView num_page = view.findViewById(R.id.num_page);
            num_page.setText(i+1+" / "+mList.size());
            Glide.with(this).load(mList.get(i).getUrl()).into(img_page);
            views.add(view);
        }
        PageAdapter pageAdapter = new PageAdapter(this, views);
        mVp.setAdapter(pageAdapter);
    }

    @Override
    public void setMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
