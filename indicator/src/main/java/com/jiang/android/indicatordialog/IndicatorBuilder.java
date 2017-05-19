package com.jiang.android.indicatordialog;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jiang on 2017/5/18.
 */

public class IndicatorBuilder {
    public static final int TOP = 12;
    public static final int BOTTOM = 13;
    public static final int LEFT = 14;
    public static final int RIGHT = 15;

    public static final int GRAVITY_LEFT = 688;
    public static final int GRAVITY_RIGHT = 689;
    public static final int GRAVITY_CENTER = 670;
    protected int width;
    protected int height;
    protected int radius = 8;
    protected int bgColor = Color.WHITE;
    protected float arrowercentage; //箭头位置
    protected int arrowdirection = TOP;
    private Activity mContext;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView.Adapter mAdapter;
    protected int gravity = GRAVITY_LEFT;

    public IndicatorBuilder(Activity context) {
        this.mContext = context;
    }

    public IndicatorBuilder width(int width) {
        this.width = width;
        return this;
    }

    public IndicatorBuilder height(int height) {
        this.height = height;
        return this;
    }

    public IndicatorBuilder bgColor(int color) {
        this.bgColor = color;
        return this;

    }

    public IndicatorBuilder radius(int radius) {
        this.radius = radius;
        return this;

    }

    public IndicatorBuilder ArrowRectage(float rectage) {
        if (rectage > 1) {
            new Exception("rectage can not >= 1");
        }
        this.arrowercentage = rectage;
        return this;
    }

    public IndicatorBuilder ArrowDirection(@ARROWDIRECTION int direction) {
        this.arrowdirection = direction;
        return this;
    }

    public IndicatorBuilder layoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        return this;
    }

    public IndicatorBuilder adapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        return this;
    }

    public IndicatorBuilder gravity(@GRAVITY int gravity) {
        this.gravity = gravity;
        return this;
    }

    public IndicatorDialog create() {
        return IndicatorDialog.newInstance(mContext, this);
    }

    @IntDef({TOP, BOTTOM, LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ARROWDIRECTION {
    }

    @IntDef({GRAVITY_LEFT, GRAVITY_RIGHT,GRAVITY_CENTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GRAVITY {

    }
}
