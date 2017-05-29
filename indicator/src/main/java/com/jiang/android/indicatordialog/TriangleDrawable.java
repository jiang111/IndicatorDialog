package com.jiang.android.indicatordialog;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.jiang.android.indicatordialog.IndicatorBuilder.BOTTOM;
import static com.jiang.android.indicatordialog.IndicatorBuilder.LEFT;
import static com.jiang.android.indicatordialog.IndicatorBuilder.TOP;

/**
 * Created by jiang on 2017/5/19.
 */

public class TriangleDrawable extends BaseDrawable {

    private int bgColor = Color.WHITE;

    public TriangleDrawable(@IndicatorBuilder.ARROWDIRECTION int arrowDirection, int bgColor) {
        super(arrowDirection);
        this.bgColor = bgColor;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(bgColor);
        paint.setStyle(Paint.Style.FILL);
        Path path = createPath();
        canvas.drawPath(path, paint);


    }

    private Path createPath() {
        Rect bound = getBounds();
        Path path = new Path();
        if (arrowDirection == TOP) {
            path.moveTo(bound.right / 2, bound.bottom / 2);
            path.lineTo(0, bound.bottom);
            path.lineTo(bound.right, bound.bottom);
            path.close();
        } else if (arrowDirection == BOTTOM) {
            path.moveTo(bound.right / 2, bound.bottom / 2);
            path.lineTo(0, 0);
            path.lineTo(bound.right, 0);
            path.close();

        } else if (arrowDirection == LEFT) {
            path.moveTo(bound.right / 2, bound.bottom / 2);
            path.lineTo(bound.right, 0);
            path.lineTo(bound.right, bound.bottom);
            path.close();
        } else {
            path.moveTo(bound.right / 2, bound.bottom / 2);
            path.lineTo(0, 0);
            path.lineTo(0, bound.bottom);
            path.close();
        }
        return path;

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
