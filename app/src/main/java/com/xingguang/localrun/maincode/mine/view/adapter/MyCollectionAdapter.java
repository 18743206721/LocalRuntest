package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xingguang.localrun.R;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/21
 * 描述:
 * 作者:LiuYu
 */
public class MyCollectionAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<String> list;

    public MyCollectionAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_my_collection);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {

//        holder.setText(R.id.item_tv_name, "好吃的蛋糕");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

}
