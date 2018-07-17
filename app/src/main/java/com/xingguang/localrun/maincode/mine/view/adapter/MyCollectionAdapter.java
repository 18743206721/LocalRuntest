package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.mine.model.MyCollectionBean;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/21
 * 描述:
 * 作者:LiuYu
 */
public class MyCollectionAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<MyCollectionBean.DataBean> list;

    public MyCollectionAdapter(Context mContext, List<MyCollectionBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_my_collection);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        ImageView item_iv_collect_img = holder.getItemView().findViewById(R.id.item_iv_collect_img);
        ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX+list.get(position).getOriginal_img(),item_iv_collect_img);
        holder.setText(R.id.item_tv_collec_title, list.get(position).getGoods_name());
        holder.setText(R.id.tv_collec_people,list.get(position).getCollect_count()+"人收藏");
        holder.setText(R.id.tv_collec_price,"¥"+list.get(position).getShop_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void setList(List<MyCollectionBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
