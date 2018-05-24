package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.mine.model.AddressModel;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/3.
 */

public class AddressManagementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private ArrayList<AddressModel> myContractList;
    Context mContext;
    private OnItem onItem;

    public void setOnItem(OnItem onItem) {
        this.onItem = onItem;
    }

    //设置默认
    public interface DefaultListener {
        void SubClick(int position, String user, String address, String id);
    }

    DefaultListener mListener;


    public void setDefaultClick(DefaultListener mListener) {
        this.mListener = mListener;
    }

    //行点击
    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //设置修改
    public interface UpdateListener {
        void UpdateClick(int position);
    }

    UpdateListener updateListener;


    public void setUpdateClick(UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    //设置删除
    public interface DeteleListener {
        void DeteleClick(int position);
    }

    DeteleListener deteleListener;


    public void setDeteleClick(DeteleListener deteleListener) {
        this.deteleListener = deteleListener;
    }

    public AddressManagementAdapter(ArrayList<AddressModel> myContractList, Context mContext) {
        this.myContractList = myContractList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_address_management, parent, false);
        view.setOnClickListener(this);
        return new ListviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ListviewHolder) {//HomeList布局
            ListviewHolder mHolder = (ListviewHolder) holder;
            mHolder.itemView.setTag(position);

            mHolder.name.setText(myContractList.get(position).getName());
            mHolder.phone.setText(myContractList.get(position).getPhone());
            mHolder.address.setText(myContractList.get(position).getProv() + myContractList.get(position).getCity() +
                    myContractList.get(position).getDist() + myContractList.get(position).getAddress());

//            if ("0".equals(myContractList.get(position).getDefAddress())) {
//                mHolder.default_address_img.setImageResource(R.mipmap.nochoice_btn_gray);
//            } else {
//                mHolder.default_address_img.setImageResource(R.mipmap.choice_btn_green);
//            }

            mHolder.defaultAddress.setTag(position);

            mHolder.defaultAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(v.getTag().toString());
                    if (mListener != null) {
                        mListener.SubClick(position,myContractList.get(position).getName()+"      "+myContractList.get(position).getPhone()
                                ,myContractList.get(position).getProv() + myContractList.get(position).getCity() +
                                        myContractList.get(position).getDist() + myContractList.get(position).getAddress(),
                                myContractList.get(position).getId());
                    }
                }
            });

            mHolder.detele.setTag(position);
            mHolder.detele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(v.getTag().toString());
                    if (deteleListener != null) {
                        deteleListener.DeteleClick(position);
                    }
                }
            });

            mHolder.update.setTag(position);
            mHolder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(v.getTag().toString());
                    if (updateListener != null) {
                        updateListener.UpdateClick(position);
                    }
                }
            });
            mHolder.ll_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItem.onItem(position,myContractList.get(position).getName()+"      "+myContractList.get(position).getPhone()
                    ,myContractList.get(position).getProv() + myContractList.get(position).getCity() +
                                    myContractList.get(position).getDist() + myContractList.get(position).getAddress());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return myContractList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, Integer.parseInt(view.getTag().toString()));
        }
    }


    /**
     * HomeList布局
     */
    class ListviewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, address;
        LinearLayout defaultAddress,ll_parent;
        ImageView update, detele, default_address_img;

        public ListviewHolder(View itemView) {
            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.name);
//            phone = (TextView) itemView.findViewById(R.id.phone);
//            address = (TextView) itemView.findViewById(R.id.address);
//            defaultAddress = (LinearLayout) itemView.findViewById(R.id.default_address);
//            ll_parent = (LinearLayout) itemView.findViewById(R.id.ll_parent);
//            update = (ImageView) itemView.findViewById(R.id.update);
//            detele = (ImageView) itemView.findViewById(R.id.detele);
//            default_address_img = (ImageView) itemView.findViewById(R.id.default_address_img);

        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    //别的页面添加回显地址信息
    public interface OnItem{
        void onItem(int position, String user, String address);
    }

}