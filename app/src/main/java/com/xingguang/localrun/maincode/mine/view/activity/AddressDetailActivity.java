package com.xingguang.localrun.maincode.mine.view.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.mine.model.AddressDetails;
import com.xingguang.localrun.maincode.mine.model.JsonBean;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.GetJsonDataUtil;

import java.util.ArrayList;

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
    CheckBox check_box;
    @BindView(R.id.default_address)
    LinearLayout defaultAddress;
    @BindView(R.id.bottom)
    TextView bottom;
    @BindView(R.id.tv_address_area)
    TextView addressTxt;
    @BindView(R.id.parent)
    LinearLayout parent;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private String provinceId = "";
    private String cityId = "";
    private String areaId = "";
    private static final int MSG_LOAD_DATA = 0x0001;
    private Thread thread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
            }
        }
    };

    private int isdefault = 1;//默认勾选
    private boolean isdianji = true;
    private String id; //编辑id

    private String province;//名字
    private String city;
    private String area;

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
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        id = getIntent().getStringExtra("id");
        if (id.equals("-1")) {
            setToolBarTitle("添加收货地址");
            bottom.setText("添 加");
        } else {
            setToolBarTitle("编辑收货地址");
            bottom.setText("编 辑");
            editaddress();
        }
        check_box.setChecked(true);
    }


    private void editaddress() {
        OkGo.<String>post(HttpManager.UserADSdetail)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("address_id", id)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        AddressDetails commonBean = gson.fromJson(response.body().toString(), AddressDetails.class);
                        name.setText(commonBean.getData().getConsignee());
                        phone.setText(commonBean.getData().getMobile());
                        provinceId = commonBean.getData().getProvince();
                        areaId = commonBean.getData().getArea();
                        cityId= commonBean.getData().getCity();

                        String JsonData = new GetJsonDataUtil().getJson(AddressDetailActivity.this, "region.json");//获取assets目录下的json文件数据
                        ArrayList<JsonBean> jsonBean = AppUtil.parseData(JsonData);//用Gson 转成实体
                        for (int i = 0; i < jsonBean.size(); i++) { //遍历省份
                            if (commonBean.getData().getProvince()
                                    .equals(options1Items.get(i).getId())) {
                                province = options1Items.get(i).getName();
                            }
                            for (int c = 0; c < jsonBean.get(i).getChild().size(); c++) {  //遍历该省份的所有城市
                                if (commonBean.getData().getCity()
                                        .equals(options1Items.get(i).getChild().get(c).getId())) {
                                    city = options1Items.get(i).getChild().get(c).getName();
                                }
                                for (int d = 0; d < jsonBean.get(i).getChild().get(c).getChild().size(); d++) {
                                    if (commonBean.getData().getArea()
                                            .equals(options1Items.get(i).getChild().get(c).getChild().get(d).getId())) {
                                        area = options1Items.get(i).getChild().get(c).getChild().get(d).getName();
                                    }
                                }
                            }
                        }
                        addressTxt.setText(province + " " + city + " " + area + " ");
                        addressXiangxi.setText(commonBean.getData().getAddress());
                        if ("0".equals(commonBean.getData().getIs_default())) {
                            check_box.setChecked(false);
                            isdefault = 0;
                        } else {
                            check_box.setChecked(true);
                            isdefault = 1;
                        }
                    }
                });
    }

    @OnClick({R.id.address, R.id.default_address, R.id.bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address: //弹出地址选择
                selectprovice();
                break;
            case R.id.default_address: //设置默认地址
                isdianji = !isdianji;
                if (isdianji) {
                    check_box.setChecked(false);
                    isdefault = 0;
                } else {
                    check_box.setChecked(true);
                    isdefault = 1;
                }
                break;
            case R.id.bottom://添加或者修改

                if (id.equals("-1")) { //添加
                    if (validate()) {
                        loadadd();
                    }
                } else { //编辑
                    if (validate()) {
                        loadedit();
                    }
                }
                break;
        }
    }


    /**
     * 编辑的接口
     */
    private void loadedit() {
        OkGo.<String>post(HttpManager.editAddress)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("consignee", name.getText().toString()) //收货人
                .params("mobile", phone.getText().toString()) //电话
                .params("province", provinceId) //省
                .params("city", cityId) //市
                .params("area", areaId) //县/区
                .params("address", addressXiangxi.getText().toString())
                .params("is_default", isdefault)//默认收货地址（1：是，0：不是）
                .params("address_id",id)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(), CommonBean.class);
                        ToastUtils.showToast(AddressDetailActivity.this, commonBean.getMsg());
                        setResult(1, AddressDetailActivity.this.getIntent());
                        finish();
                    }
                });
    }

    /**
     * 添加地址接口
     */
    private void loadadd() {
        OkGo.<String>post(HttpManager.Addadres)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("consignee", name.getText().toString()) //收货人
                .params("mobile", phone.getText().toString()) //电话
                .params("province", provinceId) //省
                .params("city", cityId) //市
                .params("area", areaId) //县/区
                .params("address", addressXiangxi.getText().toString())
                .params("is_default", isdefault)//默认收货地址（1：是，0：不是）
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(), CommonBean.class);
                        ToastUtils.showToast(AddressDetailActivity.this, commonBean.getMsg());
                        setResult(1, AddressDetailActivity.this.getIntent());
                        finish();
                    }
                });
    }

    public Boolean validate() {
        if (name.getText().length() == 0) {
            ToastUtils.showToast(this, "请填写姓名！");
            return false;
        } else if (phone.getText().length() == 0) {
            ToastUtils.showToast(this, "请填写手机号码！");
            return false;
        } else if (phone.getText().length() != 11) {
            ToastUtils.showToast(this, "请填写11位手机号");
            return false;
        } else if (addressTxt.getText().length() == 0) {
            ToastUtils.showToast(this, "请选择收货地址");
            return false;
        } else if (addressXiangxi.getText().length() == 0) {
            ToastUtils.showToast(this, "请填写详细地址");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 选择省份
     */
    private void selectprovice() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(AddressDetailActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +" "+
                        options2Items.get(options1).get(options2)+" "
                        + options3Items.get(options1).get(options2).get(options3);

                provinceId = options1Items.get(options1).getId();
                cityId = options1Items.get(options1).getChild().get(options2).getId();
                areaId = options1Items.get(options1).getChild().get(options2).getChild().get(options3).getId();
                addressTxt.setText(tx);
            }
        })
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setSubCalSize(16)//取消确定按钮文字大小
                .setContentTextSize(16)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText("选择城市")//标题文字
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();

    }

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil().getJson(AddressDetailActivity.this, "region.json");//获取assets目录下的json文件数据
        Log.e("jsonbeanqqqq", "initJsonData: " + JsonData);
        ArrayList<JsonBean> jsonBean = AppUtil.parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         */
        options1Items = jsonBean;
        Log.e("jsonbean", "initJsonData: " + jsonBean);
        for (int i = 0; i < jsonBean.size(); i++) { //遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getChild().size(); c++) {  //遍历该省份的所有城市
                String cityname = jsonBean.get(i).getChild().get(c).getName();
                CityList.add(cityname);//添加城市
                Log.e("jsonbeanddd", "initJsonData: " + cityname);
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空数据，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getChild().get(c).getChild().size() == 0) {
                    City_AreaList.add("");
                }
                for (int d = 0; d < jsonBean.get(i).getChild().get(c).getChild().size(); d++) {
                    String area = jsonBean.get(i).getChild().get(c).getChild().get(d).getName();
                    City_AreaList.add(area);  //添加该城市所有的地区数据
                }
                Province_AreaList.add(City_AreaList);
            }
            /**
             * 添加城市地区
             * */
            options2Items.add(CityList);

            /**
             *添加地区数据
             * */
            options3Items.add(Province_AreaList);
        }
    }


}
