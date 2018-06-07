package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.SharedPreferencesUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.view.adapter.DaiBanMoreAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.HistoryListAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.SearchResultAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.ShopDianAdapter;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.view.ClearEditText;
import com.xingguang.localrun.view.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/19
 * 描述: 首页搜索页面
 * 作者:LiuYu
 */
public class HomeSearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tab_all)
    XTabLayout tabAll;
    @BindView(R.id.rv_tag)
    TagCloudLayout rv_tag;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_hostiry_dele)
    ImageView ivHostiryDele;
    @BindView(R.id.ll_history_search)
    LinearLayout llHistorySearch;
    @BindView(R.id.ll_search)
    LinearLayout ll_search;
    @BindView(R.id.ll_biaoqian)
    LinearLayout ll_biaoqian;
    @BindView(R.id.ll_rv)
    LinearLayout ll_rv;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.ll_sea_biaoqian)
    LinearLayout ll_sea_biaoqian;
    @BindView(R.id.xtab_classifshop)
    XTabLayout xtab_classifshop;

    Timer timer = new Timer();
    HistoryListAdapter listAdapter;
    //搜索历史的集合
    private List<String> historList = new ArrayList<>();
    String search = "";
    public static HomeSearchActivity instance;
    int currentpos = 0;

    private List<String> proList = new ArrayList<>();
    private List<String> shopList = new ArrayList<>();
    private List<String> daibanList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void initView() {
        instance = this;
        ll_biaoqian.setVisibility(View.VISIBLE);
        ll_sea_biaoqian.setVisibility(View.GONE); //设置商品标签隐藏
        listAdapter = new HistoryListAdapter(HomeSearchActivity.this, historList);
        rv_tag.setAdapter(listAdapter);
        huixian();
        initTab();
    }

    public void initListener(int position) {
        currentpos = position;
        search = historList.get(position);
        etSearch.setText(search);
//                loadfindbykey(search, 0);
        initxianshi();
    }

    private void huixian() {
        String[] historSearch;
        if (!TextUtils.isEmpty((String) SharedPreferencesUtils.get(this, SharedPreferencesUtils.TOGETHERPLAY, ""))) {
            historSearch = ((String) SharedPreferencesUtils.get(this, SharedPreferencesUtils.TOGETHERPLAY, "")).split(",");
        } else {
            historSearch = new String[]{};
        }
        historList.clear();
        for (int i = 0, j = historSearch.length; i < j; i++) {
            historList.add(historSearch[i]);
        }
        listAdapter.setList(historList);
    }

    private void initTab() {
        tabAll.addTab(tabAll.newTab().setText("商品"));
        tabAll.addTab(tabAll.newTab().setText("店铺"));
        tabAll.addTab(tabAll.newTab().setText("代办"));

        //设置商品下综合销量价格标签
        xtab_classifshop.addTab(xtab_classifshop.newTab().setText("综合"));
        xtab_classifshop.addTab(xtab_classifshop.newTab().setText("销量"));
        xtab_classifshop.addTab(xtab_classifshop.newTab().setText("价格"));

        tabAll.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();
                ToastUtils.showToast(HomeSearchActivity.this, "dianji" + text);
                if (text.equals("商品")){
                    ll_sea_biaoqian.setVisibility(View.VISIBLE);
                    SearchResultAdapter shopadapter = new SearchResultAdapter(HomeSearchActivity.this,proList,"1");
                    LinearLayoutManager lmg = new LinearLayoutManager(HomeSearchActivity.this);
                    rv_list.setLayoutManager(lmg);
                    rv_list.setAdapter(shopadapter);
                    shopadapter.setaList(proList);
                    shopadapter.setmOnItemaddcarClickLitener(new SearchResultAdapter.OnItemaddcarClickListener() {
                        @Override
                        public void OnItemaddcarClick(TextView view, int position) {
                            ToastUtils.showToast(HomeSearchActivity.this,"已加入到购物车!");
                        }
                    });
                    shopadapter.setmOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(View view, int position) {
                            Intent intent = new Intent();
                            intent.setClass(HomeSearchActivity.this, ProductdetailsActivity.class);
                            intent.putExtra("proid", "3");
                            startActivity(intent);
                            finish();
                        }
                    });

                }else if (text.equals("店铺")){
                    ll_sea_biaoqian.setVisibility(View.GONE);
                    ShopDianAdapter adapter = new ShopDianAdapter(HomeSearchActivity.this,shopList,1);
                    LinearLayoutManager mgr = new LinearLayoutManager(HomeSearchActivity.this);
                    rv_list.setLayoutManager(mgr);
                    rv_list.setAdapter(adapter);
                    adapter.setmOnItemClickListener(new ShopDianAdapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(TextView view, int position) {
                            Intent intent = new Intent();
                            intent.setClass(HomeSearchActivity.this, LookShopActivity.class);
//                            intent.putExtra("proid", "3");
                            startActivity(intent);
                            finish();
                        }
                    });
                }else {
                    ll_sea_biaoqian.setVisibility(View.GONE);
                    DaiBanMoreAdapter adapter = new DaiBanMoreAdapter(HomeSearchActivity.this,daibanList);
                    LinearLayoutManager lmg = new LinearLayoutManager(HomeSearchActivity.this);
                    rv_list.setLayoutManager(lmg);
                    rv_list.setAdapter(adapter);
                    adapter.setOnItemClickLitener(new DaiBanMoreAdapter.OnItemClickLitener() {
                        @Override
                        public void onItemClick(TextView view, int position) {
                            startActivity(new Intent(HomeSearchActivity.this,DaiBanDetailsActivity.class));
                        }
                    });

                }


            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
            }
        });

        //设置商品下综合销量价格标签的点击
        xtab_classifshop.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();


            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });

    }

    @OnClick({R.id.iv_finish, R.id.iv_hostiry_dele, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish://返回
                finish();
                break;
            case R.id.iv_hostiry_dele://删除历史记录
                historList.clear();
                saveHistory(historList);
                initxianshi();
                ll_biaoqian.setVisibility(View.GONE);
                break;
            case R.id.tv_search: //搜索
                historyload();
                break;
        }
    }

    private void historyload() {
        if (!etSearch.getText().toString().equals("")) { //输入不为空
            initxianshi();
            ll_biaoqian.setVisibility(View.VISIBLE);
            search = etSearch.getText().toString();
            if (historList.size() != 0) {
                if (etSearch.getText().toString().equals(historList.get(currentpos))) {
                    //直接显示列表，不添加到集合
                } else {
                    //添加到集合
                    historList.add(search);
                    listAdapter.setList(historList);
                    saveHistory(historList);
                }
            } else {
                historList.add(search);
                listAdapter.setList(historList);
                saveHistory(historList);
            }
        } else {//输入为空
            ToastUtils.showToast(HomeSearchActivity.this, "请输入搜索内容!");
        }

    }

    private void saveHistory(List<String> historList) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : historList) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        String a = result.toString();
        a = a.replaceAll("\\\\", "/");
        SharedPreferencesUtils.put(HomeSearchActivity.this, SharedPreferencesUtils.TOGETHERPLAY, a);
    }

    /**
     * 显示activity的搜索，隐藏键盘
     */
    private void initxianshi() {
        llHistorySearch.setVisibility(View.VISIBLE);//设置activity的搜索显示
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AppUtil.hideSoftInput(HomeSearchActivity.this);
            }
        }, 500);
    }


}
