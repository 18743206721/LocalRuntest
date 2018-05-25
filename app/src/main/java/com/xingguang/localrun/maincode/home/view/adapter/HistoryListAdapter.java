package com.xingguang.localrun.maincode.home.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xingguang.localrun.R;

import java.util.List;

/**
 * 创建日期：2018/5/25
 * 描述:
 * 作者:LiuYu
 */
public class HistoryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDataList;
    private List<String> list;

    public HistoryListAdapter(Context mContext, List<String> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tag_layout, null);
            holder = new ViewHolder();
            holder.tv_tag = (TextView) convertView.findViewById(R.id.tag_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv_tag.setText(mDataList.get(position));
        return convertView;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void removeList(List<String> historList) {
        this.list = historList;
        historList.removeAll(list);
        notifyDataSetChanged();

    }

    class ViewHolder {
        TextView tv_tag;
    }


}
