package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.mine.model.MineApplyBean;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2018/5/21
 * 描述:店铺分类的adapter
 * 作者:LiuYu
 */
public class MineApplyAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<MineApplyBean.DataBean> list;
    private List<MineApplyBean.DataBean> selectList = new ArrayList<>();
    private boolean isSelected = false;

    public List<MineApplyBean.DataBean> getSelectList() {
        return selectList;
    }

    public MineApplyAdapter(Context mContext, List<MineApplyBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_my_apply);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        final LinearLayout ll_applys = holder.getItemView().findViewById(R.id.ll_applys);
        final ImageView checkBox = holder.getItemView().findViewById(R.id.check_box);
        holder.setText(R.id.item_tvname, list.get(position).getName());
        ll_applys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = !ll_applys.isSelected();
                if (isSelected) {
                    ll_applys.setSelected(true);
                    checkBox.setImageResource(R.mipmap.ic_checked);
                    selectList.add(list.get(position));
                    setSelectList(selectList);
                } else {
                    if (list.size() != 0) {
                        ll_applys.setSelected(false);
                        checkBox.setImageResource(R.mipmap.ic_uncheck);
                        selectList.remove(list.get(position));
                        setSelectList(selectList);
                    }
                }
            }
        });

    }

    public void setSelectList(List<MineApplyBean.DataBean> selectList) {
        if (selectList.size() != 0) {
            this.selectList = selectList;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MineApplyBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
