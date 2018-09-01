package me.zhouzhuo810.magpietvdemo;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import java.io.File;

import me.zhouzhuo810.magpietv.utils.BaseUtil;
import me.zhouzhuo810.magpietv.utils.CrashUtils;
import me.zhouzhuo810.magpietv.utils.ToastUtil;

public class MyApplication extends Application {

    private static final String CRASH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Magpie-TV" + File.separator + "Log";

    @Override
    public void onCreate() {
        super.onCreate();
        initCrash();
    }

    private void initCrash() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        CrashUtils.init(CRASH_PATH, new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(String crashInfo, Throwable e) {
                ToastUtil.showLongToast("应用开小差了，稍后重启~");
                BaseUtil.relaunchApp();
            }
        });
    }
}
