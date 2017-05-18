package com.jiang.android.indicatordialogdemo;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;

import com.jiang.android.indicatordialog.IndicatorBuilder;
import com.jiang.android.indicatordialog.IndicatorDialog;

public class MainActivity extends AppCompatActivity {

    private View tv;
    private View tv1;
    private View tv2;
    private View tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (View) findViewById(R.id.add1);
        tv2 = (View) findViewById(R.id.add2);
        tv3 = (View) findViewById(R.id.add3);
        tv1.setClickable(true);
        tv2.setClickable(true);
        tv3.setClickable(true);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(tv1);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(tv2);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show3(tv3);
            }
        });
    }

    private void show3(View view) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        IndicatorDialog dialog = new IndicatorBuilder(this)
                .width(500)
                .height((int) (height * 0.5))
                .ArrowDirection(IndicatorBuilder.BOTTOM)
                .gravity(IndicatorBuilder.GRAVITY_LEFT)
                .ArrowRectage(0.1f)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(new BaseAdapter() {
                    @Override
                    public void onBindView(BaseViewHolder holder, int position) {

                    }

                    @Override
                    public int getLayoutID(int position) {
                        return R.layout.item;
                    }

                    @Override
                    public boolean clickable() {
                        return false;
                    }

                    @Override
                    public int getItemCount() {
                        return 50;
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(true)
                .show(view);
    }

    private void show(View view) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        IndicatorDialog dialog = new IndicatorBuilder(this)
                .width(500)
                .height((int) (height * 0.5))
                .ArrowDirection(IndicatorBuilder.TOP)
                .gravity(view.getId() == R.id.add1 ? IndicatorBuilder.GRAVITY_LEFT : IndicatorBuilder.GRAVITY_RIGHT)
                .ArrowRectage(view.getId() == R.id.add1 ? 0.1f : 0.8f)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(new BaseAdapter() {
                    @Override
                    public void onBindView(BaseViewHolder holder, int position) {

                    }

                    @Override
                    public int getLayoutID(int position) {
                        return R.layout.item;
                    }

                    @Override
                    public boolean clickable() {
                        return false;
                    }

                    @Override
                    public int getItemCount() {
                        return 100;
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(true)
                .show(view);
    }
}
