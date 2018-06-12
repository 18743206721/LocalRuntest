package com.xingguang.localrun.maincode.mine.view.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
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
    ImageView defaultAddressImg;
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
                selectprovice();
                break;
            case R.id.default_address: //设置默认地址
                break;
            case R.id.bottom://添加或者修改
                break;
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
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2)
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
         *
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
