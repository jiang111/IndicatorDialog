package com.jiang.android.indicatordialog;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewConfiguration;

import java.lang.reflect.Method;

/**
 * Created by jiang on 2017/5/26.
 */

/**
 * 这个类描述了当前设备的配置中system bar的尺寸(StatusBar状态栏,NavigationBar虚拟按键栏,ActionBar标题栏)、
 * 屏幕宽高以及一些相关的特征。
 */
public class SystemBarConfig {

    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
    private static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
    private static final String NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape";
    private static final String NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width";
    private static final String SHOW_NAV_BAR_RES_NAME = "config_showNavigationBar";

    private final int mStatusBarHeight;
    private final int mActionBarHeight;
    private final boolean mHasNavigationBar;
    private final int mNavigationBarHeight;
    private final int mNavigationBarWidth;
    private final int mContentHeight;
    private final int mContentWidth;
    private final boolean mInPortrait;
    private final float mSmallestWidthDp;

    public SystemBarConfig(Activity activity) {
        Resources res = activity.getResources();
        mInPortrait = (res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        mSmallestWidthDp = getSmallestWidthDp(activity);
        mStatusBarHeight = getInternalDimensionSize(res, STATUS_BAR_HEIGHT_RES_NAME);
        mActionBarHeight = getActionBarHeight(activity);
        mNavigationBarHeight = getNavigationBarHeight(activity);
        mNavigationBarWidth = getNavigationBarWidth(activity);
        mContentHeight = getContentHeight(activity);
        mContentWidth = getContentWidth(activity);
        mHasNavigationBar = (mNavigationBarHeight > 0);

    }

    // 安卓系统允许修改系统的属性来控制navigation bar的显示和隐藏，此方法用来判断是否有修改过相关属性。
    // (修改系统文件，在build.prop最后加入qemu.hw.mainkeys=1即可隐藏navigation bar)
    // 相关属性模拟器中有使用。
    // 当返回值等于"1"表示隐藏navigation bar，等于"0"表示显示navigation bar。
    @TargetApi(19)
    private String getNavBarOverride() {
        String isNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                isNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
                isNavBarOverride = null;
            }
        }
        return isNavBarOverride;
    }

    //通过此方法获取action bar的高度
    @TargetApi(14)
    private int getActionBarHeight(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            TypedValue tv = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return result;
    }

    //通过此方法获取navigation bar的高度
    @TargetApi(14)
    public int getNavigationBarHeight(Context context) {
        Resources res = context.getResources();
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar(context)) {
                String key;
                if (mInPortrait) {
                    key = NAV_BAR_HEIGHT_RES_NAME;
                } else {
                    key = NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME;
                }
                return getInternalDimensionSize(res, key);
            }
        }
        return result;
    }

    //通过此方法获取navigation bar的宽度
    @TargetApi(14)
    private int getNavigationBarWidth(Context context) {
        Resources res = context.getResources();
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar(context)) {
                return getInternalDimensionSize(res, NAV_BAR_WIDTH_RES_NAME);
            }
        }
        return result;
    }

    //通过此方法判断是否存在navigation bar
    @TargetApi(14)
    private boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier(SHOW_NAV_BAR_RES_NAME, "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // 查看是否有通过系统属性来控制navigation bar。
            if ("1".equals(getNavBarOverride())) {
                hasNav = false;
            } else if ("0".equals(getNavBarOverride())) {
                hasNav = true;
            }
            return hasNav;
        } else {
            //可通过此方法来查看设备是否存在物理按键(menu,back,home键)。
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    //通过此方法获取资源对应的像素值
    private int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //通过此方法获取最小一边的dp值，再通过这个dp值大小来判断设备的navigation bar是显示在底部还是右侧
    @TargetApi(17)
    private float getSmallestWidthDp(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        float widthDp;
        float heightDp;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //API 17之后使用，获取的像素宽高包含虚拟键所占空间，在API 17之前通过反射获取，
            //获取的屏幕高度包含status bar和navigation bar
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            widthDp = metrics.widthPixels / metrics.density;
            heightDp = metrics.heightPixels / metrics.density;
        } else {
            //获取的屏幕高度包含status bar,但不包含navigation bar
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            widthDp = metrics.widthPixels / metrics.density;
            heightDp = (metrics.heightPixels + getNavigationBarWidth(activity)) / metrics.density;
        }
        return Math.min(widthDp, heightDp);
    }

    //通过此方法获取屏幕高度(不含status bar 和 navigation bar的高度)
    private int getContentHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels - getStatusBarHeight();
    }

    //通过此方法获取屏幕的宽度(不含navigation bar的宽度)
    private int getContentWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 判断navigation bar 是显示在底部还是显示在右侧
     *
     * @return true表示在底部，false表示在右侧
     */
    public boolean isNavigationAtBottom() {
        return (mSmallestWidthDp >= 600 || mInPortrait);
    }

    /**
     * 获取status bar状态栏高度
     *
     * @return 状态栏高度的像素值
     */
    public int getStatusBarHeight() {
        return mStatusBarHeight;
    }

    /**
     * 获取action bar的高度
     *
     * @return action bar高度的像素值
     */
    public int getActionBarHeight() {
        return mActionBarHeight;
    }

    /**
     * 判断此设备是否有navigation bar虚拟按键栏
     *
     * @return true表示有，false表示无
     */
    public boolean hasNavigtionBar() {
        return mHasNavigationBar;
    }

    /**
     * 获取navigation bar虚拟按键栏的高度
     *
     * @return 返回navigation bar虚拟按键栏的高度的像素值，如果设备没有navigation bar虚拟按键栏则返回0
     */
    public int getNavigationBarHeight() {
        return mNavigationBarHeight;
    }

    /**
     * 获取navigation bar虚拟按键栏的宽度（当navigation bar虚拟按键栏垂直显示在右侧时使用）
     *
     * @return 返回navigation bar虚拟按键栏的宽度的像素值，如果设备没有navigation bar虚拟按键栏则返回0
     */
    public int getNavigationBarWidth() {
        return mNavigationBarWidth;
    }

    /**
     * 获取屏幕高度(不含status bar 和 navigation bar的高度)
     *
     * @return 返回屏幕高度的像素值(不含status bar 和 navigation bar的高度)
     */
    public int getContentHeight() {
        return mContentHeight;
    }

    /**
     * 获取屏幕宽度(不含navigation bar的宽度)
     *
     * @return 返回屏幕宽度的像素值(不含navigation bar的宽度)
     */
    public int getContentWidth() {
        return mContentWidth;
    }

}