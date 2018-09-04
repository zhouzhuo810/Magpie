package me.zhouzhuo810.magpiedemo;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import java.io.File;

import me.zhouzhuo810.magpie.utils.BaseUtil;
import me.zhouzhuo810.magpie.utils.CrashUtils;
import me.zhouzhuo810.magpie.utils.LanguageUtil;

public class MyApplication extends Application {

    private static final String CRASH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Magpie" + File.separator + "Log";

    @Override
    public void onCreate() {
        super.onCreate();

        //工具类初始化，包括屏幕适配
        BaseUtil.init(this);

        //初始化语言
        LanguageUtil.setGlobalLanguage(LanguageUtil.SIMPLE_CHINESE);

        //Crash Handler
        initCrash();
    }

    private void initCrash() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        CrashUtils.init(CRASH_PATH, new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(String crashInfo, Throwable e) {
                BaseUtil.relaunchApp();
            }
        });
    }
}
