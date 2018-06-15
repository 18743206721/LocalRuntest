package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.mine.model.ZujiBean;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/24
 * 描述: 足迹列表适配器
 * 作者:LiuYu
 */
public class MyFootPrinAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<ZujiBean.DataBean> list;
    private OnItemCancelClickLitener mOnItemCancelClickLitener;

    public MyFootPrinAdapter(Context mContext, List<ZujiBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }
    //取消关注
    public interface OnItemCancelClickLitener {
        void onItemCancelClick(TextView view, int position);
    }

    public void setOnItemCancelClickLitener(OnItemCancelClickLitener mOnItemCancelClickLitener) {
        this.mOnItemCancelClickLitener = mOnItemCancelClickLitener;
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
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        RecyclerView item_rv_pro = holder.getItemView().findViewById(R.id.item_rv_pro);
        final TextView item_tvdeleted = holder.getItemView().findViewById(R.id.item_tvdeleted);

        MyItemFootAdapter myItemFootAdapter = new MyItemFootAdapter(mContext,list.get(position).getGoods());
        GridLayoutManager mgr = new GridLayoutManager(mContext, 2);
        item_rv_pro.setLayoutManager(mgr);
        item_rv_pro.setAdapter(myItemFootAdapter);
        myItemFootAdapter.notifyDataSetChanged();

        //删除
        if (mOnItemCancelClickLitener != null) {
            item_tvdeleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemCancelClickLitener.onItemCancelClick(item_tvdeleted,position);
                }
            });
        }
        holder.setText(R.id.item_tv_footprint,list.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


    public void setList(List<ZujiBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
