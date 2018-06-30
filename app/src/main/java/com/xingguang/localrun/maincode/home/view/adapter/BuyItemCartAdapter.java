package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.CartBean;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/25
 * 描述:购买页面商品适配器
 * 作者:LiuYu
 */
public class BuyItemCartAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<CartBean.DataBean.CartlistBean.GoodsBean> list;
    private OnItemClickLitener mOnItemClickLitener;

    public void setList(List<CartBean.DataBean.CartlistBean.GoodsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public BuyItemCartAdapter(Context mContext, List<CartBean.DataBean.CartlistBean.GoodsBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_listitembuy);
        return commonViewHolder;
    }
    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.getItemView(),position);
                }
            });
        }
        ImageView imageView = holder.getItemView().findViewById(R.id.iv_back);
        ImageLoader.getInstance().initGlide(mContext).loadImage(
                HttpManager.INDEX+list.get(position).getOriginal_img(),imageView);
        holder.setText(R.id.tv_name,list.get(position).getGoods_name());//商品名字
        if (list.get(position).getSpec_key_name().equals("")){
            holder.setText(R.id.tv_shop_guige,"");//商品规格
        }else {
            holder.setText(R.id.tv_shop_guige,list.get(position).getSpec_key_name());//商品规格
        }
        holder.setText(R.id.tv_price,"¥"+list.get(position).getGoods_price());//商品价格
        holder.setText(R.id.tv_count,"x "+list.get(position).getGoods_num());//商品数量
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
