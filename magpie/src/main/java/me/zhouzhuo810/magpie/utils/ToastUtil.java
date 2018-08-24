package me.zhouzhuo810.magpie.utils;

import android.widget.Toast;

/**
 * 吐司工具类
 */
public class ToastUtil {

    private ToastUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 弹出短吐司
     * @param msg 消息内容
     */
    public static void showShortToast(String msg) {
        Toast.makeText(BaseUtil.getApp(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出长吐司
     * @param msg 消息内容
     */
    public static void showLongToast(String msg) {
        Toast.makeText(BaseUtil.getApp(), msg, Toast.LENGTH_LONG).show();
    }


}
