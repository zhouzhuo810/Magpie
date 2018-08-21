package me.zhouzhuo810.magpiedemo;

import android.app.Application;

import com.yanzhenjie.kalle.Kalle;
import com.yanzhenjie.kalle.KalleConfig;
import com.yanzhenjie.kalle.OkHttpConnectFactory;
import com.yanzhenjie.kalle.connect.http.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import me.zhouzhuo810.magpie.utils.BaseUtils;
import me.zhouzhuo810.magpie.utils.LanguageUtil;
import me.zhouzhuo810.magpiedemo.api.converter.GsonConverter;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //工具类初始化，包括屏幕适配
        BaseUtils.init(this);

        //初始化语言
        LanguageUtil.setGlobalLanguage(LanguageUtil.SIMPLE_CHINESE);

        //网络配置
        Kalle.setConfig(
                KalleConfig.newBuilder()
                        .connectFactory(OkHttpConnectFactory.newBuilder().build())
                        .addInterceptor(new LoggerInterceptor(getString(R.string.app_name), true))
                        .connectionTimeout(10, TimeUnit.SECONDS)
                        .converter(new GsonConverter())
                        .build()
        );
    }
}
