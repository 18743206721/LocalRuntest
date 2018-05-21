package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/21
 * 描述:
 * 作者:LiuYu
 */
public class MyAttentionAdapter extends RecyclerView.Adapter <CommonViewHolder>{

    private Context mContext;
    private List<String> list;
    private OnItemCancelClickLitener mOnItemCancelClickLitener;

    //取消关注
    public interface OnItemCancelClickLitener {
        void onItemCancelClick(TextView view, int position);
    }

    public void setOnItemCancelClickLitener(OnItemCancelClickLitener mOnItemCancelClickLitener) {
        this.mOnItemCancelClickLitener = mOnItemCancelClickLitener;
    }

    public MyAttentionAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_my_attention);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        View viewtop = holder.getItemView().findViewById(R.id.view_top);
        if (position == 0){
            viewtop.setVisibility(View.VISIBLE);
        }else{
            viewtop.setVisibility(View.GONE);
        }

        final TextView item_tv_cancel_attention = holder.getItemView().findViewById(R.id.item_tv_cancel_attention);

        if (mOnItemCancelClickLitener != null) {
            item_tv_cancel_attention.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemCancelClickLitener.onItemCancelClick(item_tv_cancel_attention,position);
                }
            });
        }

//        ImageLoader.loadCircleImage(mContext,list.get(position).getUserImg(),item_iv_avtuar); //图片
//        holder.setText(R.id.item_tv_headertitle,list.get(position).getUserName()); //店名

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
