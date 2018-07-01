package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.mine.model.OrderBean;
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
    private OnOrderTwoPay mOnOrdertwopay;
    private OnOrderComment mOnOrdercomment;

    public interface OnOrderCancel {
        void OnOrdercancel(TextView item_ordercancel, int position);
    }
    public interface OnOrderDeleted {
        void OnOrderdeleted(TextView item_orderdeleted, int position);
    }
    public interface OnOrderPay {
        void OnOrderPay(TextView item_orderpay, int position);
    }
    public interface OnOrderTwoPay {
        void OnOrderTwoPay(TextView item_ordertwopay, int position);
    }
    public interface OnOrderComment {
        void OnOrderComment(TextView item_comment, int position);
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

    public void setmOnOrdertwopay(OnOrderTwoPay mOnOrdertwopay) {
        this.mOnOrdertwopay = mOnOrdertwopay;
    }

    public void setmOnOrdercomment(OnOrderComment mOnOrdercomment) {
        this.mOnOrdercomment = mOnOrdercomment;
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
        final TextView itemtv_order_twopay = holder.getItemView().findViewById(R.id.itemtv_order_twopay);
        final TextView itemtv_pay_awaypay = holder.getItemView().findViewById(R.id.itemtv_pay_awaypay);
        final TextView itemtv_comment = holder.getItemView().findViewById(R.id.itemtv_comment);
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
        if (mOnOrdertwopay != null){ //二次购买的点击
            itemtv_order_twopay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnOrdertwopay.OnOrderTwoPay(itemtv_order_twopay,position);
                }
            });
        }
        if (mOnOrderpay != null){//去支付的点击
            itemtv_pay_awaypay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnOrderpay.OnOrderPay(itemtv_pay_awaypay,position);
                }
            });
        }

        if (mOnOrdercomment != null){//评论的点击
            itemtv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnOrdercomment.OnOrderComment(itemtv_comment,position);
                }
            });
        }

        MyAllOrderItemAdapter  itemAdapter = new MyAllOrderItemAdapter(mContext,list.get(position).getGoods());
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        item_recyc.setLayoutManager(manager);
        item_recyc.setAdapter(itemAdapter);

        if (type.equals("0")) {//全部
//            if (list.get(position).getType().equals("1")){
//                item_ll_vsbtn.setVisibility(View.VISIBLE);
//                itemtv_order_cancel.setVisibility(View.VISIBLE);
//                itemtv_pay_awaypay.setVisibility(View.VISIBLE);
//                itemtv_order_deleted.setVisibility(View.GONE);
//                itemtv_comment.setVisibility(View.GONE);
//                itemtv_order_twopay.setVisibility(View.GONE);
//            }else if (list.get(position).getType().equals("2")){
//                item_ll_vsbtn.setVisibility(View.GONE);
//            }else {
//                item_ll_vsbtn.setVisibility(View.VISIBLE);
//                itemtv_order_cancel.setVisibility(View.GONE);
//                itemtv_pay_awaypay.setVisibility(View.GONE);
//                itemtv_order_deleted.setVisibility(View.VISIBLE);
//                itemtv_comment.setVisibility(View.VISIBLE);
//                itemtv_order_twopay.setVisibility(View.VISIBLE);
//            }

        }else if ("1".equals(type)){//待付款
            item_ll_vsbtn.setVisibility(View.VISIBLE);
            itemtv_order_cancel.setVisibility(View.VISIBLE);
            itemtv_pay_awaypay.setVisibility(View.VISIBLE);
            itemtv_order_deleted.setVisibility(View.GONE);
            itemtv_comment.setVisibility(View.GONE);
            itemtv_order_twopay.setVisibility(View.GONE);
        }else if ("2".equals(type)){//待发货
            item_ll_vsbtn.setVisibility(View.GONE);
        }else {//已完成
            item_ll_vsbtn.setVisibility(View.VISIBLE);
            itemtv_order_cancel.setVisibility(View.GONE);
            itemtv_pay_awaypay.setVisibility(View.GONE);
            itemtv_order_deleted.setVisibility(View.VISIBLE);
            itemtv_comment.setVisibility(View.VISIBLE);
            itemtv_order_twopay.setVisibility(View.VISIBLE);

        }

        holder.setText(R.id.item_tv_shop,list.get(position).getShop_name());//店名
        holder.setText(R.id.item_tv_daigoupr,"¥"+list.get(position).getShipping_price());//代购费
        holder.setText(R.id.item_tvsum_money,"¥"+list.get(position).getOrder_amount());//总计

//        RoundImageView myplay_iv_header = holder.getItemView().findViewById(R.id.myplay_iv_header);
//        ImageLoader.loadCircleImage(mContext,list.get(position).getAliianceImg(),myplay_iv_header);// 头像
//        holder.setText(R.id.itme_myplay_title,list.get(position).getAllianceName());//联盟名称
//        holder.setText(R.id.item_tv_myunion_title,list.get(position).getActivityName());//活动名称
//
//        ImageView item_iv_union_img = holder.getItemView().findViewById(R.id.item_iv_union_img);
//        ImageLoader.getInstance().initGlide(mContext).loadImage(list.get(position).getActivityImg(),item_iv_union_img);
//
//        holder.setText(R.id.item_tv_union_time,list.get(position).getActivityBeginTime()+"开始");
//        holder.setText(R.id.item_tvaddress,list.get(position).getActivityAddress()); // 地址
//        holder.setText(R.id.item_tv_count,"x"+list.get(position).getCount()); //数量
//        holder.setText(R.id.item_tv_danjiaprice,"¥ "+list.get(position).getPrice()+".00"); // 单价
//        holder.setText(R.id.item_tv_subcount,"共"+list.get(position).getCount()+"张票"); // 共几张票
//        //计算总价
//        if (!list.isEmpty()){
//            int a = Integer.parseInt(list.get(position).getCount());
//            double b = Double.parseDouble(list.get(position).getPrice());
//            holder.setText(R.id.item_tv_subpirce,"¥ "+a * b+"0");
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    
}
