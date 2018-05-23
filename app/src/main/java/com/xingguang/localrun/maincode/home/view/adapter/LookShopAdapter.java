package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.model.GlideImageLoader;
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
    private List<String> list;
    private int type;
    LookShopItemAdapter adapter;

    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg"};
    private List<String> networkImages;
    private Banner banner;


    public LookShopAdapter(Context mContext, List<String> list, int type) {
        this.mContext = mContext;
        this.list = list;
        this.type = type;
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
    public void onBindViewHolder(CommonViewHolder holder, int position) {



        if (type == 1){
            RecyclerView item_rv_lookshop = holder.getItemView().findViewById(R.id.item_rv_lookshop);
            banner = holder.getItemView().findViewById(R.id.banner);
            Star iv_tice_star = holder.getItemView().findViewById(R.id.iv_tice_star);

//            iv_tice_star.setMark(Float.parseFloat(ticemodule.getMagicerLevel() + "f")/2);
            iv_tice_star.setMark(4f);//设置星星的充满度

            adapter = new LookShopItemAdapter(mContext,list,type);
            GridLayoutManager mgr = new GridLayoutManager(mContext, 2);
            item_rv_lookshop.setLayoutManager(mgr);
            item_rv_lookshop.setAdapter(adapter);

            initpage();

        }else if (type == 2){



        }else{


        }


    }

    private void initpage() {
        networkImages = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            networkImages.add(images[i]);
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(networkImages);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


    @Override
    public int getItemCount() {
        if (type==1){
            return 1;
        }
        return 5;
    }



}
