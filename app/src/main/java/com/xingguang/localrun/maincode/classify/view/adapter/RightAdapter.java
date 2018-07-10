package com.xingguang.localrun.maincode.classify.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.classify.model.ClassifBean;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.RoundRectImageView;

import java.util.List;

/**
 * 基本功能：右侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class RightAdapter extends BaseAdapter {

    private Context mContext;
    private List<ClassifBean.DataBean> list;
    private LayoutInflater inflater;

    public RightAdapter(Context context, List<ClassifBean.DataBean> list) {
        this.mContext = context;
        this.list = list;
    }

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_lvright, parent, false);
            holder = new ViewHolder();
            holder.textItem = (TextView) convertView.findViewById(R.id.textItem);
            holder.item_tv_inshop = convertView.findViewById(R.id.item_tv_inshop);
            holder.imageItem = convertView.findViewById(R.id.imageItem);
            holder.iv1 = convertView.findViewById(R.id.iv1);
            holder.iv2 = convertView.findViewById(R.id.iv2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textItem.setText(list.get(position).getShop_name()); //店名字
        //商店logo
        ImageLoader.loadRoundImage(mContext, HttpManager.INDEX + list.get(position).getLogo(), holder.imageItem, 5);
        //图片1
        ImageLoader.getInstance().initGlide(mContext).loadImage(
                HttpManager.INDEX + list.get(position).getGoods().get(0).getOriginal_img(), holder.iv1);
        //图片2
        ImageLoader.getInstance().initGlide(mContext).loadImage(
                HttpManager.INDEX + list.get(position).getGoods().get(1).getOriginal_img(), holder.iv2);

        holder.item_tv_inshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, LookShopActivity.class)
                .putExtra("shopid",list.get(position).getId()));
            }
        });

        return convertView;
    }

    public void setList(List<ClassifBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView textItem;
        TextView item_tv_inshop;
        RoundRectImageView imageItem;
        ImageView iv1, iv2;


    }

}
