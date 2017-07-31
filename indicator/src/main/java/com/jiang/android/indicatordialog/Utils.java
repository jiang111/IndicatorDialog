package com.jiang.android.indicatordialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by jiang on 2017/5/18.
 */

public class Utils {
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int[] getLocationInWindow(Activity context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return new int[]{dm.widthPixels, dm.heightPixels};


    }

    public static int[] getLocationInContent(Activity context) {
        int[] location = getLocationInWindow(context);
        int height = location[1] - new SystemBarConfig(context).getStatusBarHeight();
        return new int[]{location[0], height};

    }

    public static int getStatusBarHeight(Activity context) {
        return new SystemBarConfig(context).getStatusBarHeight();
    }

    public static int getNavigationBarHeight(Activity context) {
        return new SystemBarConfig(context).getNavigationBarHeight();

    }
}
