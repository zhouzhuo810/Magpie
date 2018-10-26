package me.zhouzhuo810.magpietvdemo;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

import me.zhouzhuo810.magpietv.utils.BaseUtil;
import me.zhouzhuo810.magpietv.utils.CrashUtil;

public class MyApplication extends Application {

    private static final String CRASH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Magpie-TV" + File.separator + "Log";

    @Override
    public void onCreate() {
        super.onCreate();
        BaseUtil.init(this);
    
        try {
            CrashReport.initCrashReport(this, "98d7438e50", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
