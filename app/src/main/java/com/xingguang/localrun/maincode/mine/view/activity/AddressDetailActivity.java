package com.xingguang.localrun.maincode.mine.view.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.popwindow.AddAddressPopUpWindow;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressDetailActivity extends ToolBarActivity {

    public static AddressDetailActivity instance;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.address)
    LinearLayout address;
    @BindView(R.id.address_xiangxi)
    EditText addressXiangxi;
    @BindView(R.id.default_address_img)
    ImageView defaultAddressImg;
    @BindView(R.id.default_address)
    LinearLayout defaultAddress;
    @BindView(R.id.bottom)
    TextView bottom;
    @BindView(R.id.tv_address_area)
    TextView addressTxt;
    @BindView(R.id.parent)
    LinearLayout parent;


    private String province = "";
    private String citis = "";
    private String district = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_detail;
    }

    @Override
    protected void initView() {
        instance = this;
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        title = getIntent().getStringExtra("title");
//        id = getIntent().getStringExtra("id");
//        if ("update".equals(title)) {
//            setToolBarTitle("编辑收货地址");
//            AddressDetail();
//        } else {
//            setToolBarTitle("添加收货地址");
//        }


    }

    @OnClick({R.id.address, R.id.default_address, R.id.bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address: //弹出地址选择
                new AddAddressPopUpWindow(this, parent, province, citis, district, true, "");
                break;
            case R.id.default_address: //设置默认地址
                break;
            case R.id.bottom://添加或者修改
                break;
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://地址
                    String[] address = msg.obj.toString().split("\\ ");
                    province = address[0];
                    citis = address[1];
                    district = address[2];
                    addressTxt.setText(province + " " + citis + " " + district);
                    break;
                default:
                    break;
            }
        }

    };


}
