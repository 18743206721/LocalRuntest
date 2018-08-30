package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.classify.model.ClassifBean;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;
import com.xingguang.localrun.view.RoundRectImageView;

import java.util.List;

/**
 * 创建日期：2018/5/25
 * 描述:
 * 作者:LiuYu
 */
public class ShopDianAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<ClassifBean.DataBean> list;
    private int type;

    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }

    public ShopDianAdapter(Context mContext, List<ClassifBean.DataBean> list, int type) {
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
        RoundRectImageView imageItem = holder.getItemView().findViewById(R.id.imageItem);
        ImageView item_iv_backimg1 = holder.getItemView().findViewById(R.id.item_iv_backimg1);
        ImageView item_iv_backimg2 = holder.getItemView().findViewById(R.id.item_iv_backimg2);
        ImageView item_iv_backimg3 = holder.getItemView().findViewById(R.id.item_iv_backimg3);

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(holder.getItemView(), position);
                }
            });
        }

        holder.setText(R.id.textItem, list.get(position).getShop_name()); //店名
        //logo
        ImageLoader.loadRoundImage(mContext, HttpManager.INDEX +
                list.get(position).getLogo(), imageItem, 5);

        //图片1 ,价格1
        ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX +
                list.get(position).getGoods().get(0).getOriginal_img(), item_iv_backimg1);
        holder.setText(R.id.tv_shop_price1, "¥"+list.get(position).getGoods().get(0).getShop_price());
        //图片2 ,价格2
        ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX +
                list.get(position).getGoods().get(1).getOriginal_img(), item_iv_backimg2);
        holder.setText(R.id.tv_shop_price2, "¥"+list.get(position).getGoods().get(1).getShop_price());
        //图片3 ,价格3
        ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX +
                list.get(position).getGoods().get(2).getOriginal_img(), item_iv_backimg3);
        holder.setText(R.id.tv_shop_price3, "¥"+list.get(position).getGoods().get(2).getShop_price());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ClassifBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }


}
