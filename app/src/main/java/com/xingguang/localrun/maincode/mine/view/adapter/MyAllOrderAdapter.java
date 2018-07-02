package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.mine.model.OrderBean;
import com.xingguang.localrun.maincode.mine.view.activity.PingLunActivity;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.List;

/**
 * 创建日期：2017/12/3
 * 描述:一起玩订单页
 * 作者:LiuYu
 */

public class MyAllOrderAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<OrderBean.DataBean.ListBean> list;
    private String type;
    private OnOrderCancel mOnOrdercancel;
    private OnOrderDeleted mOnOrderdeleted;
    private OnOrderPay mOnOrderpay;
    private OnOrderTrue mOnOrdertrue;


    public interface OnOrderCancel {
        void OnOrdercancel(TextView item_ordercancel, int position);
    }
    public interface OnOrderDeleted {
        void OnOrderdeleted(TextView item_orderdeleted, int position);
    }
    public interface OnOrderPay {
        void OnOrderpay(TextView item_orderpay, int position);
    }

    public interface OnOrderTrue {
        void OnOrdertrue(TextView item_ordertrue, int position);
    }

    public void setmOnOrdertrue(OnOrderTrue mOnOrdertrue) {
        this.mOnOrdertrue = mOnOrdertrue;
    }

    public void setmOnOrdercancel(OnOrderCancel mOnOrdercancel) {
        this.mOnOrdercancel = mOnOrdercancel;
    }

    public void setmOnOrderdeleted(OnOrderDeleted mOnOrderdeleted) {
        this.mOnOrderdeleted = mOnOrderdeleted;
    }

    public void setmOnOrderpay(OnOrderPay mOnOrderpay) {
        this.mOnOrderpay = mOnOrderpay;
    }


    public MyAllOrderAdapter(Context mContext, List<OrderBean.DataBean.ListBean> list, String type) {
        this.mContext = mContext;
        this.list = list;
        this.type = type;
    }

    public void setList(List<OrderBean.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_myall_order);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {

        LinearLayout item_ll_vsbtn = holder.getItemView().findViewById(R.id.item_ll_vsbtn);
        final TextView itemtv_order_cancel = holder.getItemView().findViewById(R.id.itemtv_order_cancel);
        final TextView itemtv_order_deleted = holder.getItemView().findViewById(R.id.itemtv_order_deleted);
        final TextView itemtv_pay_awaypay = holder.getItemView().findViewById(R.id.itemtv_pay_awaypay);
        final TextView itemtv_order_true = holder.getItemView().findViewById(R.id.itemtv_order_true);

        RecyclerView item_recyc = holder.getItemView().findViewById(R.id.item_recyc);

        //设置分割线
        View vline = holder.getItemView().findViewById(R.id.item_myorder_view);
        if (position == 0 ){
            vline.setVisibility(View.VISIBLE);
        }else {
            vline.setVisibility(View.GONE);
        }

        if (mOnOrdercancel != null) {//取消的点击
            itemtv_order_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnOrdercancel.OnOrdercancel(itemtv_order_cancel,position);
                }
            });
        }
        if (mOnOrderdeleted != null){//删除的点击
            itemtv_order_deleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnOrderdeleted.OnOrderdeleted(itemtv_order_deleted,position);
                }
            });
        }

        if (mOnOrderpay != null){//去支付的点击
            itemtv_pay_awaypay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnOrderpay.OnOrderpay(itemtv_pay_awaypay,position);
                }
            });
        }

        if (mOnOrdertrue != null){//确认收货的点击
            itemtv_order_true.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnOrdertrue.OnOrdertrue(itemtv_order_true,position);
                }
            });
        }


        MyAllOrderItemAdapter  itemAdapter = new MyAllOrderItemAdapter(mContext,list.get(position).getGoods(),type);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        item_recyc.setLayoutManager(manager);
        item_recyc.setAdapter(itemAdapter);


        //评论
        itemAdapter.setmOnOrdercomment(new MyAllOrderItemAdapter.OnOrderComment() {
            @Override
            public void OnOrderComment(TextView item_comment, int position) {


                mContext.startActivity(new Intent(mContext, PingLunActivity.class));
            }
        });

        if (type.equals("0")) {//全部
            if (list.get(position).getOrder_type() == 1){ //待支付
                //取消订单和付款按钮显示
                item_ll_vsbtn.setVisibility(View.VISIBLE);
                itemtv_order_cancel.setVisibility(View.VISIBLE); //取消订单
                itemtv_pay_awaypay.setVisibility(View.VISIBLE); // 支付
                itemtv_order_deleted.setVisibility(View.GONE);//删除
                itemtv_order_true.setVisibility(View.GONE);
            }else if (list.get(position).getOrder_type() == 2){ //待发货
                item_ll_vsbtn.setVisibility(View.GONE);
            }else if (list.get(position).getOrder_type() == 3){ //已完成
                item_ll_vsbtn.setVisibility(View.VISIBLE);
                itemtv_order_cancel.setVisibility(View.GONE);
                itemtv_pay_awaypay.setVisibility(View.GONE);
                itemtv_order_deleted.setVisibility(View.VISIBLE);
                itemtv_order_true.setVisibility(View.GONE);
            }else if (list.get(position).getOrder_type() == 4){ //待收货，确认订单按钮
                item_ll_vsbtn.setVisibility(View.VISIBLE);
                itemtv_order_cancel.setVisibility(View.GONE);
                itemtv_pay_awaypay.setVisibility(View.GONE);
                itemtv_order_deleted.setVisibility(View.GONE);
                itemtv_order_true.setVisibility(View.VISIBLE);
            }else if (list.get(position).getOrder_type() == 5){ //已取消
                item_ll_vsbtn.setVisibility(View.VISIBLE);
                itemtv_order_deleted.setVisibility(View.VISIBLE);
                itemtv_order_cancel.setVisibility(View.GONE);
                itemtv_pay_awaypay.setVisibility(View.GONE);
                itemtv_order_deleted.setVisibility(View.GONE);
                itemtv_order_true.setVisibility(View.GONE);
            }

        }else if ("1".equals(type)){//待付款
            //取消订单和付款按钮显示
            item_ll_vsbtn.setVisibility(View.VISIBLE);
            itemtv_order_cancel.setVisibility(View.VISIBLE); //取消订单
            itemtv_pay_awaypay.setVisibility(View.VISIBLE); // 支付
            itemtv_order_deleted.setVisibility(View.GONE);//删除
        }else if ("2".equals(type)){//待发货
            item_ll_vsbtn.setVisibility(View.GONE);
        }else {//已完成
            item_ll_vsbtn.setVisibility(View.VISIBLE);
            itemtv_order_cancel.setVisibility(View.GONE);
            itemtv_pay_awaypay.setVisibility(View.GONE);
            itemtv_order_deleted.setVisibility(View.VISIBLE);
        }

        holder.setText(R.id.item_tv_shop,list.get(position).getShop_name());//店名
        holder.setText(R.id.item_tv_daigoupr,"¥"+list.get(position).getShipping_price());//代购费
        holder.setText(R.id.item_tvsum_money,"¥"+list.get(position).getOrder_amount());//总计

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    
}
