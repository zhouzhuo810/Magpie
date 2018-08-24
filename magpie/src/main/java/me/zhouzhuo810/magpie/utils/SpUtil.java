package me.zhouzhuo810.magpie.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 */
public class SpUtil {

    private SpUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static SharedPreferences getShared() {
        String packageName = BaseUtil.getApp().getPackageName();
        String projectName = packageName.substring(packageName.lastIndexOf(".") + 1, packageName.length());
        return BaseUtil.getApp().getSharedPreferences(projectName, Context.MODE_PRIVATE);
    }

    /**
     * 保存int类型键值对
     *
     * @param key   键
     * @param value 值
     */
    public static void putInt(String key, int value) {
        getShared().edit().putInt(key, value).apply();
    }

    /**
     * 获取int类型键值对
     *
     * @param key 键
     * @return 值
     */
    public static int getInt(String key) {
        return getShared().getInt(key, 0);
    }


    /**
     * 获取int类型键值对
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static int getInt(String key, int defValue) {
        return getShared().getInt(key, defValue);
    }

    /**
     * 保存long类型键值对
     *
     * @param key   键
     * @param value 值
     */
    public static void putLong(String key, long value) {
        getShared().edit().putLong(key, value).apply();
    }

    /**
     * 获取long类型键值对
     *
     * @param key 键
     * @return 值
     */
    public static long getLong(String key) {
        return getShared().getLong(key, 0);
    }

    /**
     * 获取long类型键值对
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static long getLong(String key, long defValue) {
        return getShared().getLong(key, defValue);
    }

    /**
     * 保存float类型键值对
     *
     * @param key   键
     * @param value 值
     */
    public static void putFloat(String key, float value) {
        getShared().edit().putFloat(key, value).apply();
    }

    /**
     * 获取float类型键值对
     *
     * @param key 键
     * @return 值
     */
    public static float getFloat(String key) {
        return getShared().getFloat(key, 0f);
    }

    /**
     * 获取float类型键值对
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static float getFloat(String key, float defValue) {
        return getShared().getFloat(key, defValue);
    }

    /**
     * 保存string类型键值对
     *
     * @param key   键
     * @param value 值
     */
    public static void putString(String key, String value) {
        getShared().edit().putString(key, value).apply();
    }

    /**
     * 获取string类型键值对
     *
     * @param key 键
     * @return 值
     */
    public static String getString(String key) {
        return getShared().getString(key, null);
    }

    /**
     * 获取string类型键值对
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static String getString(String key, String defValue) {
        return getShared().getString(key, defValue);
    }

    /**
     * 保存boolean类型键值对
     *
     * @param key   键
     * @param value 值
     */
    public static void putBoolean(String key, boolean value) {
        getShared().edit().putBoolean(key, value).apply();
    }

    /**
     * 获取boolean类型键值对
     *
     * @param key 键
     * @return 值
     */
    public static boolean getBoolean(String key) {
        return getShared().getBoolean(key, false);
    }

    /**
     * 获取boolean类型键值对
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getShared().getBoolean(key, defValue);
    }

    /**
     * 移除
     *
     * @param key 键
     */
    public static void remoteKey(String key) {
        getShared().edit().remove(key).apply();
    }

    /**
     * 清空所有key value
     *
     * @param context 上下文
     */
    public static void clearAll(Context context) {
        getShared().edit().clear().apply();
    }

}
