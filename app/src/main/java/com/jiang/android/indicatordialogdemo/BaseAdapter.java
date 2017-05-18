package com.jiang.android.indicatordialogdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jiang on 16/8/29.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {


        if (clickable()) {
            holder.getConvertView().setClickable(true);
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(v, position);
                }
            });
        }

        onBindView(holder, holder.getLayoutPosition());

    }

    public abstract void onBindView(BaseViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        return getLayoutID(position);
    }


    public abstract int getLayoutID(int position);

    public abstract boolean clickable();

    public void onItemClick(View v, int position) {
    }



}