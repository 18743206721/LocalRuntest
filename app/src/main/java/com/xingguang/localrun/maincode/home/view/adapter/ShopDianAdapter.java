package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/25
 * 描述:
 * 作者:LiuYu
 */
public class ShopDianAdapter extends RecyclerView.Adapter<CommonViewHolder>{

    private Context mContext;
    private List<String> list;
    private int type;

    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }
    public ShopDianAdapter(Context mContext, List<String> list, int type) {
        this.mContext = mContext;
        this.list = list;
        this.type = type;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_shop_dian);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        final TextView item_tv = holder.getItemView().findViewById(R.id.item_tv_inshop);
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            item_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(item_tv, position);
                }
            });
        }


//        holder.setText(R.id.item_tv_name, "好吃的蛋糕");

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public interface OnItemClickListener {
        void OnItemClick(TextView view, int position);
    }


}
