package me.zhouzhuo810.magpietv.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

public class ActivityUtil {

    public static Bundle getOptionsBundle(final Context context,
                                          final int enterAnim,
                                          final int exitAnim) {
        return ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim).toBundle();
    }


    public static Bundle getOptionsBundle(final Activity activity,
                                          final View[] sharedElements) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return null;
        if (sharedElements == null) return null;
        int len = sharedElements.length;
        if (len <= 0) return null;
        @SuppressWarnings("unchecked")
        Pair<View, String>[] pairs = new Pair[len];
        for (int i = 0; i < len; i++) {
            pairs[i] = Pair.create(sharedElements[i], sharedElements[i].getTransitionName());
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs).toBundle();
    }
}
