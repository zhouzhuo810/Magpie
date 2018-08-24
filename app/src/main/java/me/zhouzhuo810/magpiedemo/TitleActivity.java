package me.zhouzhuo810.magpiedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.ui.widget.MarkView;
import me.zhouzhuo810.magpie.ui.widget.TitleBar;

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
        TitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setOnTitleClickListener(new TitleBar.OnTitleClick() {
            @Override
            public void onLeftClick(ImageView ivLeft, MarkView mv, TextView tvLeft) {
                closeAct();
            }

            @Override
            public void onTitleClick(TextView tvTitle) {

            }

            @Override
            public void onRightClick(ImageView ivRight, MarkView mv, TextView tvRight) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
