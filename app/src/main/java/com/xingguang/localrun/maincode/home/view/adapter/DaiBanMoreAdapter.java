package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.model.DaiBanMoreBean;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/25
 * 描述:
 * 作者:LiuYu
 */
public class DaiBanMoreAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<DaiBanMoreBean.DataBean> list;
    private OnItemClickLitener mOnItemClickLitener;

    public void setList(List<DaiBanMoreBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickLitener {
        void onItemClick(TextView view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public DaiBanMoreAdapter(Context mContext, List<DaiBanMoreBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_my_daibanmore);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {

        final TextView item_click_jin = holder.getItemView().findViewById(R.id.item_click_jin);
        if (mOnItemClickLitener != null) {
            item_click_jin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(item_click_jin,position);
                }
            });
        }

        holder.setText(R.id.item_tv_ads,list.get(position).getDescribe());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
