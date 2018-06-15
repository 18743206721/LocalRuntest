package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.model.TjtaskBean;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

public class HomeDaiBanAdapter extends RecyclerView.Adapter <CommonViewHolder> {


    private Context mContext;
    private List<TjtaskBean.DataBean> list;

    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }

    public HomeDaiBanAdapter(Context mContext,List<TjtaskBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_home_daiban);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        final TextView item_tv_lianxi = holder.getItemView().findViewById(R.id.item_tv_lianxi);

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            item_tv_lianxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(item_tv_lianxi, position);
                }
            });
        }

        holder.setText(R.id.item_tv_name, list.get(position).getTitle());
        holder.setText(R.id.item_tvdes, list.get(position).getDescribe());
        holder.setText(R.id.tv_statrtime, list.get(position).getStart_time());
        holder.setText(R.id.tv_endtime, list.get(position).getEnd_time());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<TjtaskBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void OnItemClick(TextView view, int position);
    }



}
