package me.zhouzhuo810.magpietvdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.zhouzhuo810.magpietv.act.BaseActivity;
import me.zhouzhuo810.magpietv.utils.RandomUtil;
import me.zhouzhuo810.magpietv.utils.RxHelper;
import me.zhouzhuo810.magpietv.widget.ScrollListRecyclerView;
import me.zhouzhuo810.magpietvdemo.adapter.ScrollListAdapter;
import me.zhouzhuo810.magpietvdemo.entity.TestListEntity;

public class ScrollListActivity extends BaseActivity {
    
    private ScrollListRecyclerView<TestListEntity> mScrollListRecyclerView;
    private ScrollListAdapter mScrollListAdapter;
    private Disposable mInterval;
    
    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.activity_scroll_list;
    }
    
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mScrollListRecyclerView = findViewById(R.id.rv);
    }
    
    @Override
    public void initData() {
        mScrollListAdapter = new ScrollListAdapter(this, null);
        mScrollListRecyclerView.setAdapter(mScrollListAdapter);
        
        randomDataTask();
        
    }
    
    
    private void randomDataTask() {
        cancelDisposable(mInterval);
        mInterval = RxHelper.interval(5, TimeUnit.SECONDS, new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                List<TestListEntity> list = new ArrayList<>();
                for (int i = 0; i < RandomUtil.getRandomIntFromTo(20, 50); i++) {
                    list.add(new TestListEntity("姓名" + i, "男", "test@qq.com", RandomUtil.getRandomSixInt() + "", RandomUtil.getRandomColorBlackToWhite()));
                }
                mScrollListRecyclerView.setNewData(list, false);
            }
        });
    }
    
    
    @Override
    public void initEvent() {
    
    }
    
    @Override
    public boolean shouldNotInvokeInitMethods(Bundle savedInstanceState) {
        return false;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelDisposable(mInterval);
    }
}
