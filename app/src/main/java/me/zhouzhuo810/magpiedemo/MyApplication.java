package me.zhouzhuo810.magpiedemo;

import android.Manifest;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

import me.zhouzhuo810.magpie.app.BaseApplication;
import me.zhouzhuo810.magpie.cons.Cons;
import me.zhouzhuo810.magpie.utils.BaseUtil;
import me.zhouzhuo810.magpie.utils.CrashUtil;
import me.zhouzhuo810.magpie.utils.LanguageUtil;
import me.zhouzhuo810.magpie.utils.NoticeUtil;
import me.zhouzhuo810.magpie.utils.SpUtil;

public class MyApplication extends BaseApplication {
    
    private static MyApplication instance;
    
    private static final String CRASH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
        + File.separator + "Magpie" + File.separator + "Log";
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        instance = this;
        
        try {
            CrashReport.initCrashReport(this, "cf6f26c47c", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        NoticeUtil.initNoticeChannel("1", getString(R.string.app_name), "Magpie通知渠道", 0, true);
        
        //Crash Handler
        //        initCrash();
    }
    
    
    public static MyApplication getInstance() {
        return instance;
    }
    
    @Override
    public boolean shouldSupportMultiLanguage() {
        return true;
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
