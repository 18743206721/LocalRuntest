package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.GlideImageLoader;
import com.xingguang.localrun.maincode.home.model.ShopAllGoodBean;
import com.xingguang.localrun.maincode.home.model.ShopBannerBean;
import com.xingguang.localrun.maincode.home.model.ShopIndex;
import com.xingguang.localrun.maincode.home.model.ShopJianjieBean;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;
import com.xingguang.localrun.view.Star;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2018/5/23
 * 描述:
 * 作者:LiuYu
 */
public class LookShopAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<ShopBannerBean.DataBean> bannerlist;
    private List<ShopIndex.DataBean> indexlist;
    private ShopJianjieBean.DataBean jianjieBean;
    private List<ShopAllGoodBean.DataBean> goodsList;
    private int type;
    LookShopItemAdapter adapter;
    private List<String> networkImages;
    private Banner banner;

    public LookShopAdapter(Context mContext, List<ShopBannerBean.DataBean> bannerlist, List<ShopIndex.DataBean> indexlist,
                           List<ShopAllGoodBean.DataBean> goodsList, ShopJianjieBean.DataBean jianjieBean, int type) {
        this.mContext = mContext;
        this.bannerlist = bannerlist;
        this.indexlist = indexlist;
        this.goodsList = goodsList;
        this.jianjieBean = jianjieBean;
        this.type = type;
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        if (type == 1) {
            commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_lookshop_all);
        } else if (type == 2) {
            commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_shop);
        } else {
            commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_shop);
        }
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {

        if (type == 1) {
            RecyclerView item_rv_lookshop = holder.getItemView().findViewById(R.id.item_rv_lookshop);
            banner = holder.getItemView().findViewById(R.id.banner);
            Star iv_tice_star = holder.getItemView().findViewById(R.id.iv_tice_star);
            TextView tv_baifenbi = holder.getItemView().findViewById(R.id.tv_baifenbi);
            TextView tv_attention = holder.getItemView().findViewById(R.id.tv_attention);

            //设置星星的充满度
            if (jianjieBean.getPercent() == 0){
                tv_baifenbi.setVisibility(View.GONE);
            }else {
                tv_baifenbi.setText(jianjieBean.getPercent()+"%");
                tv_baifenbi.setVisibility(View.VISIBLE);
                iv_tice_star.setMark(Float.parseFloat(  (5/100)*jianjieBean.getPercent()+"f"));
            }

            adapter = new LookShopItemAdapter(mContext, indexlist, type);
            GridLayoutManager mgr = new GridLayoutManager(mContext, 2);
            item_rv_lookshop.setLayoutManager(mgr);
            item_rv_lookshop.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            initpage(bannerlist);

            adapter.setOnItemClickListener(new LookShopItemAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, ProductdetailsActivity.class);
                    intent.putExtra("goods_id", indexlist.get(position).getGoods_id());
                    mContext.startActivity(intent);
//                    LookShopActivity activity = (LookShopActivity) mContext;
//                    activity.finish();
                }
            });

            //简介
            holder.setText(R.id.tv_name,jianjieBean.getShop_name());//店铺名字
            holder.setText(R.id.item_tv_time,jianjieBean.getJoin_time());//时间
            ImageView item_iv_zizhi = holder.getItemView().findViewById(R.id.item_iv_zizhi);//资质
            ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX+jianjieBean.getLicence(),item_iv_zizhi);
            ImageView iv_logo = holder.getItemView().findViewById(R.id.iv_logo);//logo
            ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX+jianjieBean.getLogo(),iv_logo);

            if (jianjieBean.getIs_collected() == 0){ //未关注
                holder.setText(R.id.tv_attention,"关注");
            }else{ //已关注
                holder.setText(R.id.tv_attention,"已关注");
            }

            tv_attention.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (jianjieBean.getIs_collected() == 0){ //未关注
                        holder.setText(R.id.tv_attention,"已关注");
                    }

                }
            });

        } else if (type == 2) {
            if (mOnItemClickListener != null) {
                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //注意这里使用getTag方法获取position
                        mOnItemClickListener.onItemClick(holder.getItemView(), position);
                    }
                });
            }
            for (int i = 0; i < goodsList.size(); i++) {
                ShopAllGoodBean.DataBean bean = goodsList.get(i);
                ImageView item_iv_backimg = holder.getItemView().findViewById(R.id.item_iv_backimg);
                ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX +
                        bean.getOriginal_img(), item_iv_backimg);
                holder.setText(R.id.tv_shop_price, "¥" + bean.getShop_price());
            }

        } else {

            if (mOnItemClickListener != null) {
                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //注意这里使用getTag方法获取position
                        mOnItemClickListener.onItemClick(holder.getItemView(), position);
                    }
                });
            }

            for (int i = 0; i < goodsList.size(); i++) {
                ShopAllGoodBean.DataBean bean = goodsList.get(i);
                ImageView item_iv_backimg = holder.getItemView().findViewById(R.id.item_iv_backimg);
                ImageLoader.getInstance().initGlide(mContext).loadImage(HttpManager.INDEX +
                        bean.getOriginal_img(), item_iv_backimg);
                holder.setText(R.id.tv_shop_price, "¥" + bean.getShop_price());
            }


        }


    }

    private void initpage(List<ShopBannerBean.DataBean> bannerlist) {
        networkImages = new ArrayList<>();
        for (int i = 0; i < bannerlist.size(); i++) {
            networkImages.add(HttpManager.INDEX + this.bannerlist.get(i).getImage());
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(networkImages);
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


    @Override
    public int getItemCount() {
        if (type == 1) {
            return 1;
        }else if (type == 2){
            return goodsList.size();
        }else{
            return goodsList.size();
        }
    }

    public void setList(List<ShopBannerBean.DataBean> bannerlist) {
        this.bannerlist = bannerlist;
        notifyDataSetChanged();
    }

    public void setListIndex(List<ShopIndex.DataBean> listIndex) {
        this.indexlist = listIndex;
        notifyDataSetChanged();
    }

    public void setgoodsList(List<ShopAllGoodBean.DataBean> goodsList) {
        this.goodsList = goodsList;
        notifyDataSetChanged();
    }

    public void setnewsgoodsList(List<ShopAllGoodBean.DataBean> newsgoodsList) {
        this.goodsList = newsgoodsList;
        notifyDataSetChanged();
    }

    public void setjianjie(ShopJianjieBean.DataBean jianjie) {
        this.jianjieBean = jianjie;
        notifyDataSetChanged();
    }


    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
