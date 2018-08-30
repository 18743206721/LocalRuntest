package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.maincode.mine.model.ZujiBean;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/24
 * 描述:
 * 作者:LiuYu
 */
public class MyItemFootAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<ZujiBean.DataBean.GoodsBean> list;

    public MyItemFootAdapter(Context mContext, List<ZujiBean.DataBean.GoodsBean> list) {
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
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        ImageView item_iv_backimg = holder.getItemView().findViewById(R.id.item_iv_backimg);
        ImageLoader.getInstance().initGlide(mContext).loadImage(
                HttpManager.INDEX + list.get(position).getOriginal_img(), item_iv_backimg);
        holder.setText(R.id.tv_shop_price, "¥" + list.get(position).getShop_price());

        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, ProductdetailsActivity.class);
                intent.putExtra("goods_id",list.get(position).getGoods_id());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
