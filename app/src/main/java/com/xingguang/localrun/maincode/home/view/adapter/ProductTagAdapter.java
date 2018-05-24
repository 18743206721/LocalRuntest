package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.model.ProductDetailsBean;
import java.util.ArrayList;


public class ProductTagAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ProductDetailsBean> list;
    private boolean isSelected = false;


    //设置修改
    public interface UpdateListener {
        void UpdateClick(int position);
    }

    UpdateListener updateListener;


    public void setUpdateClick(UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    public ProductTagAdapter(Context context, ArrayList<ProductDetailsBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tag_flow_tv, null);
            holder = new ViewHolder();
            holder.tv_tag = (TextView) convertView.findViewById(R.id.tag_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductDetailsBean lists = list.get(position);
//        holder.tv_tag.setText(lists.getName());

//        if ("1".equals(lists.getIsClick())) {
//            holder.tv_tag.setTextColor(mContext.getResources().getColor(R.color.theme_color));
//            holder.tv_tag.setBackgroundResource(R.drawable.checked_bg);
//        } else {
//            holder.tv_tag.setTextColor(mContext.getResources().getColor(R.color.textBlack));
//            holder.tv_tag.setBackgroundResource(R.drawable.normal_bg);
//        }
//
//        if (0 == lists.getStock()) {
//            holder.tv_tag.setEnabled(false);
//            holder.tv_tag.setTextColor(mContext.getResources().getColor(R.color.textLightGray));
//            holder.tv_tag.setBackgroundResource(R.drawable.normal_bg);
//        }

        holder.tv_tag.setTag(position);
        holder.tv_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(v.getTag().toString());
                if (updateListener != null) {
                    updateListener.UpdateClick(position);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView tv_tag;
    }


}
