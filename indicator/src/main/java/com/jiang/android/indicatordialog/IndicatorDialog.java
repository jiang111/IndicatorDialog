package com.jiang.android.indicatordialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
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

import static com.jiang.android.indicatordialog.IndicatorBuilder.TOP;

/**
 * Created by jiang on 2017/5/18.
 */

public class IndicatorDialog {

    public static float ARROW_RECTAGE = 0.1f;
    private Activity mContext;
    private Dialog mDialog;
    private IndicatorBuilder mBuilder;
    private RecyclerView recyclerView;
    private LinearLayout rootLayout;
    int gravity = Gravity.TOP | Gravity.LEFT;
    private LinearLayout childLayout;
    private int arrowWidth;
    private View arrowTop;
    private View topRadiusView;
    private View bottomRadiusView;
    private int mRadiusHeight;

    public static IndicatorDialog newInstance(Activity context, IndicatorBuilder builder) {
        IndicatorDialog dialog = new IndicatorDialog(context, builder);
        return dialog;
    }


    public IndicatorDialog(Activity context, IndicatorBuilder builder) {
        this.mContext = context;
        this.mBuilder = builder;
        this.arrowWidth = (int) (mBuilder.width * ARROW_RECTAGE);
        this.mRadiusHeight = (int) (mBuilder.radius * 1.5);
        initDialog();
    }

    private void initDialog() {
        mDialog = new Dialog(mContext, android.R.style.Theme_Material_Dialog_NoActionBar);
        rootLayout = new LinearLayout(mContext);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams rootParam = new ViewGroup.LayoutParams(mBuilder.width,
                mBuilder.height <= 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : mBuilder.height);
        rootLayout.setLayoutParams(rootParam);
        if (mBuilder.arrowdirection == TOP)
            addArrow2LinearLayout();
        addRecyclerView2RecyclerView();


        mDialog.setContentView(rootLayout);
        setSize2Dialog(mBuilder.height);

    }

    /**
     * modify recyclerview state
     */
    private void addRecyclerView2RecyclerView() {
        childLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.dialog_layout, rootLayout, true);

        recyclerView = (RecyclerView) childLayout.findViewById(R.id.j_dialog_rv);
        recyclerView.setBackgroundColor(mBuilder.bgColor);

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

    /**
     * modify top & bottom bg color
     */
    private void modifybgColor() {
        topRadiusView = childLayout.findViewById(R.id.j_dialog_top);
        bottomRadiusView = childLayout.findViewById(R.id.j_dialog_bottom);

        if (mRadiusHeight <= 0) {
            topRadiusView.setVisibility(View.GONE);
            bottomRadiusView.setVisibility(View.GONE);

        } else {
            topRadiusView.setVisibility(View.VISIBLE);
            bottomRadiusView.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams topRadiusLayoutParam = (RelativeLayout.LayoutParams) topRadiusView.getLayoutParams();
            topRadiusLayoutParam.height = mRadiusHeight;
            topRadiusLayoutParam.width = mBuilder.width;
            topRadiusView.setLayoutParams(topRadiusLayoutParam);
            Bitmap bitmap = Bitmap.createBitmap(mBuilder.width, mRadiusHeight, Bitmap.Config.RGB_565);
            RoundedDrawable roundedDrawable = new RoundedDrawable(bitmap, mBuilder.bgColor);
            roundedDrawable.setCornerRadius(mBuilder.radius, mBuilder.radius, 0, 0);
            topRadiusView.setBackgroundDrawable(roundedDrawable);
            LinearLayout.LayoutParams bottomRadiusLayoutParam = (LinearLayout.LayoutParams) bottomRadiusView.getLayoutParams();
            bottomRadiusLayoutParam.height = mRadiusHeight;
            bottomRadiusLayoutParam.width = mBuilder.width;

            bottomRadiusView.setLayoutParams(bottomRadiusLayoutParam);
            Bitmap bitmapBottom = Bitmap.createBitmap(mBuilder.width, mRadiusHeight, Bitmap.Config.RGB_565);
            RoundedDrawable roundedDrawableBottom = new RoundedDrawable(bitmapBottom, mBuilder.bgColor);
            roundedDrawableBottom.setCornerRadius(0, 0, mBuilder.radius, mBuilder.radius);
            bottomRadiusView.setBackgroundDrawable(roundedDrawableBottom);
        }
    }

