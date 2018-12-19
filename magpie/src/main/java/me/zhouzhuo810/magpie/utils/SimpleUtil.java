package me.zhouzhuo810.magpie.utils;

import android.graphics.Typeface;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.View;

import java.text.DecimalFormat;

/**
 * 常用方法简化
 */
public class SimpleUtil {
    
    /**
     * 缩放View
     *
     * @param v 要缩放的View
     */
    public static void scaleView(View v) {
        ScreenAdapterUtil.getInstance().loadView(v);
    }
    
    /**
     * 缩放View
     *
     * @param v 要缩放的View
     */
    public static void scaleViewByWidthHeight(View v) {
        ScreenAdapterUtil.getInstance().loadView(v, true);
    }
    
    /**
     * 缩放值按宽度（默认）
     *
     * @param px 原来值
     * @return 缩放后的值
     */
    public static int getScaledValue(int px) {
        return ScreenAdapterUtil.getInstance().getScaledValue(px);
    }
    
    /**
     * 缩放值
     *
     * @param px 原来值
     * @return 缩放后的值
     */
    public static int getScaledValueByHeight(int px) {
        return ScreenAdapterUtil.getInstance().getScaledValueByHeight(px);
    }
    
    /**
     * 获取字符串资源
     *
     * @param resId 资源ID
     * @return 字符串
     */
    public static String getString(@StringRes int resId) {
        return BaseUtil.getApp().getString(resId);
    }
    
    /**
     * 获取颜色资源
     *
     * @param resId 资源ID
     * @return 颜色值
     */
    public static int getColor(@ColorRes int resId) {
        return BaseUtil.getApp().getResources().getColor(resId);
    }
    
    /**
     * 获取字符串数组资源
     *
     * @param resId 资源ID
     * @return 字符串数组
     */
    public static String[] getStringArray(@ArrayRes int resId) {
        return BaseUtil.getApp().getResources().getStringArray(resId);
    }
    
    /**
     * 获取assets文件夹中的字体
     *
     * @param path 路径，如fonts/xxx.ttf
     * @return Typeface对象，没有则返回null
     */
    public static Typeface getFontFromAssets(String path) {
        return FontUtil.getTypeFaceFromAssets(path);
    }
    
    /**
     * 获取assets文件夹中的文件的字符串内容
     *
     * @param path 路径，如json/test.json
     * @return 文件内容字符串
     */
    public static String getFileContentFromAssets(String path) {
        return AssetsUtil.getFileToStringFromAssets(path);
    }
    
    /**
     * 格式化浮点数为字符串
     *
     * @param f 浮点数
     * @param n 保留小数位
     * @return 字符串
     */
    public static String formatFloatWithDecimal(float f, int n) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("#0.");
        if (n <= 1) {
            pattern.append("0");
        } else {
            for (int i = 0; i < n; i++) {
                pattern.append("0");
            }
        }
        return new DecimalFormat(pattern.toString()).format(f);
    }
}
