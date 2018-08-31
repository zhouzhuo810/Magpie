package me.zhouzhuo810.magpiedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.ui.widget.TabBar;

public class TabActivity extends BaseActivity {

    TabBar tabBar;

    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tabBar = findViewById(R.id.tab_bar);
        tabBar.setOnTabBarClickListener(new TabBar.OnTabBarClick() {
            @Override
            public void onTabClick(ImageView iv, TextView tv, int position, boolean changed) {

            }
        });
        tabBar.setSelection(0);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