    private void addBottomArrow2LinearLayout() {
        View arrowBottom = childLayout.findViewById(R.id.j_dialog_bottom_arrow);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) arrowBottom.getLayoutParams();
        layoutParams.width = arrowWidth;
        layoutParams.height = arrowWidth;
        layoutParams.leftMargin = (int) (mBuilder.width * mBuilder.arrowercentage) - arrowWidth / 2;
        arrowBottom.setLayoutParams(layoutParams);
        TriangleDrawable drawable = new TriangleDrawable(mBuilder.arrowdirection, mBuilder.bgColor);
        drawable.setBounds(arrowBottom.getLeft(), arrowBottom.getTop(), arrowBottom.getRight(), arrowBottom.getBottom());
        arrowBottom.setBackgroundDrawable(drawable);
    }

    private void resizeHeight(int recyclerviewHeight) {

        modifybgColor();
        int arrowHeght = arrowWidth;
        int topAndBottomHeight = mRadiusHeight * 2;
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

        if (mBuilder.arrowdirection == TOP) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) arrowTop.getLayoutParams();
            layoutParams.leftMargin = (int) (mBuilder.width * mBuilder.arrowercentage) - arrowWidth / 2;
            arrowTop.setLayoutParams(layoutParams);
            TriangleDrawable drawable = new TriangleDrawable(mBuilder.arrowdirection, mBuilder.bgColor);
            drawable.setBounds(arrowTop.getLeft(), arrowTop.getTop(), arrowTop.getRight(), arrowTop.getBottom());
            arrowTop.setBackgroundDrawable(drawable);
        }
        if (mBuilder.arrowdirection == IndicatorBuilder.BOTTOM) {
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
            params1.bottomMargin = arrowWidth + mRadiusHeight;
            recyclerView.setLayoutParams(params1);
        } else {
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
            params1.bottomMargin = mRadiusHeight;
            recyclerView.setLayoutParams(params1);
        }
        rootLayout.requestLayout();
        setSize2Dialog(result);

    }

    private void addArrow2LinearLayout() {
        arrowTop = new View(mContext);
        rootLayout.addView(arrowTop);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) arrowTop.getLayoutParams();
        layoutParams.width = arrowWidth;
        layoutParams.height = arrowWidth;
        arrowTop.setLayoutParams(layoutParams);
    }

    private void setSize2Dialog(int height) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        if (mBuilder.animator != 0) {
            dialogWindow.setWindowAnimations(mBuilder.animator);
        }
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if (mBuilder.gravity == IndicatorBuilder.GRAVITY_RIGHT) {
            gravity = Gravity.RIGHT | (mBuilder.arrowdirection == TOP ? Gravity.TOP : Gravity.BOTTOM);
        } else if (mBuilder.gravity == IndicatorBuilder.GRAVITY_LEFT) {
            gravity = Gravity.LEFT | (mBuilder.arrowdirection == TOP ? Gravity.TOP : Gravity.BOTTOM);
        } else {
            gravity = Gravity.CENTER_HORIZONTAL | (mBuilder.arrowdirection == TOP ? Gravity.TOP : Gravity.BOTTOM);
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

        int x = 0;
        if (mBuilder.gravity == IndicatorBuilder.GRAVITY_LEFT) {
            x = -1 * (int) (mBuilder.width * mBuilder.arrowercentage) + view.getWidth() / 2;
        } else if (mBuilder.gravity == IndicatorBuilder.GRAVITY_RIGHT) {
            x = -1 * (mBuilder.width - (int) (mBuilder.width * mBuilder.arrowercentage)) + view.getWidth() / 2;
        }
//        else {
//            x =-1 * (int) (mBuilder.width * mBuilder.arrowercentage) - view.getWidth() / 2;
//        }


        show(view, x, 0);

    }

    public void show(View view, int xOffset, int yOffset) {
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
        } else if ((gravity & Gravity.LEFT) == Gravity.LEFT) {
            x = location[0];
        } else {
            x = 0;
        }
        x += xOffset;
        if (x < 0) x = 0;
        if (mBuilder.arrowdirection == IndicatorBuilder.BOTTOM) {
            y = height - location[1];
        } else {
            y = location[1];
        }
        y += yOffset;
        if (y < 0) y = 0;
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

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public Dialog getDialog() {
        return mDialog;
    }


}
