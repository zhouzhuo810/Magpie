package me.zhouzhuo810.magpie.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;

import me.zhouzhuo810.magpie.utils.loadviewhelper.AbsLoadViewHelper;
import me.zhouzhuo810.magpie.utils.loadviewhelper.LoadViewHelper;

public class DisplayUtil {

    private DisplayUtil() {
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
    public static void init(Context context) {
        init(context, new IProvider() {
            @Override
            public AbsLoadViewHelper provide(Context context, int designWidth, int designDpi,
                                             float fontSize, String unit) {
                return new LoadViewHelper(context, designWidth, designDpi, fontSize, unit);
            }
        });
    }

    public static void init(Context context, IProvider provider) {
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


    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) BaseUtils.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return BaseUtils.getApp().getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) BaseUtils.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return BaseUtils.getApp().getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    /**
     * convert px to its equivalent dp
     * <p>
     * 将px转换为与之相等的dp
     */
    public static int px2dp(float pxValue) {
        final float scale = BaseUtils.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * convert dp to its equivalent px
     * <p>
     * 将dp转换为与之相等的px
     */
    public static int dp2px(float dipValue) {
        final float scale = BaseUtils.getApp().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * convert px to its equivalent sp
     * <p>
     * 将px转换为sp
     */
    public static int px2sp(float pxValue) {
        final float fontScale = BaseUtils.getApp().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * convert sp to its equivalent px
     * <p>
     * 将sp转换为px
     */
    public static int sp2px(float spValue) {
        final float fontScale = BaseUtils.getApp().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * Return the density of screen.
     *
     * @return the density of screen
     */
    public static float getScreenDensity() {
        return BaseUtils.getApp().getResources().getDisplayMetrics().density;
    }

    /**
     * Return the screen density expressed as dots-per-inch.
     *
     * @return the screen density expressed as dots-per-inch
     */
    public static int getScreenDensityDpi() {
        return BaseUtils.getApp().getResources().getDisplayMetrics().densityDpi;
    }

}
