package me.zhouzhuo810.magpiedemo.fgm;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import me.zhouzhuo810.magpie.ui.fgm.BaseFragment;
import me.zhouzhuo810.magpiedemo.R;

public class TestFragmentOne extends BaseFragment {

    private TextView tvTab;
    private boolean loadFinish;

    @Override
    public int getLayoutId() {
        return R.layout.fgm_test;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvTab = (TextView) findViewById(R.id.tv_tab);
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            int index = getArguments().getInt("index");
            tvTab.setText("Tab" + index);
        }
    }

    @Override
    public void lazyLoadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvTab.setText("i'm new one"+System.currentTimeMillis());
                loadFinish = true;
            }
        }, 100);
    }

    @Override
    public boolean needNotLazyLoadData() {
        return loadFinish;
    }

    @Override
    public void initEvent() {

    }

}
