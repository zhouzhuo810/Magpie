package me.zhouzhuo810.magpie.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.zhouzhuo810.magpie.cons.Cons;

public class LanguageUtil {


    public static final int SIMPLE_CHINESE = 0;
    public static final int TRADITIONAL_CHINESE = 1;
    public static final int ENGLISH = 2;

    @IntDef({SIMPLE_CHINESE, TRADITIONAL_CHINESE, ENGLISH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LANGUAGE {
    }


    private static Map<String, Locale> mSupportLanguages = new HashMap<String, Locale>(3) {{
        put(Cons.SIMPLIFIED_CHINESE, Locale.SIMPLIFIED_CHINESE);
        put(Cons.TRADITIONAL_CHINESE, Locale.TRADITIONAL_CHINESE);
        put(Cons.ENGLISH, Locale.ENGLISH);
    }};


    /**
     * 设置应用全局语言
     *
     * @param language 语言
     *                 <ul>
     *                 <li>{@link LanguageUtil#SIMPLE_CHINESE}</li>
     *                 <li>{@link LanguageUtil#TRADITIONAL_CHINESE }</li>
     *                 <li>{@link LanguageUtil#ENGLISH }</li>
     *                 </ul>
     */
    public static void setGlobalLanguage(@LanguageUtil.LANGUAGE int language) {
        SpUtil.putInt(Cons.SP_KEY_OF_CHOOSED_LANGUAGE, language);
    }


    public static void applyLanguage(Context context, String newLanguage) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getSupportLanguage(newLanguage);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // apply locale
            configuration.setLocale(locale);
        } else {
            // updateConfiguration
            configuration.locale = locale;
            DisplayMetrics dm = resources.getDisplayMetrics();
            resources.updateConfiguration(configuration, dm);
        }
    }

    public static Context attachBaseContext(Context context, String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context, language);
        } else {
            applyLanguage(context, language);
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context, String language) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale;
        if (TextUtils.isEmpty(language)) {//如果没有指定语言使用系统首选语言
            locale = getSystemPreferredLanguage();
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = getSupportLanguage(language);
        }
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    /**
     * 获取当前系统语言,用于接口请求参数，系统需要的中文标识为zh，而此处返回的是ch，因为接口定的是ch
     *
     * @return 只返回中（ch）英（en）文，如果不是英文，其它情况一律中文
     */
    public static String getLanguage() {
        Locale locale;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            locale = BaseUtil.getApp().getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = BaseUtil.getApp().getResources().getConfiguration().locale;
        }

        if (locale == null) {
            return "ch";
        } else if (locale.getLanguage().equals(new Locale("en").getLanguage())) {
            return "en";
        } else {
            return "ch";
        }
    }


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
