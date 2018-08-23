package me.zhouzhuo810.magpie.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import me.zhouzhuo810.magpie.utils.loadviewhelper.AbsLoadViewHelper;
import me.zhouzhuo810.magpie.utils.loadviewhelper.LoadViewHelper;

/**
 * 屏幕适配工具类
 */
public class ScreenAdapterUtil {
    private ScreenAdapterUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static AbsLoadViewHelper sLoadViewHelper;

    public static AbsLoadViewHelper getInstance() {
        return sLoadViewHelper;
    }

    /**
     * 在Application的onCreate中调用此方法
     *
     * @param context 上下文
     */
    public static void init(Application context) {
        init(context, new ScreenAdapterUtil.IProvider() {
            @Override
            public AbsLoadViewHelper provide(Context context, int designWidth, int designDpi,
                                             float fontSize, String unit) {
                return new LoadViewHelper(context, designWidth, designDpi, fontSize, unit);
            }
        });
    }

    public static void init(Context context, ScreenAdapterUtil.IProvider provider) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            throw new RuntimeException("Please add 'design_width', 'design_dpi', 'font_size' and 'unit' meta-data tag in your application.");
        }
        int designwidth = applicationInfo.metaData.getInt("design_width");
        int designdpi = applicationInfo.metaData.getInt("design_dpi");
        float fontsize = applicationInfo.metaData.getFloat("font_size");
        String unit = applicationInfo.metaData.getString("unit");
        sLoadViewHelper = provider.provide(context, designwidth, designdpi, fontsize, unit);
    }

    public interface IProvider {
        AbsLoadViewHelper provide(Context context, int designWidth, int designDpi, float fontSize, String unit);
    }
}