package com.jiang.android.indicatordialogdemo;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.android.indicatordialog.IndicatorBuilder;
import com.jiang.android.indicatordialog.IndicatorDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<String> mLists = new ArrayList<>();
    private List<Integer> mICons = new ArrayList<>();
    private ImageView mAdd;
    private ImageView mAddLeft;
    private ImageView mAddCenter;
    private ImageView mBottom3;
    private ImageView mBottom2;
    private ImageView mBottom1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        mAddLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTopDialog(v, 0.1f, IndicatorBuilder.GRAVITY_LEFT);
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTopDialog(v, 0.9f, IndicatorBuilder.GRAVITY_RIGHT);
            }
        });

        mAddCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopDialog(v, 0.5f, IndicatorBuilder.GRAVITY_CENTER);
            }
        });


        mBottom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v, 0.1f, IndicatorBuilder.GRAVITY_LEFT);

            }
        });
        mBottom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v, 0.5f, IndicatorBuilder.GRAVITY_CENTER);

            }
        });
        mBottom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v, 0.9f, IndicatorBuilder.GRAVITY_RIGHT);

            }
        });
    }

    private void showBottomDialog(View v, float v1, int gravityCenter) {
        mLists.clear();
        mICons.clear();
        mLists.add("创建群聊");
        mICons.add(R.drawable.ic_chat_bubble_outline_black_24dp_white);
        mLists.add("加好友");
        mICons.add(R.drawable.ic_child_friendly_black_24dp_white);
        mLists.add("扫一扫");
        mICons.add(R.drawable.ic_settings_bluetooth_black_24dp_white);
        mLists.add("面对面快传");
        mICons.add(R.drawable.ic_autorenew_black_24dp_white);
        mLists.add("付款");
        mICons.add(R.drawable.ic_monetization_on_black_24dp_white);
        mLists.add("拍摄");
        mICons.add(R.drawable.ic_camera_black_24dp_white);
        mLists.add("面对面红包");
        mICons.add(R.drawable.ic_attach_money_black_24dp_white);
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        IndicatorDialog dialog = new IndicatorBuilder(this)
                .width(400)
                .height((int) (height * 0.5))
                .ArrowDirection(IndicatorBuilder.BOTTOM)
                .bgColor(Color.parseColor("#49484b"))
                .gravity(gravityCenter)
                .ArrowRectage(v1)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(new BaseAdapter() {
                    @Override
                    public void onBindView(BaseViewHolder holder, int position) {
                        TextView tv = holder.getView(R.id.item_add);
                        tv.setText(mLists.get(position));
                        tv.setCompoundDrawablesWithIntrinsicBounds(mICons.get(position), 0, 0, 0);

                        if (position == mLists.size() - 1) {
                            holder.setVisibility(R.id.item_line, BaseViewHolder.GONE);
                        } else {
                            holder.setVisibility(R.id.item_line, BaseViewHolder.VISIBLE);

                        }
                    }

                    @Override
                    public int getLayoutID(int position) {
                        return R.layout.item_2;
                    }

                    @Override
                    public boolean clickable() {
                        return false;
                    }

                    @Override
                    public int getItemCount() {
                        return mLists.size();
                    }
                }).create();

        dialog.setCanceledOnTouchOutside(true);
        dialog.show(v);
    }


    private void showTopDialog(View v, float v1, int gravityCenter) {
        mLists.clear();
        mICons.clear();
        mLists.add("创建群聊");
        mICons.add(R.drawable.ic_chat_bubble_outline_black_24dp);
        mLists.add("加好友");
        mICons.add(R.drawable.ic_child_friendly_black_24dp);
        mLists.add("扫一扫");
        mICons.add(R.drawable.ic_settings_bluetooth_black_24dp);
        mLists.add("面对面快传");
        mICons.add(R.drawable.ic_autorenew_black_24dp);
        mLists.add("付款");
        mICons.add(R.drawable.ic_monetization_on_black_24dp);
        mLists.add("拍摄");
        mICons.add(R.drawable.ic_camera_black_24dp);
        mLists.add("面对面红包");
        mICons.add(R.drawable.ic_attach_money_black_24dp);
        mLists.add("创建群聊");
        mICons.add(R.drawable.ic_chat_bubble_outline_black_24dp);
        mLists.add("加好友");
        mICons.add(R.drawable.ic_child_friendly_black_24dp);
        mLists.add("扫一扫");
        mICons.add(R.drawable.ic_settings_bluetooth_black_24dp);
        mLists.add("面对面快传");
        mICons.add(R.drawable.ic_autorenew_black_24dp);
        mLists.add("付款");
        mICons.add(R.drawable.ic_monetization_on_black_24dp);
        mLists.add("拍摄");
        mICons.add(R.drawable.ic_camera_black_24dp);
        mLists.add("面对面红包");
        mICons.add(R.drawable.ic_attach_money_black_24dp);
        mLists.add("创建群聊");
        mICons.add(R.drawable.ic_chat_bubble_outline_black_24dp);
        mLists.add("加好友");
        mICons.add(R.drawable.ic_child_friendly_black_24dp);
        mLists.add("扫一扫");
        mICons.add(R.drawable.ic_settings_bluetooth_black_24dp);
        mLists.add("面对面快传");
        mICons.add(R.drawable.ic_autorenew_black_24dp);
        mLists.add("付款");
        mICons.add(R.drawable.ic_monetization_on_black_24dp);
        mLists.add("拍摄");
        mICons.add(R.drawable.ic_camera_black_24dp);
        mLists.add("面对面红包");
        mICons.add(R.drawable.ic_attach_money_black_24dp);
        mLists.add("创建群聊");
        mICons.add(R.drawable.ic_chat_bubble_outline_black_24dp);
        mLists.add("加好友");
        mICons.add(R.drawable.ic_child_friendly_black_24dp);
        mLists.add("扫一扫");
        mICons.add(R.drawable.ic_settings_bluetooth_black_24dp);
        mLists.add("面对面快传");
        mICons.add(R.drawable.ic_autorenew_black_24dp);
        mLists.add("付款");
        mICons.add(R.drawable.ic_monetization_on_black_24dp);
        mLists.add("拍摄");
        mICons.add(R.drawable.ic_camera_black_24dp);
        mLists.add("面对面红包");
        mICons.add(R.drawable.ic_attach_money_black_24dp);
        mLists.add("创建群聊");
        mICons.add(R.drawable.ic_chat_bubble_outline_black_24dp);
        mLists.add("加好友");
        mICons.add(R.drawable.ic_child_friendly_black_24dp);
        mLists.add("扫一扫");
        mICons.add(R.drawable.ic_settings_bluetooth_black_24dp);
        mLists.add("面对面快传");
        mICons.add(R.drawable.ic_autorenew_black_24dp);
        mLists.add("付款");
        mICons.add(R.drawable.ic_monetization_on_black_24dp);
        mLists.add("拍摄");
        mICons.add(R.drawable.ic_camera_black_24dp);
        mLists.add("面对面红包");
        mICons.add(R.drawable.ic_attach_money_black_24dp);
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        IndicatorDialog dialog = new IndicatorBuilder(this)
                .width(400)
                .animator(R.style.dialog_exit)
                .height((int) (height * 0.5))
                .ArrowDirection(IndicatorBuilder.TOP)
                .bgColor(Color.WHITE)
                .gravity(gravityCenter)
                .ArrowRectage(v1)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(new BaseAdapter() {
                    @Override
                    public void onBindView(BaseViewHolder holder, int position) {
                        TextView tv = holder.getView(R.id.item_add);
                        tv.setText(mLists.get(position));
                        tv.setCompoundDrawablesWithIntrinsicBounds(mICons.get(position), 0, 0, 0);

                        if (position == mLists.size() - 1) {
                            holder.setVisibility(R.id.item_line, BaseViewHolder.GONE);
                        } else {
                            holder.setVisibility(R.id.item_line, BaseViewHolder.VISIBLE);

                        }
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
                        return mLists.size();
                    }
                }).create();

        dialog.setCanceledOnTouchOutside(true);
        dialog.show(v);

    }

    private void initView() {
        mAdd = $(R.id.activity_add);
        mAddLeft = $(R.id.activity_add_left);
        mAddCenter = $(R.id.activity_add_center);
        mBottom1 = $(R.id.activity_add_bottom_1);
        mBottom2 = $(R.id.activity_add_bottom_2);
        mBottom3 = $(R.id.activity_add_bottom_3);
        mAdd.setClickable(true);
        mAddLeft.setClickable(true);
        mAddCenter.setClickable(true);
        mBottom1.setClickable(true);
        mBottom2.setClickable(true);
        mBottom3.setClickable(true);
    }


    public <T> T $(int id) {
        return (T) findViewById(id);
    }

}
