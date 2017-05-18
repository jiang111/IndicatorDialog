/**
 * created by jiang, 12/3/15
 * Copyright (c) 2015, jyuesong@gmail.com All Rights Reserved.
 * *                #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */

package com.jiang.android.indicatordialogdemo;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jiang on 12/3/15.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    protected final SparseArray<View> mViews;
    protected View mConvertView;


    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mConvertView = itemView;
    }


    /**
     * 通过控件的Id获取对应的控件，如果没有则加入mViews，则从item根控件中查找并保存到mViews中
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public BaseViewHolder setBgColor(@IdRes int resID, int color) {
        getView(resID).setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBgDrawable(@IdRes int resID, Drawable drawable) {
        getView(resID).setBackground(drawable);
        return this;

    }

    public BaseViewHolder setText(@IdRes int resID, String text) {
        ((TextView) getView(resID)).setText(text);
        return this;
    }

    public BaseViewHolder setTextSize(@IdRes int resID, int spSize) {
        ((TextView) getView(resID)).setTextSize(spSize);
        return this;
    }

    public BaseViewHolder setVisibility(@IdRes int resID, @Visibility int visibility) {
        switch (visibility) {
            case VISIBLE:
                getView(resID).setVisibility(View.VISIBLE);
                break;
            case INVISIBLE:
                getView(resID).setVisibility(View.INVISIBLE);
                break;
            case GONE:
                getView(resID).setVisibility(View.GONE);
                break;

        }
        return this;

    }

    public BaseViewHolder setTextColor(int id, int textColor) {
        ((TextView) getView(id)).setTextColor(textColor);
        return this;
    }


    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    public static final int VISIBLE = 0x00000000;
    public static final int INVISIBLE = 0x00000004;

    public static final int GONE = 0x00000008;


}
