package com.jiang.android.indicatordialog;

import android.content.Context;

/**
 * Created by jiang on 2017/5/18.
 */

public class Utils {
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
