package com.xingguang.localrun.maincode.classify.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.mine.model.MineApplyBean;

import java.util.List;

/**
 * 基本功能：左侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class LeftListAdapter extends BaseAdapter {
    private Context context;
    private List<MineApplyBean.DataBean> list;
    boolean[] flagArray;

    public LeftListAdapter(Context context,List<MineApplyBean.DataBean> list,boolean[] flagArray) {
        this.context = context;
        this.list = list;
        this.flagArray = flagArray;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Holder holder = null;
        if (arg1 == null) {
            holder = new Holder();
            arg1 = LayoutInflater.from(context).inflate(R.layout.item_lvleft, null);
            holder.left_list_item = (TextView) arg1.findViewById(R.id.left_list_item);
            arg1.setTag(holder);
        } else {
            holder = (Holder) arg1.getTag();
        }
        holder.updataView(arg0);
        return arg1;
    }

    public int indexOf(String title) {
        return list.size();
    }

    public void setList(List<MineApplyBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private class Holder {
        private TextView left_list_item;

        public void updataView(final int position) {
            left_list_item.setText(list.get(position).getName());
            if (flagArray[position]) {
                left_list_item.setBackgroundColor(Color.rgb(255, 255, 255));
                left_list_item.setTextColor(ContextCompat.getColor(context,R.color.text_color_red));
            } else {
                left_list_item.setBackgroundColor(Color.TRANSPARENT);
                left_list_item.setTextColor(ContextCompat.getColor(context,R.color.white));
            }
        }

    }
}
