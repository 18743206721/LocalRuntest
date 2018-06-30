package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.xingguang.localrun.maincode.shop.model.PayResult;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.GetJsonDataUtil;
import com.xingguang.localrun.view.PayPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 立即购买页面
 */
public class BuyActivity extends ToolBarActivity {

    @BindView(R.id.llbuy)
    RelativeLayout llbuy;
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

    private String adsId; //支付时用到的地址id
    private double allprice; //总金额


    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    PayResult payResult = new PayResult((String) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    Log.i("Pay", "Pay:" + resultInfo);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(BuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };

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
                            mDatas.clear();
                            mDatas.addAll(bean.getData().getCartlist());
                            adapter.setList(mDatas);
                            tvPeisong.setText("¥"+bean.getData().getShipping_price());//配送金额
                            allprice = bean.getData().getTotal_amount();
                            tvHejiprice.setText("¥"+allprice);//总金额

                            //地址选择
                            adsId = bean.getData().getAddress().getAddress_id();
                            name.setText(bean.getData().getAddress().getConsignee());
                            phone.setText(bean.getData().getAddress().getMobile());
                            address.setText(bean.getData().getAddress().getAddress2());

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
                if (adsId.equals("")){
                    ToastUtils.showToast(BuyActivity.this,"请选择收货地址!");
                }else {
                    new PayPopWindow(BuyActivity.this, llbuy,allprice,adsId,edContent.getText().toString());
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 50) {
            if (resultCode == 50) {
                addressId = data.getStringExtra("id");
                nameStr = data.getStringExtra("name");
                phoneStr = data.getStringExtra("phone");
                xiangxiads = data.getStringExtra("xiangxiads");
                proviceid = data.getStringExtra("provice");
                cityid = data.getStringExtra("city");
                areaid = data.getStringExtra("area");
                load();
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
        for (int i = 0; i < jsonBean.size(); i++) { //遍历省份
            if (proviceid.equals(jsonBean.get(i).getId())){
                provicename = jsonBean.get(i).getName();
            }
            for (int c = 0; c < jsonBean.get(i).getChild().size(); c++) {  //遍历该省份的所有城市
                if (cityid.equals(jsonBean.get(i).getChild().get(c).getId())){
                    cityname = jsonBean.get(i).getChild().get(c).getName();
                }
                for (int d = 0; d < jsonBean.get(i).getChild().get(c).getChild().size(); d++) {
                    if (areaid.equals(jsonBean.get(i).getChild().get(c).getChild().get(d).getId())){
                        areaname = jsonBean.get(i).getChild().get(c).getChild().get(d).getName();
                    }
                }
            }
            address.setText(provicename+cityname+areaname+" "+xiangxiads);
        }



    }


}
