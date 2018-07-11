package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.SharedPreferencesUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.GoodsDetailsBean;
import com.xingguang.localrun.maincode.home.model.SearchOneBean;
import com.xingguang.localrun.maincode.home.model.SearchTwoBean;
import com.xingguang.localrun.maincode.home.model.Searchthreebean;
import com.xingguang.localrun.maincode.home.model.SpecBean;
import com.xingguang.localrun.maincode.home.view.adapter.HistoryListAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.SearchOneAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.SearchThreeAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.SearchTwoAdapter;
import com.xingguang.localrun.popwindow.NowBuyPopUpWindow;
import com.xingguang.localrun.popwindow.NowOrderPopUpWindow;
import com.xingguang.localrun.refresh.RefreshUtil;
import com.xingguang.localrun.refresh.SinaRefreshHeader;
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
public class HomeSearchActivity extends BaseActivity implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;
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
    @BindView(R.id.tw_refresh)
    TwinklingRefreshLayout twRefresh;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    @BindView(R.id.empty)
    ImageView empty;

    Timer timer = new Timer();
    HistoryListAdapter listAdapter;
    //搜索历史的集合
    private List<String> historList = new ArrayList<>();
    String search = "";
    public static HomeSearchActivity instance;
    int currentpos = 0;
    private boolean isRefresh = false;
    //商品规格列表
    private ArrayList<SpecBean.DataBean> specBeanList = new ArrayList<>();
    private List<SearchOneBean.DataBeanX.DataBean> oneList = new ArrayList<>(); //商品集合
    private List<SearchTwoBean.DataBeanX.DataBean> twoList = new ArrayList<>(); //店铺集合
    private List<Searchthreebean.DataBeanX.DataBean> threeList = new ArrayList<>(); //代办集合
    private int type = 1; //1：商品；2：店铺；3：代办
    private int page = 1; //页数
    private int sort = 1; //1：综合；2：销量；3：价格
    //购买件数
    private int nums = 1;
    //规格ID
    private String itemid = "";

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    nums = Integer.parseInt(msg.obj.toString().split("\\ ")[0]);
                    itemid = msg.obj.toString().split("\\ ")[1];
                    break;
            }
        }

    };
    private String goodsId;//店铺id

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void initView() {
        twRefresh.setHeaderView(new SinaRefreshHeader(HomeSearchActivity.this));
        twRefresh.setBottomView(new LoadingView(HomeSearchActivity.this));
        twRefresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());
        instance = this;
        ll_biaoqian.setVisibility(View.VISIBLE);
        ll_sea_biaoqian.setVisibility(View.GONE); //设置商品标签隐藏
        listAdapter = new HistoryListAdapter(HomeSearchActivity.this, historList);
        rv_tag.setAdapter(listAdapter);

        llHistorySearch.setVisibility(View.VISIBLE);

        huixian();
        initTab();
    }

    public void initListener(int position) {
        currentpos = position;
        search = historList.get(position);
        etSearch.setText(search);
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
                if (text.equals("商品")) {
                    type = 1;
                    ll_sea_biaoqian.setVisibility(View.VISIBLE);
                } else if (text.equals("店铺")) {
                    type = 2;
                    ll_sea_biaoqian.setVisibility(View.GONE);
                } else {
                    type = 3;
                    ll_sea_biaoqian.setVisibility(View.GONE);
                }
                if (etSearch.getText().length() != 0) {
                    load(type, page, sort);
                }
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {}

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {}
        });

        //设置商品下综合销量价格标签的点击
        xtab_classifshop.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();
                if (text.equals("综合")) {
                    sort = 1;
                } else if (text.equals("销量")) {
                    sort = 2;
                } else {
                    sort = 3;
                }
                load(type, 1, sort);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
            }
        });

    }

    /**
     * 店铺和代办的数据源
     */
    private void load(final int type, final int page, int sort) {
        if (search.length() == 0) {
            search = "";
        }
        OkGo.<String>post(HttpManager.search)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("type", type)
                .params("sort", sort)
                .params("keyword", search)
                .params("page", page)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        llHistorySearch.setVisibility(View.GONE);
                        Gson gson = new Gson();
                        if (type == 3) {//搜索代办
                            ll_sea_biaoqian.setVisibility(View.GONE);

                            Searchthreebean bean = gson.fromJson(response.body().toString(), Searchthreebean.class);
                            if (bean.getData().getData() != null) {

                                if (bean.getData().getData().size() == 0 && page != 1) {
                                    Toast.makeText(HomeSearchActivity.this,
                                            "只有这么多了~",
                                            Toast.LENGTH_SHORT).show();
                                }

                                if (page == 1) {
                                    threeList.clear();
                                }
                                threeList.addAll(bean.getData().getData());
                                if (threeList.size() == 0) {
                                    relativelayout.setVisibility(View.GONE);
                                    empty.setVisibility(View.VISIBLE);
                                } else {
                                    empty.setVisibility(View.GONE);
                                    relativelayout.setVisibility(View.VISIBLE);
                                }
                                SearchThreeAdapter adapter = new SearchThreeAdapter(HomeSearchActivity.this, threeList);
                                LinearLayoutManager lmg = new LinearLayoutManager(HomeSearchActivity.this);
                                rv_list.setLayoutManager(lmg);
                                rv_list.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                adapter.setOnItemClickLitener(new SearchThreeAdapter.OnItemClickLitener() {
                                    @Override
                                    public void onItemClick(TextView view, int position) {
                                        startActivity(new Intent(HomeSearchActivity.this, DaiBanDetailsActivity.class)
                                                .putExtra("danbanid", threeList.get(position).getId()));
                                    }
                                });
                            }
                        } else if (type == 2) { //搜索店铺
                            ll_sea_biaoqian.setVisibility(View.GONE);
                            SearchTwoBean twobean = gson.fromJson(response.body().toString(), SearchTwoBean.class);
                            if (twobean.getData().getData() != null) {
                                if (page == 1) {
                                    twoList.clear();
                                }
                                twoList.addAll(twobean.getData().getData());
                                if (twoList.size() == 0) {
                                    relativelayout.setVisibility(View.GONE);
                                    empty.setVisibility(View.VISIBLE);
                                } else {
                                    empty.setVisibility(View.GONE);
                                    relativelayout.setVisibility(View.VISIBLE);
                                }
                                SearchTwoAdapter adapter = new SearchTwoAdapter(HomeSearchActivity.this, twoList);
                                LinearLayoutManager mgr = new LinearLayoutManager(HomeSearchActivity.this);
                                rv_list.setLayoutManager(mgr);
                                rv_list.setAdapter(adapter);
                                adapter.setmOnItemClickListener(new SearchTwoAdapter.OnItemClickListener() {
                                    @Override
                                    public void OnItemClick(TextView view, int position) {
                                        Intent intent = new Intent();
                                        intent.setClass(HomeSearchActivity.this, LookShopActivity.class);
                                        intent.putExtra("shopid", twoList.get(position).getId());
                                        startActivity(intent);
                                    }
                                });
                            }
                        } else if (type == 1) { //搜索商品
                            ll_sea_biaoqian.setVisibility(View.VISIBLE);
                            SearchOneBean onebean = gson.fromJson(response.body().toString(), SearchOneBean.class);
                            if (onebean.getData().getData() != null) {
                                if (page == 1) {
                                    oneList.clear();
                                }
                                oneList.addAll(onebean.getData().getData());
                                if (oneList.size() == 0) {
                                    relativelayout.setVisibility(View.GONE);
                                    empty.setVisibility(View.VISIBLE);
                                } else {
                                    empty.setVisibility(View.GONE);
                                    relativelayout.setVisibility(View.VISIBLE);
                                }

                                SearchOneAdapter oneAdapter = new SearchOneAdapter(HomeSearchActivity.this, oneList);
                                LinearLayoutManager lmg = new LinearLayoutManager(HomeSearchActivity.this);
                                rv_list.setLayoutManager(lmg);
                                rv_list.setAdapter(oneAdapter);
                                oneAdapter.notifyDataSetChanged();
                                oneAdapter.setmOnItemaddcarClickLitener(new SearchOneAdapter.OnItemaddcarClickListener() {
                                    @Override
                                    public void OnItemaddcarClick(TextView view, int position) {
                                        //先弹出选择规格窗口，走规格接口，确定再走购物车接口
                                        loadcaradd(position);
                                    }
                                });
                                oneAdapter.setmOnItemClickListener(new SearchOneAdapter.OnItemClickListener() {
                                    @Override
                                    public void OnItemClick(View view, int position) {
                                        Intent intent = new Intent();
                                        intent.setClass(HomeSearchActivity.this, ProductdetailsActivity.class);
                                        intent.putExtra("goods_id", oneList.get(position).getGoods_id());
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                        if (isRefresh) {
                            twRefresh.finishRefreshing();
                        } else {
                            twRefresh.finishLoadmore();
                        }
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
            search = etSearch.getText().toString();
            if (historList.size() != 0) {

                if (!search.equals(historList.get(currentpos))) {
                    for (int i = 0; i < historList.size(); i++) {
                        if (!search.equals(historList.get(i))) {

                        } else {
                            historList.remove(i);
                        }
                    }

                    historList.add(search);
                    listAdapter.setList(historList);
                    saveHistory(historList);
                }

            } else {

                historList.add(search);
                listAdapter.setList(historList);
                saveHistory(historList);
            }
            load(type, 1, sort);
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

    @Override
    public void onRefresh() {
        isRefresh = true;
        load(type, page, sort);
    }

    @Override
    public void onLoad() {
        isRefresh = false;
        load(type, page++, sort);
    }


    /**
     * 商品规格
     */
    private void loadcaradd(final int position) {
        goodsId = oneList.get(position).getGoods_id();
        OkGo.<String>post(HttpManager.spec)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("goods_id", goodsId)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        SpecBean bean = gson.fromJson(response.body().toString(), SpecBean.class);
                        if (bean.getData() != null) {
                            if (bean.getData().size() != 0) {
                                specBeanList.clear();
                                specBeanList.addAll(bean.getData());
                                loadDetails(goodsId,bean.getData());
                            } else {
                                loadDetails(goodsId,bean.getData());
                            }
                        }
                    }
                });
    }

    //商品详情
    private void loadDetails(final String goodsId, final List<SpecBean.DataBean> list) {
        OkGo.<String>post(HttpManager.GoodsDetail)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("goods_id", goodsId)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        GoodsDetailsBean bean = gson.fromJson(response.body().toString(), GoodsDetailsBean.class);
                        if (bean.getData() != null) {
                            GoodsDetailsBean.DataBean dataBean = bean.getData();
                            String original_img = HttpManager.INDEX + dataBean.getOriginal_img();
                            String storgeNum = dataBean.getStore_count();//库存
                            if (list.size() == 0) { //无规格时加入购物车
                                new NowOrderPopUpWindow(HomeSearchActivity.this, ll_parent, original_img, goodsId, storgeNum, dataBean,3);
                            } else {//有规格时加入购物车
                                for (int i = 0, j = specBeanList.size(); i < j; i++) {
                                    if (itemid.equals(specBeanList.get(i).getItem_id())) {
                                        specBeanList.get(i).setIsClick("1");
                                    } else {
                                        specBeanList.get(i).setIsClick("0");
                                    }
                                }
                                new NowBuyPopUpWindow(HomeSearchActivity.this, ll_parent, specBeanList, original_img, nums,dataBean, 2);
                            }
                        }
                    }
                });
    }

}
