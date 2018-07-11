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
import com.xingguang.localrun.maincode.mine.model.JsonBean;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.GetJsonDataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class AddressManagementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<AddressModel.DataBean> myContractList;
    Context mContext;
    private OnItem onItem;
    private List<AddressModel.DataBean> remove;


    public void setList(List<AddressModel.DataBean> list) {
        this.myContractList = list;
        notifyDataSetChanged();
    }

    public void setRemove(List<AddressModel.DataBean> remove, int position) {
        this.myContractList = remove;
        myContractList.remove(position);
        notifyDataSetChanged();
    }

    //设置默认
    public interface DefaultListener {
        void SubClick(ImageView imageView, String isdefult, int position);
    }

    DefaultListener mListener;


    public void setDefaultClick(DefaultListener mListener) {
        this.mListener = mListener;
    }

    //行点击
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    //行点击
    public interface OnItemClickLitener {
        void onItemClick(ImageView imageView, String isdefult,int position);
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

    public AddressManagementAdapter(List<AddressModel.DataBean> myContractList, Context mContext) {
        this.myContractList = myContractList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_address_management, parent, false);
//        view.setOnClickListener(this);
        return new ListviewHolder(view);
    }

    String province;
    String city;
    String area;

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ListviewHolder) {//HomeList布局
            final ListviewHolder mHolder = (ListviewHolder) holder;
            mHolder.itemView.setTag(position);

            mHolder.name.setText(myContractList.get(position).getConsignee()); //设置姓名
            mHolder.phone.setText(myContractList.get(position).getMobile());

            String JsonData = new GetJsonDataUtil().getJson(mContext, "region.json");//获取assets目录下的json文件数据
            ArrayList<JsonBean> jsonBean = AppUtil.parseData(JsonData);//用Gson 转成实体
             List<JsonBean> options1Items = jsonBean;
            for (int i = 0; i < jsonBean.size(); i++) { //遍历省份
                if (myContractList.get(position).getProvince()
                        .equals(options1Items.get(i).getId())){
                    province = options1Items.get(i).getName();
                }
                for (int c = 0; c < jsonBean.get(i).getChild().size(); c++) {  //遍历该省份的所有城市
                    if (myContractList.get(position).getCity()
                            .equals(options1Items.get(i).getChild().get(c).getId())){
                        city = options1Items.get(i).getChild().get(c).getName();
                    }
                    for (int d = 0; d < jsonBean.get(i).getChild().get(c).getChild().size(); d++) {
                        if (myContractList.get(position).getArea()
                                .equals(options1Items.get(i).getChild().get(c).getChild().get(d).getId())){
                            area = options1Items.get(i).getChild().get(c).getChild().get(d).getName();
                        }
                    }
                }
            }

            mHolder.address.setText(province+city+area + myContractList.get(position).getAddress());

            if ("0".equals(myContractList.get(position).getIs_default())) {
                mHolder.default_address_img.setImageResource(R.mipmap.ic_uncheck);
            } else {
                mHolder.default_address_img.setImageResource(R.mipmap.ic_checked);
            }

            mHolder.defaultAddress.setTag(position);
            mHolder.defaultAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(v.getTag().toString());
                    if (mListener != null) {
                        mListener.SubClick(mHolder.default_address_img,myContractList.get(position).getIs_default(),position);
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
            mHolder.ll_parent.setTag(position);
            mHolder.ll_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickLitener != null) {
                        //注意这里使用getTag方法获取position
                        mOnItemClickLitener.onItemClick(mHolder.default_address_img,myContractList.get(position).getIs_default(),position);
                    }

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

    /**
     * HomeList布局
     */
    class ListviewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, address,update,detele;
        LinearLayout defaultAddress,ll_parent;
        ImageView default_address_img;

        public ListviewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            address = (TextView) itemView.findViewById(R.id.address);
            defaultAddress = (LinearLayout) itemView.findViewById(R.id.default_address);
            ll_parent = (LinearLayout) itemView.findViewById(R.id.ll_parent);
            update = (TextView) itemView.findViewById(R.id.itemtv_ads_edit);
            detele = (TextView) itemView.findViewById(R.id.itemtv_ads_delet);
            default_address_img = (ImageView) itemView.findViewById(R.id.default_address_img);

        }
    }

    //别的页面添加回显地址信息
    public interface OnItem{
        void onItem(int position, String user, String address);
    }

}