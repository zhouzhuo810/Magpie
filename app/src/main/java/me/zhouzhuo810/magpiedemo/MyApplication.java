package me.zhouzhuo810.magpiedemo;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

import me.zhouzhuo810.magpie.utils.BaseUtil;
import me.zhouzhuo810.magpie.utils.CrashUtil;
import me.zhouzhuo810.magpie.utils.LanguageUtil;

public class MyApplication extends Application {
    
    private static final String CRASH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
        + File.separator + "Magpie" + File.separator + "Log";
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        //工具类初始化，包括屏幕适配
        BaseUtil.init(this);
        
        //初始化语言
        LanguageUtil.setGlobalLanguage(LanguageUtil.SIMPLE_CHINESE);
        
        try {
            CrashReport.initCrashReport(this, "cf6f26c47c", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Crash Handler
        //        initCrash();
    }
    
    private void initCrash() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            CrashUtil.init(new CrashUtil.OnCrashListener() {
                @Override
                public void onCrash(String crashInfo, Throwable e) {
                    BaseUtil.relaunchApp();
                }
            });
        } else {
            CrashUtil.init(CRASH_PATH, new CrashUtil.OnCrashListener() {
                @Override
                public void onCrash(String crashInfo, Throwable e) {
                    BaseUtil.relaunchApp();
                }
            });
        }
    }
}
