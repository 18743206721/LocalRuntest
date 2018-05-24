package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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
public class MyFootPrinAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<String> list;

    public MyFootPrinAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_my_footprint);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        RecyclerView item_rv_pro = holder.getItemView().findViewById(R.id.item_rv_pro);

        MyItemFootAdapter myItemFootAdapter = new MyItemFootAdapter(mContext);
        GridLayoutManager mgr = new GridLayoutManager(mContext, 2);
        item_rv_pro.setLayoutManager(mgr);
        item_rv_pro.setAdapter(myItemFootAdapter);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


}
