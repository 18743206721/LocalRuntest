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
public class SearchResultAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<String> list;
    private String type;
    private List<String> aList;

    private OnItemClickListener mOnItemClickLitener;
    private OnItemaddcarClickListener mOnItemaddcarClickLitener;

    public void setmOnItemaddcarClickLitener(OnItemaddcarClickListener mOnItemaddcarClickLitener) {
        this.mOnItemaddcarClickLitener = mOnItemaddcarClickLitener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }
    public SearchResultAdapter(Context mContext, List<String> list, String type) {
        this.mContext = mContext;
        this.list = list;
        this.type = type;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_my_prosearch);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        final TextView item_tv_addcar = holder.getItemView().findViewById(R.id.item_tv_addcar);
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(holder.getItemView(), position);
                }
            });
        }
        //加入购物车
        item_tv_addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemaddcarClickLitener.OnItemaddcarClick(item_tv_addcar, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void setaList(List<String> aList) {
        this.aList = aList;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }
    public interface OnItemaddcarClickListener {
        void OnItemaddcarClick(TextView view, int position);
    }

}
