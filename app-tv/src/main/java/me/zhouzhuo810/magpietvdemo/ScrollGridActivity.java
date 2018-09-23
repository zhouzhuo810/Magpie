package me.zhouzhuo810.magpietvdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo810.magpietv.act.BaseActivity;
import me.zhouzhuo810.magpietv.widget.ScrollGridRecyclerView;
import me.zhouzhuo810.magpietv.widget.ScrollListRecyclerView;
import me.zhouzhuo810.magpietvdemo.adapter.ScrollListAdapter;
import me.zhouzhuo810.magpietvdemo.entity.TestListEntity;

public class ScrollGridActivity extends BaseActivity {

    private ScrollGridRecyclerView<TestListEntity> mScrollGridRecyclerView;
    private ScrollListAdapter mScrollListAdapter;

    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroll_grid;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mScrollGridRecyclerView = findViewById(R.id.rv);
    }

    @Override
    public void initData() {
        mScrollListAdapter = new ScrollListAdapter(this, null);
        mScrollGridRecyclerView.setAdapter(mScrollListAdapter);
        List<TestListEntity> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add(new TestListEntity("姓名" + i, "男", "test@qq.com", "10086"));
        }
        mScrollGridRecyclerView.setNewData(list);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean shouldNotInvokeInitMethods(Bundle savedInstanceState) {
        return false;
    }
}
