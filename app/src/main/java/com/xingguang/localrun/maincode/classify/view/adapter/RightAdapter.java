package com.xingguang.localrun.maincode.classify.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;

/**
 * 基本功能：右侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class RightAdapter extends BaseAdapter {

    private Context mContext;
    private String[] rightStr;
    private LayoutInflater inflater;

    public RightAdapter(Context context, String[] rightStr) {
        this.mContext = context;
        this.rightStr = rightStr;
    }

    public int getCount() {
        return rightStr.length;
    }

    @Override
    public Object getItem(int i) {
        return rightStr[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_lvright, parent, false);
            holder = new ViewHolder();
            holder.textItem = (TextView) convertView.findViewById(R.id.textItem);
            holder.item_tv_inshop = convertView.findViewById(R.id.item_tv_inshop);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textItem.setText(rightStr[position]);

        holder.item_tv_inshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, LookShopActivity.class));
            }
        });

        return convertView;
    }

    public int indexOf(String s) {
        return rightStr.length;
    }

    class ViewHolder {
        TextView textItem;
        TextView item_tv_inshop;
    }

}