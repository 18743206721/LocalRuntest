package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xingguang.localrun.R;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/24
 * 描述:
 * 作者:LiuYu
 */
public class MyItemFootAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<String> list;

    public MyItemFootAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_shop);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }



}
