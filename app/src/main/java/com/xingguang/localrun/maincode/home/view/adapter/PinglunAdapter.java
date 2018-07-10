package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.PingJiaBean;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;
import com.xingguang.localrun.view.RoundImageView;
import com.xingguang.localrun.view.RoundRectImageView;

import java.util.List;

/**
 * dialog适配器
 */
public class PinglunAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private Context mContext;
    private List<PingJiaBean.DataBean> list;

    public PinglunAdapter(Context context, List<PingJiaBean.DataBean> mDatas) {
        this.mContext = context;
        this.list = mDatas;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_pinglun);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        RoundRectImageView item_img1 = holder.getItemView().findViewById(R.id.item_img1);
        RoundRectImageView item_img2 = holder.getItemView().findViewById(R.id.item_img2);
        RoundRectImageView item_img3 = holder.getItemView().findViewById(R.id.item_img3);
        RoundImageView iv_userimg = holder.getItemView().findViewById(R.id.iv_userimg);

        holder.setText(R.id.tv_time, AppUtil.times(list.get(position).getAdd_time())); //时间
        holder.setText(R.id.tv_name, list.get(position).getUsername());
        holder.setText(R.id.tv_content, list.get(position).getContent());//内容
        for (int i = 0; i < list.get(position).getImg().size(); i++) {
            if (i == 0) {
                ImageLoader.loadRoundImage(mContext, HttpManager.INDEX+list.get(position).getImg().get(0), item_img1, 5);
            } else if (i == 1) {
                ImageLoader.loadRoundImage(mContext, HttpManager.INDEX+list.get(position).getImg().get(1), item_img2, 5);
            } else if (i == 2) {
                ImageLoader.loadRoundImage(mContext, HttpManager.INDEX+list.get(position).getImg().get(2), item_img3, 5);
            }
        }
        ImageLoader.loadCircleImage(mContext,HttpManager.INDEX+ list.get(position).getAvatar(),iv_userimg);


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setList(List<PingJiaBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
