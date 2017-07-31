package com.jiang.android.indicatordialog;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
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
    protected int mArrowWidth;
    protected float arrowercentage; //箭头位置
    protected int arrowdirection = TOP;
    private Activity mContext;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView.Adapter mAdapter;
    protected int gravity = GRAVITY_LEFT;
    protected int animator;
    protected BaseDrawable mArrowDrawable;
    protected boolean dimEnabled = true;

    public IndicatorBuilder(Activity context) {
        this.mContext = context;
    }

    /**
     * 对话框宽度
     *
     * @param width px
     * @return
     */
    public IndicatorBuilder width(int width) {
        this.width = width;
        return this;
    }

    /**
     * 对话框高度， -1则自动适配，否则如果内容真实的高度大于指定的高度，则使用指定的高度，否则使用真实的高度
     *
     * @param height 高度，单位px
     * @return
     */
    public IndicatorBuilder height(int height) {
        this.height = height;
        return this;
    }

    /**
     * 对话框背景颜色
     *
     * @param color
     * @return
     */
    public IndicatorBuilder bgColor(int color) {
        this.bgColor = color;
        return this;

    }

    /**
     * dialog的圆角度数 必须 >= 0
     *
     * @param radius 四周圆角度数
     * @return
     */
    public IndicatorBuilder radius(int radius) {
        if (radius < 0) {
            new Exception("radius must >=0");
        }
        this.radius = radius;
        return this;

    }

    /**
     * 为dialog添加进入退出动画
     *
     * @param animator
     * @return
     */
    public IndicatorBuilder animator(@StyleRes int animator) {
        this.animator = animator;
        return this;
    }

    /**
     * 三角箭头的宽高，因为他是正方形的。
     * 如果不填则默认是用 {@IndicatorDialog.ARROW_RECTAGE} 这个属性，把Dialog的width属性取出来 mBuilder.width * ARROW_RECTAGE 来获取这个箭头的宽度
     * 单位px
     * 当然，你也可以修改{@IndicatorDialog.ARROW_RECTAGE} 这个属性，但是注意： 它是static的，所以会对全局起效。
     *
     * @param width 箭头的宽高
     * @return
     */
    public IndicatorBuilder arrowWidth(int width) {
        this.mArrowWidth = width;
        return this;
    }

    /**
     * 箭头的drawable，你可以通过继承BaseDrawable来实现自定义箭头的样式，默认会将箭头的高度/2往view方向偏移。
     * 不填则是用默认的三角箭头
     *
     * @param drawable
     * @return
     */
    public IndicatorBuilder arrowDrawable(BaseDrawable drawable) {
        this.mArrowDrawable = drawable;
        return this;
    }

    /**
     * 箭头的位置，如果是上下方向，则 view的位置为 width*rectage,如果是左右方向，则 view的位置为 {@IndicatorDialog.mResultHeight} *rectage
     *
     * @param rectage
     * @return
     */
    public IndicatorBuilder ArrowRectage(float rectage) {
        if (rectage > 1 || rectage < 0) {
            new Exception("rectage must be 0 <= rectage <= 1");
        }
        this.arrowercentage = rectage;
        return this;
    }

    /**
     * 箭头方向
     *
     * @param direction
     * @return
     */
    public IndicatorBuilder ArrowDirection(@ARROWDIRECTION int direction) {
        this.arrowdirection = direction;
        return this;
    }


    /**
     * 背景模糊效果，默认true
     *
     * @param enable 默认true
     * @return
     */
    public IndicatorBuilder dimEnabled(boolean enable) {
        this.dimEnabled = enable;
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

        if (width <= 0)
            throw new NullPointerException("width can not be 0");

        if (arrowercentage < 0)
            throw new NullPointerException("arrowercentage can not < 0");


        if (mAdapter == null)
            throw new NullPointerException("adapter can not be null");

        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        }

        return IndicatorDialog.newInstance(mContext, this);
    }

    @IntDef({TOP, BOTTOM, LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ARROWDIRECTION {
    }

    @IntDef({GRAVITY_LEFT, GRAVITY_RIGHT, GRAVITY_CENTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GRAVITY {

    }
}
