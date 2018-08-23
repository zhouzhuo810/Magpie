package me.zhouzhuo810.magpiedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.zhouzhuo810.magpie.ui.act.BaseActivity;

/**
 * 标题栏用法展示
 */
public class TitleActivity extends BaseActivity {
    @Override
    public boolean shouldSupportMultiLanguage() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_title;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
