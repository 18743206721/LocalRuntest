package com.xingguang.localrun.maincode.classify.view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xingguang.localrun.R;

/**
 * 基本功能：右侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class MainSectionedAdapter extends BaseAdapter {

    private Context mContext;
    private String[] leftStr;
    private String[][] rightStr;
    private LayoutInflater inflater;

    public MainSectionedAdapter(Context context, String[] leftStr, String[][] rightStr) {
        this.mContext = context;
        this.leftStr = leftStr;
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.item_tv_history.setText(rightStr[position]);
        return convertView;
    }


    class ViewHolder {
        TextView textItem;
    }

}
