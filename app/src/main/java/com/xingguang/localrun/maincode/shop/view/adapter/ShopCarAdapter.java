package com.xingguang.localrun.maincode.shop.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.shop.model.GoodInfo;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

public class ShopCarAdapter extends RecyclerView.Adapter <CommonViewHolder> {

    private Context mContext;
    private List<GoodInfo> list;
    private boolean isShow = true;//是否显示编辑/完成

    private OnItemClickListener mOnItemClickLitener;

    //修改
    private OnItemEditListener mOnItemEditLitener;
    public void setmOnItemEditListener(OnItemEditListener mOnItemEditListener) {
        this.mOnItemEditLitener = mOnItemEditListener;
    }
    //删除
    private OnItemDeletedListener mOnItemDeletedLitener;
    public void setmOnItemDeletedListener(OnItemDeletedListener mOnItemDeletedListener) {
        this.mOnItemDeletedLitener = mOnItemDeletedListener;
    }

    //选中
    private CheckInterface checkInterface;
    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickLitener = mOnItemClickListener;
    }

    public ShopCarAdapter(Context mContext,List<GoodInfo> mList) {
        this.mContext = mContext;
        this.list = mList;
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_shopcar);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        final TextView tv_edit = holder.getItemView().findViewById(R.id.tv_shop_xiugai);
        final TextView tv_shop_deleted = holder.getItemView().findViewById(R.id.tv_shop_deleted);
        final CheckBox check_box = holder.getItemView().findViewById(R.id.check_box);

        final GoodInfo shoppingCartBean = list.get(position);

        boolean choosed = shoppingCartBean.isChoose();
        if (choosed){
            check_box.setChecked(true);
        }else{
            check_box.setChecked(false);
        }

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(holder.getItemView(), position);
                }
            });
        }
        //修改
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemEditLitener.OnItemEdit(tv_edit,position);
            }
        });
        //删除
        tv_shop_deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemDeletedLitener.OnItemDeleted(tv_shop_deleted,position);
            }
        });
        check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartBean.setChoose(((CheckBox) v).isChecked());
                checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
            }
        });

        //判断是否在编辑状态下
        if (isShow) {//编辑
            tv_edit.setVisibility(View.VISIBLE);
            tv_shop_deleted.setVisibility(View.GONE);
        } else { //完成
            tv_edit.setVisibility(View.GONE);
            tv_shop_deleted.setVisibility(View.VISIBLE);
        }


        holder.setText(R.id.tv_intro, shoppingCartBean.getName());
        holder.setText(R.id.tv_shop_count,shoppingCartBean.getCount());
        holder.setText(R.id.tv_shop_price, String.valueOf(shoppingCartBean.getPrice()));




    }





    /**
     * 是否显示可编辑
     *
     * @param flag
     */
    public void isShow(boolean flag) {
        isShow = flag;
        notifyDataSetChanged();
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<GoodInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    public interface OnItemEditListener {
        void OnItemEdit(TextView edit, int position);
    }

    public interface OnItemDeletedListener {
        void OnItemDeleted(TextView deleted, int position);
    }

    public interface OnItemCheckListener {
        void OnItemCheck(CheckBox check, int position);
    }


}
