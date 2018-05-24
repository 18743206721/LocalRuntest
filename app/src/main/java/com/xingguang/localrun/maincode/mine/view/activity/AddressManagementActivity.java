package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.mine.model.AddressModel;
import com.xingguang.localrun.maincode.mine.view.adapter.AddressManagementAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/24
 * 描述:收货地址管理类
 * 作者:LiuYu
 */
public class AddressManagementActivity extends ToolBarActivity {

    @BindView(R.id.address_management_recycle)
    RecyclerView addressManagementRecycle;
    @BindView(R.id.bottom)
    TextView bottom;
    @BindView(R.id.main_fl)
    LinearLayout mainFl;
    @BindView(R.id.empty)
    ImageView empty;

    private List<String> addressId;
    private String userInf;
    private String userAddress;
    private String userAddressId;

    private ArrayList<AddressModel> myContractList;

    private AddressManagementAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_management;
    }

    @Override
    protected void initView() {
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setToolBarTitle("收货地址");
        initList();
        AddressList();

    }



    @OnClick(R.id.bottom)
    public void onViewClicked() {
        Intent intent = new Intent(AddressManagementActivity.this, AddressDetailActivity.class);
//                intent.putExtra("title", "update");
//                intent.putExtra("id", myContractList.get(position).getId());
        startActivityForResult(intent, 1);
    }

    /**
     * 初始化
     */
    public void initList() {
        myContractList = new ArrayList<AddressModel>();
        mAdapter = new AddressManagementAdapter(myContractList, this);
        addressManagementRecycle.setLayoutManager(new LinearLayoutManager(this));
        addressManagementRecycle.setAdapter(mAdapter);
        addressManagementRecycle.setNestedScrollingEnabled(false);
        //设置默认
        mAdapter.setDefaultClick(new AddressManagementAdapter.DefaultListener() {
            @Override
            public void SubClick(int position, String user, String address, String id) {
//                SetDefAddress(myContractList.get(position).getId(), position);
//                userInf = user;
//                userAddress = address;
//                userAddressId = id;
            }
        });
        //编辑
        mAdapter.setUpdateClick(new AddressManagementAdapter.UpdateListener() {
            @Override
            public void UpdateClick(int position) {
                Intent intent = new Intent(AddressManagementActivity.this, AddressDetailActivity.class);
//                intent.putExtra("title", "update");
//                intent.putExtra("id", myContractList.get(position).getId());
                startActivityForResult(intent, 1);
            }
        });
        //删除
        mAdapter.setDeteleClick(new AddressManagementAdapter.DeteleListener() {
            @Override
            public void DeteleClick(int position) {
//                DeteleAddress(myContractList.get(position).getId(), position);
            }
        });
    }

    private void AddressList() {

    }



}
