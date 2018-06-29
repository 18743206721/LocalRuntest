package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.CartBean;
import com.xingguang.localrun.maincode.home.view.adapter.BuyCartAdapter;
import com.xingguang.localrun.maincode.mine.model.JsonBean;
import com.xingguang.localrun.maincode.mine.view.activity.AddressManagementActivity;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.GetJsonDataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 立即购买页面
 */
public class BuyActivity extends ToolBarActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.ll_ads)
    LinearLayout llAds;
    @BindView(R.id.main_home_lv)
    RecyclerView recyc;
    @BindView(R.id.tv_peisong)
    TextView tvPeisong;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_allprice)
    TextView tvAllprice;
    @BindView(R.id.tv_hejiprice)
    TextView tvHejiprice;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    BuyCartAdapter adapter;
    List<CartBean.DataBean.CartlistBean> mDatas = new ArrayList<>();
    public static BuyActivity instance;
    //id,姓名，电话，详细地址
    private String addressId,nameStr,phoneStr,xiangxiads;
    //省市区id
    private String proviceid ,cityid,areaid;
    //省市区name
    private String provicename,cityname,areaname;

    double totalPrice;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initView() {
        instance = this;
        setToolBarTitle("确认订单");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        totalPrice = getIntent().getDoubleExtra("totalPrice",0.00);

        adapter = new BuyCartAdapter(BuyActivity.this, mDatas);
        LinearLayoutManager manager = new LinearLayoutManager(BuyActivity.this);
        recyc.setLayoutManager(manager);
        recyc.setAdapter(adapter);
        recyc.setNestedScrollingEnabled(false);
        recyc.setFocusable(false);

        load();
        initListener();

    }

    private void load() {
        OkGo.<String>post(HttpManager.Cart)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(BuyActivity.this))
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CartBean bean = gson.fromJson(response.body().toString(), CartBean.class);
                        if (bean.getData()!=null){
                            mDatas.addAll(bean.getData().getCartlist());
                            adapter.setList(mDatas);
                            tvPeisong.setText("¥"+bean.getData().getShipping_price());//配送金额
                            tvAllprice.setText("¥"+bean.getData().getTotal_amount());//总金额
                        }else {
                            ToastUtils.showToast(BuyActivity.this, bean.getMsg());
                        }

                    }
                });

    }

    private void initListener() {
//        Intent intent = new Intent(BuyActivity.this,ProductdetailsActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        startActivity(intent);
    }

    @OnClick({R.id.ll_ads, R.id.tv_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ads: //选择地址
                Intent intent = new Intent(BuyActivity.this, AddressManagementActivity.class);
                intent.putExtra("buytype",1);
                startActivityForResult(intent, 50);
                break;
            case R.id.tv_order://立即购买

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
//        if (requestCode == REQUEST_CODE_PAYMENT) {
//            if (resultCode == NowPayActivity.RESULT_OK) {
//                String result = data.getExtras().getString("pay_result");
//                if (result.equals("success")) {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "交易成功",
//                            Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(PurchaseDetailPayActivity.this, PurchasePaySuccessActivity.class);
//                    intent.putExtra("jifenNum", jifenNum);
//                    startActivity(intent);
//                    finish();
//                } else if (result.equals("fail")) {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "交易失败", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (result.equals("cancel")) {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "用户取消交易",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "支付插件没有安装",
//                            Toast.LENGTH_SHORT).show();
//                }
//                // 处理返回值
//                // "success" - 支付成功
//                // "fail"    - 支付失败
//                // "cancel"  - 取消支付
//                // "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
//                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
//                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
////                showMsg(result, errorMsg, extraMsg);
//            }
//        }
        if (requestCode == 50) {
            if (resultCode == 50) {
                addressId = data.getStringExtra("id");
//                address.setText("收货地址：" + data.getStringExtra("address"));
                nameStr = data.getStringExtra("name");
                phoneStr = data.getStringExtra("phone");
                xiangxiads = data.getStringExtra("xiangxiads");
                proviceid = data.getStringExtra("provice");
                cityid = data.getStringExtra("city");
                areaid = data.getStringExtra("area");
                name.setText(nameStr);
                phone.setText(phoneStr);
                initJsonData(proviceid,cityid,areaid,xiangxiads);
            }
        }
    }

    private void initJsonData(String proviceid, String cityid, String areaid,String xiangxiads) {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil().getJson(BuyActivity.this, "region.json");//获取assets目录下的json文件数据
        Log.e("jsonbeanqqqq", "initJsonData: " + JsonData);
        ArrayList<JsonBean> jsonBean = AppUtil.parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         */
//        options1Items = jsonBean;
//        Log.e("jsonbean", "initJsonData: " + jsonBean);
        for (int i = 0; i < jsonBean.size(); i++) { //遍历省份
            if (proviceid.equals(jsonBean.get(i).getId())){
                provicename = jsonBean.get(i).getName();
            }
//            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
//            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getChild().size(); c++) {  //遍历该省份的所有城市
                if (cityid.equals(jsonBean.get(i).getChild().get(c).getId())){
                    cityname = jsonBean.get(i).getChild().get(c).getName();
                }
//                String cityname = jsonBean.get(i).getChild().get(c).getName();
//                CityList.add(cityname);//添加城市
//                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空数据，防止数据为null 导致三个选项长度不匹配造成崩溃
//                if (jsonBean.get(i).getChild().get(c).getChild().size() == 0) {
//                    City_AreaList.add("");
//                }
                for (int d = 0; d < jsonBean.get(i).getChild().get(c).getChild().size(); d++) {
                    if (areaid.equals(jsonBean.get(i).getChild().get(c).getChild().get(d).getId())){
                        areaname = jsonBean.get(i).getChild().get(c).getChild().get(d).getName();
                    }
//                    City_AreaList.add(area);  //添加该城市所有的地区数据
                }
//                Province_AreaList.add(City_AreaList);
            }
//            /**
//             * 添加城市地区
//             * */
//            options2Items.add(CityList);

//            /**
//             *添加地区数据
//             * */
//            options3Items.add(Province_AreaList);

            address.setText(provicename+cityname+areaname+" "+xiangxiads);

        }
    }



}
