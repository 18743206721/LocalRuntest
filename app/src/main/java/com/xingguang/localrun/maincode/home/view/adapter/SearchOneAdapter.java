package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.SearchOneBean;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2018/5/25
 * 描述:搜索商品adapter
 * 作者:LiuYu
 */
public class SearchOneAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<SearchOneBean.DataBeanX.DataBean> list;

    private OnItemClickListener mOnItemClickLitener;
    private OnItemaddcarClickListener mOnItemaddcarClickLitener;

    public void setmOnItemaddcarClickLitener(OnItemaddcarClickListener mOnItemaddcarClickLitener) {
        this.mOnItemaddcarClickLitener = mOnItemaddcarClickLitener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }
    public SearchOneAdapter(Context mContext, List<SearchOneBean.DataBeanX.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
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
        ImageView item_ivmr_orderimg = holder.getItemView().findViewById(R.id.item_ivmr_orderimg);
        holder.setText(R.id.item_tvmr_shopname,list.get(position).getGoods_name()); //名字
        holder.setText(R.id.tv_pro_price,"¥ "+list.get(position).getShop_price()); //价格
        holder.setText(R.id.item_tvrm_people,list.get(position).getSale_num()+"人购买");
        ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX+list.get(position).getOriginal_img(),
                item_ivmr_orderimg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setaList(List<SearchOneBean.DataBeanX.DataBean> aList) {
        this.list = aList;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }
    public interface OnItemaddcarClickListener {
        void OnItemaddcarClick(TextView view, int position);
    }

}
