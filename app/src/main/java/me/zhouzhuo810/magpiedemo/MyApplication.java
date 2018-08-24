package me.zhouzhuo810.magpiedemo;

import android.app.Application;

import me.zhouzhuo810.magpie.utils.BaseUtils;
import me.zhouzhuo810.magpie.utils.LanguageUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //工具类初始化，包括屏幕适配
        BaseUtils.init(this);

        //初始化语言
        LanguageUtil.setGlobalLanguage(LanguageUtil.SIMPLE_CHINESE);

    }
}
