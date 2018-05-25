package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

public class HomeDaiGouAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<String> list;

    private OnItemClickListener mOnItemClickLitener;

//    private OnItemLookshopListener mOnItemLookshopLitener;
//
//    public void setmOnItemLookshopListener(OnItemLookshopListener mOnItemLookshopListener) {
//        this.mOnItemLookshopLitener = mOnItemLookshopListener;
//    }


    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }

    public HomeDaiGouAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_home_daigou);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(holder.getItemView(), position);
                }
            });
        }
        final TextView item_tv_look = holder.getItemView().findViewById(R.id.item_tv_look);

        holder.setText(R.id.item_tv_name, "好吃的蛋糕");

//        item_tv_look.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mOnItemLookshopLitener.OnItemLookshop(item_tv_look, position);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

//    public interface OnItemLookshopListener {
//        void OnItemLookshop(View view, int position);
//    }


}
