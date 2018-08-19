package me.zhouzhuo810.magpiedemo;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.zhouzhuo810.magpie.mvp.presenter.IBasePresenter;
import me.zhouzhuo810.magpie.mvp.ui.activity.BaseMVPActivity;
import me.zhouzhuo810.magpie.utils.CollectionUtil;

public class MainActivity extends BaseMVPActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public View getDecorView() {
        return getWindow().getDecorView();
    }

    @Override
    public IBasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((20 - i) + "#");
        }
        Collections.shuffle(list);
        Log.e(TAG, "排序前：\n" + list.toString());
        CollectionUtil.sort(list, false);
        Log.e(TAG, "排序后：\n" + list.toString());
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void closeAct() {

    }

    @Override
    public void closeAct(boolean defaultBack) {

    }

    @Override
    public void closeAllAct() {

    }

    @Override
    public void updateLanguage() {

    }
}
