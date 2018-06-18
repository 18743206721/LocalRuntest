package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.ShopIndex;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/23
 * 描述:
 * 作者:LiuYu
 */
public class LookShopItemAdapter  extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<ShopIndex.DataBean> indexlist;
    private int type;

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public LookShopItemAdapter(Context mContext, List<ShopIndex.DataBean> indexlist, int type) {
        this.mContext = mContext;
        this.indexlist = indexlist;
        this.type = type;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_shop);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {

        if (mOnItemClickListener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //注意这里使用getTag方法获取position
                    mOnItemClickListener.onItemClick(holder.getItemView(), position);
                }
            });
        }
        ImageView item_iv_backimg = holder.getItemView().findViewById(R.id.item_iv_backimg);
        ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX+
        indexlist.get(position).getOriginal_img(),item_iv_backimg);
        holder.setText(R.id.tv_shop_price,"¥"+indexlist.get(position).getShop_price());
    }

    @Override
    public int getItemCount() {
        return indexlist.size();
    }

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
