package me.zhouzhuo810.magpie.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.zhouzhuo810.magpie.cons.Cons;

/**
 * 语言支持工具类
 * <p>
 * （暂时只支持简体中文和英文）
 */

public class SupportLanguageUtil {
    private static Map<String, Locale> mSupportLanguages = new HashMap<String, Locale>(3) {{
        put(Cons.SIMPLIFIED_CHINESE, Locale.SIMPLIFIED_CHINESE);
        put(Cons.TRADITIONAL_CHINESE, Locale.TRADITIONAL_CHINESE);
        put(Cons.ENGLISH, Locale.ENGLISH);
    }};

    /**
     * 是否支持此语言
     *
     * @param language language
     * @return true:支持 false:不支持
     */
    public static boolean isSupportLanguage(String language) {
        return mSupportLanguages.containsKey(language);
    }

    /**
     * 获取支持语言
     *
     * @param language language
     * @return 支持返回支持语言，不支持返回系统首选语言
     */
    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSupportLanguage(String language) {
        if (isSupportLanguage(language)) {
            return mSupportLanguages.get(language);
        }
        return getSystemPreferredLanguage();
    }

    /**
     * 获取系统首选语言
     *
     * @return Locale
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Locale getSystemPreferredLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }
}
