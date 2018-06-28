package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.model.CartBean;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/25
 * 描述:购买页面适配器
 * 作者:LiuYu
 */
public class BuyCartAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<CartBean.DataBean.CartlistBean> list;

    public void setList(List<CartBean.DataBean.CartlistBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public BuyCartAdapter(Context mContext, List<CartBean.DataBean.CartlistBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_buy);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        final RecyclerView item_rv = holder.getItemView().findViewById(R.id.item_rv);
        BuyItemCartAdapter itemadapter = new BuyItemCartAdapter(mContext,list.get(position).getGoods());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        item_rv.setLayoutManager(linearLayoutManager);
        item_rv.setAdapter(itemadapter);

        //点击页面跳转详情
        itemadapter.setOnItemClickLitener(new BuyItemCartAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext, ProductdetailsActivity.class);
                intent.putExtra("goods_id",list.get(position).getGoods().get(i).getGoods_id());
                mContext.startActivity(intent);
            }
        });

        holder.setText(R.id.tv_shopname,list.get(position).getShop_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
