package me.zhouzhuo810.magpie.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 基础工具类
 */
public class BaseUtil {

    private static Application mApp;

    private BaseUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 在自定义的Application的onCreate方法中调用
     *
     * @param app Application
     */
    public static void init(Application app) {
        mApp = app;
        //顺便初始化屏幕适配工具类
        ScreenAdapterUtil.init(mApp);
    }

    public static Application getApp() {
        checkContextNull();
        return mApp;
    }

    private static void checkContextNull() {
        if (mApp == null) {
            throw new NullPointerException("Please invoke BaseUtil.init(Application app) method first");
        }
    }

    /**
     * 获取包名，版本号，版本名称等信息
     *
     * @param context 上下文
     * @return 信息
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new PackageInfo();
    }

}
