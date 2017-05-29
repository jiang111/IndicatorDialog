package com.jiang.android.indicatordialog;

import android.graphics.drawable.Drawable;

/**
 * Created by jiang on 2017/5/29.
 */

public abstract class BaseDrawable extends Drawable {

    public final int arrowDirection;

    public BaseDrawable(@IndicatorBuilder.ARROWDIRECTION int arrowDirection) {
        this.arrowDirection = arrowDirection;
    }
}
