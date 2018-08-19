package me.zhouzhuo810.magpiedemo;

import android.app.Application;

import me.zhouzhuo810.magpie.utils.DisplayUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //屏幕适配
        DisplayUtil.init(this);
    }
}
