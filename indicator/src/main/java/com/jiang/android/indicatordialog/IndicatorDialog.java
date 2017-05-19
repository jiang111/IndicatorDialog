package com.jiang.android.indicatordialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by jiang on 2017/5/18.
 */

public class IndicatorDialog {

    public static final float ARROW_RECTAGE = 0.075f;
    private Activity mContext;
    private Dialog mDialog;
    private IndicatorBuilder mBuilder;
    private RecyclerView recyclerView;
    private LinearLayout rootLayout;
    int gravity = Gravity.TOP | Gravity.LEFT;
    private LinearLayout childLayout;
    private int arrowWidth;

    public static IndicatorDialog newInstance(Activity context, IndicatorBuilder builder) {
        IndicatorDialog dialog = new IndicatorDialog(context, builder);
        return dialog;
    }


    public IndicatorDialog(Activity context, IndicatorBuilder builder) {
        this.mContext = context;
        this.mBuilder = builder;
        this.arrowWidth = (int) (mBuilder.width * ARROW_RECTAGE);
        initDialog();
    }

    private void initDialog() {
        mDialog = new Dialog(mContext);
        rootLayout = new LinearLayout(mContext);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams rootParam = new ViewGroup.LayoutParams(mBuilder.width,
                mBuilder.height <= 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : mBuilder.height);
        rootLayout.setLayoutParams(rootParam);
        if (mBuilder.arrowdirection == IndicatorBuilder.TOP)
            addArrow2LinearLayout();
        addRecyclerView2RecyclerView();


        mDialog.setContentView(rootLayout);
        setSize2Dialog(mBuilder.height);

    }

    private void addRecyclerView2RecyclerView() {
        childLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, rootLayout, true);
        recyclerView = (RecyclerView) childLayout.findViewById(R.id.j_dialog_rv);


        if (mBuilder.arrowdirection != IndicatorBuilder.BOTTOM) {
            childLayout.findViewById(R.id.j_dialog_bottom_arrow).setVisibility(View.GONE);
        } else {
            addBottomArrow2LinearLayout();
        }


        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int recyclerviewHeight = recyclerView.getHeight();
                resizeHeight(recyclerviewHeight);
            }
        });

    }

    private void addBottomArrow2LinearLayout() {
        View arrowBottom = childLayout.findViewById(R.id.j_dialog_bottom_arrow);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) arrowBottom.getLayoutParams();
        layoutParams.width = arrowWidth;
        layoutParams.height = arrowWidth;
        layoutParams.leftMargin = (int) (mBuilder.width * mBuilder.arrowercentage);
        arrowBottom.setLayoutParams(layoutParams);
        TriangleDrawable drawable = new TriangleDrawable(mBuilder.arrowdirection, mBuilder.bgColor);
        drawable.setBounds(arrowBottom.getLeft(), arrowBottom.getTop(), arrowBottom.getRight(), arrowBottom.getBottom());
        arrowBottom.setBackgroundDrawable(drawable);
    }

    private void resizeHeight(int recyclerviewHeight) {

        int arrowHeght = arrowWidth;
        int topAndBottomHeight = Utils.dip2px(mContext, 10);
        int calcuteResult = recyclerviewHeight + topAndBottomHeight + arrowHeght;

        int result;
        if (mBuilder.height <= 0 || calcuteResult < mBuilder.height) {
            result = calcuteResult;
        } else {
            result = mBuilder.height;
        }

        ViewGroup.LayoutParams params = rootLayout.getLayoutParams();
        params.height = result;
        rootLayout.setLayoutParams(params);

        if (mBuilder.arrowdirection == IndicatorBuilder.BOTTOM) {
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
            params1.bottomMargin = arrowWidth + Utils.dip2px(mContext, 5);
            recyclerView.setLayoutParams(params1);

        }

        rootLayout.requestLayout();
        setSize2Dialog(result);

    }

    private void addArrow2LinearLayout() {
        View arrow = new View(mContext);
        rootLayout.addView(arrow);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) arrow.getLayoutParams();
        layoutParams.width = (int) (mBuilder.width * 0.075);
        layoutParams.height = (int) (mBuilder.width * 0.075);
        layoutParams.leftMargin = (int) (mBuilder.width * mBuilder.arrowercentage);
        arrow.setLayoutParams(layoutParams);
        TriangleDrawable drawable = new TriangleDrawable(mBuilder.arrowdirection, mBuilder.bgColor);
        drawable.setBounds(arrow.getLeft(), arrow.getTop(), arrow.getRight(), arrow.getBottom());
        arrow.setBackgroundDrawable(drawable);
    }

    private void setSize2Dialog(int height) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if (mBuilder.gravity == IndicatorBuilder.GRAVITY_RIGHT) {
            gravity = Gravity.RIGHT | (mBuilder.arrowdirection == IndicatorBuilder.TOP ? Gravity.TOP : Gravity.BOTTOM);
        } else {
            gravity = Gravity.LEFT | (mBuilder.arrowdirection == IndicatorBuilder.TOP ? Gravity.TOP : Gravity.BOTTOM);
        }
        dialogWindow.setGravity(gravity);
        lp.width = mBuilder.width; // 宽度
        lp.height = height; // 高度
        dialogWindow.setAttributes(lp);
    }

    public IndicatorDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }


    public void show(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int x;
        int y;
        if ((gravity & Gravity.RIGHT) == Gravity.RIGHT) {
            x = width - location[0] - view.getWidth();
        } else {
            x = location[0];
        }

        if (mBuilder.arrowdirection == IndicatorBuilder.BOTTOM) {
            y = height - location[1];
        } else {
            y = location[1] + view.getHeight() / 2;
        }
        show(x, y);

    }

    public void show(int x, int y) {

        recyclerView.setLayoutManager(mBuilder.mLayoutManager);
        recyclerView.setAdapter(mBuilder.mAdapter);


        setDialogPosition(x, y);
        mDialog.show();
    }

    private static final String TAG = "IndicatorDialog";

    private void setDialogPosition(int x, int y) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = x;
        lp.y = y;
        dialogWindow.setAttributes(lp);
    }


}
