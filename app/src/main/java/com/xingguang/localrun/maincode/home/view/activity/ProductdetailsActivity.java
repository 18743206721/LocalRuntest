package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.http.MyShare;
import com.xingguang.localrun.main.view.MainActivity;
import com.xingguang.localrun.maincode.home.model.GlideImageLoader;
import com.xingguang.localrun.maincode.home.model.GoodsDetailsBean;
import com.xingguang.localrun.maincode.home.model.PingJiaBean;
import com.xingguang.localrun.maincode.home.model.SpecBean;
import com.xingguang.localrun.maincode.home.view.adapter.PinglunAdapter;
import com.xingguang.localrun.popwindow.CrowdPopUpWindow;
import com.xingguang.localrun.popwindow.NowBuyPopUpWindow;
import com.xingguang.localrun.popwindow.NowOrderPopUpWindow;
import com.xingguang.localrun.popwindow.SharePopUpWindow;
import com.xingguang.localrun.refresh.RefreshUtil;
import com.xingguang.localrun.utils.AppUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/23
 * 描述:商品详情
 * 作者:LiuYu
 */
public class ProductdetailsActivity extends BaseActivity implements SharePopUpWindow.OnShareListener,
        RefreshUtil.OnRefreshListener {

    @BindView(R.id.iv_tice_head)
    Banner ivTiceHead;
    @BindView(R.id.rl_my_header)
    RelativeLayout rlMyHeader;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.iv_fenxiang)
    ImageView ivFenxiang;
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @BindView(R.id.mcollasptoobarlayout)
    CollapsingToolbarLayout mcollasptoobarlayout;
    @BindView(R.id.mAppbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.tv_pro_price)
    TextView tvProPrice;
    @BindView(R.id.ll_profenxinag)
    LinearLayout llProfenxinag;
    @BindView(R.id.tv_pro_name)
    TextView tvProName;
    @BindView(R.id.tv_pro_guige)
    TextView tvProGuige;
    @BindView(R.id.ll_guige)
    LinearLayout llGuige;
    @BindView(R.id.webView1)
    WebView webView1;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.ll_liebiao)
    LinearLayout llLiebiao;
    @BindView(R.id.ll_wpl)
    LinearLayout llWpl;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.line_two)
    TextView lineTwo;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.collect_img)
    ImageView collectImg;
    @BindView(R.id.tv_pro)
    TextView tvPro;
    @BindView(R.id.collect)
    LinearLayout collect;
    @BindView(R.id.add_shopcar)
    TextView addShopcar;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.bottom)
    RelativeLayout bottom;
    @BindView(R.id.ll_parent)
    RelativeLayout llParent;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tw_refresh)
    TwinklingRefreshLayout twRefresh;
    @BindView(R.id.nestscroll)
    NestedScrollView nestedScrollView;

    private boolean isRefresh = false;
    private boolean isshow;
    public static ProductdetailsActivity instance;
    //商品规格列表
    private ArrayList<SpecBean.DataBean> specBeanList = new ArrayList<>();
    //商品详情
    private List<PingJiaBean.DataBean> mdatas = new ArrayList<>();
    //购买件数
    private int nums = 1;
    //规格ID
    private String itemid = "";
    private Intent intent;
    private CrowdPopUpWindow mPopUpWindow;
    private String goods_id; //商品id
    //分享
    private String shareUrl, shareTitle;
    private UMImage image;
    private UMWeb web;

    private List<String> networkImages = new ArrayList<>(); //轮播图集合
    String original_img = ""; //规格里的图片
    private String storgeNum = "";//商品详情里的规格
    GoodsDetailsBean.DataBean dataBean;
    private int page = 1;
    private PinglunAdapter pinglunadapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_productdetails;
    }

    @Override
    protected void initView() {
        twRefresh.setEnableRefresh(false);
        twRefresh.setEnableOverScroll(false);
        twRefresh.setBottomView(new LoadingView(this));
        twRefresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());
        goods_id = getIntent().getStringExtra("goods_id");
        instance = this;

        twRefresh.setTargetView(nestedScrollView);

        initAdapter();
        loadDetails();
        loadshare();
        loadGuiGe();
        loadpingLun(1);
        initListener();

    }

    /**
     * 商品详情接口
     */
    private void loadDetails() {
        OkGo.<String>post(HttpManager.GoodsDetail)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("goods_id", goods_id)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        GoodsDetailsBean bean = gson.fromJson(response.body().toString(), GoodsDetailsBean.class);
                        if (bean.getData() != null) {
                            dataBean = bean.getData();
                            //加载头部轮播图
                            for (int i = 0; i < dataBean.getGoods_images().size(); i++) {
                                networkImages.add(HttpManager.INDEX + dataBean.getGoods_images().get(i).getImage_url());
                            }
                            initpage();
                            //webview，商品详情
                            webView1.setVisibility(View.VISIBLE);
                            String html = dataBean.getGoods_content();
                            String data = html.replace("%@", html);
                            webView1.loadData(data, "text/html; charset=UTF-8", null);
                            original_img = HttpManager.INDEX + dataBean.getOriginal_img();
                            tvProName.setText(dataBean.getGoods_name());//商品名称
                            tvProPrice.setText("¥" + dataBean.getShop_price());//优惠价
                            if (bean.getData().getIs_collected() == 1) { //收藏过该商品
                                AppUtil.setThemeColor(ProductdetailsActivity.this, collectImg, R.mipmap.pro_collection);
                                tvPro.setText("已收藏");
                                tvPro.setTextColor(ContextCompat.getColor(ProductdetailsActivity.this, R.color.home_read));
                            } else { //没收藏
                                tvPro.setText("收藏");
                                tvPro.setTextColor(ContextCompat.getColor(ProductdetailsActivity.this, R.color.textDarkGray));
                                AppUtil.setThemeColor2(ProductdetailsActivity.this, collectImg, R.mipmap.pro_collection);
                            }
                            //立即购买，如果没规格，获取商品库存
                            storgeNum = bean.getData().getStore_count();
                        } else {
                            ToastUtils.showToast(ProductdetailsActivity.this, bean.getMsg());
                        }

                    }
                });
    }

    private void loadshare() {
        shareUrl = MyShare.getDownload();
        shareTitle = MyShare.getTitle();
    }

    @Override
    public void onShareListener(SHARE_MEDIA share_media) {
        image = new UMImage(this, R.mipmap.icon_logo);//网络图片
        web = new UMWeb(shareUrl);
        web.setTitle(shareTitle);//标题
        web.setThumb(image);  //缩略图
        new ShareAction(ProductdetailsActivity.this)
                .setPlatform(share_media)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(ProductdetailsActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ProductdetailsActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ProductdetailsActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
        }
    };

    private void initAdapter() {
        pinglunadapter = new PinglunAdapter(ProductdetailsActivity.this, mdatas);
        LinearLayoutManager manager = new LinearLayoutManager(ProductdetailsActivity.this);
        rvComment.setLayoutManager(manager);
        rvComment.setAdapter(pinglunadapter);
        rvComment.setNestedScrollingEnabled(false);
    }

    private void initListener() {
//        rvComment.addOnScrollListener(new SwipyAppBarScrollListener(mAppbarlayout, llParent, rvComment));

//        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                twRefresh.setEnabled(true);
//            }
//        });

        mAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("dff", "onOffsetChanged: " + verticalOffset);
                if (verticalOffset >= 0) {
                    twRefresh.setEnableRefresh(true);
                    twRefresh.setEnableOverScroll(false);
                } else {
                    twRefresh.setEnableRefresh(false);
                    twRefresh.setEnableOverScroll(false);
                }

            }
        });


        //設置appbar的滑動颜色渐变效果
        mAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mtoolbar.setBackgroundColor(AppUtil.changeAlpha(getResources().getColor(R.color.white),
                        Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
                //如果相等，设置图片
                if (appBarLayout.getTotalScrollRange() == -verticalOffset) {
                    tv_title.setVisibility(View.VISIBLE);
                    back.setImageResource(R.mipmap.back_black);
                    ivFenxiang.setImageResource(R.mipmap.pro_more);
                } else {
                    tv_title.setVisibility(View.GONE);
                    back.setImageResource(R.mipmap.back_details);
                    ivFenxiang.setImageResource(R.mipmap.message_details);
                }
            }
        });

    }

    @OnClick({R.id.ll_shop, R.id.collect, R.id.add_shopcar, R.id.commit, R.id.ll_guige, R.id.back,
            R.id.iv_fenxiang, R.id.ll_profenxinag})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shop://店铺
//                ivTiceHead.stopAutoPlay();
//                Intent intent = new Intent(ProductdetailsActivity.this,LookShopActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                startActivity(intent);


                intent = new Intent();
                intent.setClass(ProductdetailsActivity.this, LookShopActivity.class);
                intent.putExtra("shopid", dataBean.getShop_id());
                startActivity(intent);
                finish();
                LookShopActivity.instance.finish();

                break;
            case R.id.collect://收藏
                collection();
                break;
            case R.id.add_shopcar: //添加购物车
                if (specBeanList.size() == 0) {
                    //添加购物车接口，无规格时，不传itemid
                    new NowOrderPopUpWindow(ProductdetailsActivity.this, llParent, original_img, goods_id, storgeNum, 2);
                } else { //有规格时
                    for (int i = 0, j = specBeanList.size(); i < j; i++) {
                        if (itemid.equals(specBeanList.get(i).getItem_id())) {
                            specBeanList.get(i).setIsClick("1");
                        } else {
                            specBeanList.get(i).setIsClick("0");
                        }
                    }
                    new NowBuyPopUpWindow(ProductdetailsActivity.this, llParent, specBeanList, original_img, nums, 4);
                }
                break;
            case R.id.ll_profenxinag: //友盟分享
                new SharePopUpWindow(ProductdetailsActivity.this, llParent, ProductdetailsActivity.this);
                break;
            case R.id.commit://立即购买
                if (specBeanList.size() != 0) {  //有规格情况
                    for (int i = 0, j = specBeanList.size(); i < j; i++) {
                        if (itemid.equals(specBeanList.get(i).getItem_id())) {
                            specBeanList.get(i).setIsClick("1");
                        } else {
                            specBeanList.get(i).setIsClick("0");
                        }
                    }
                    new NowBuyPopUpWindow(ProductdetailsActivity.this, llParent, specBeanList, original_img, nums, 3);
                } else {//无规格情况
                    //走商品详情接口
                    new NowOrderPopUpWindow(ProductdetailsActivity.this, llParent, original_img, goods_id, storgeNum, 1);

                }

                break;
            case R.id.ll_guige://选择规格
                if (specBeanList.size() != 0) {
                    for (int i = 0, j = specBeanList.size(); i < j; i++) {
                        if (itemid.equals(specBeanList.get(i).getItem_id())) {
                            specBeanList.get(i).setIsClick("1");
                        } else {
                            specBeanList.get(i).setIsClick("0");
                        }
                    }
                    new NowBuyPopUpWindow(ProductdetailsActivity.this, llParent, specBeanList, original_img, nums, 1);
                } else {
                    ToastUtils.showToast(ProductdetailsActivity.this, "此商品暂无规格!");
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.iv_fenxiang:
                if (mPopUpWindow == null) {
                    //自定义的单击事件
                    OnClickLintener paramOnClickListener = new OnClickLintener();
                    mPopUpWindow = new CrowdPopUpWindow(ProductdetailsActivity.this, paramOnClickListener);
                    //监听窗口的焦点事件，点击窗口外面则取消显示
                    mPopUpWindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                mPopUpWindow.dismiss();
                            }
                        }
                    });
                }
                WindowManager wm = ProductdetailsActivity.this.getWindowManager();
                int width = wm.getDefaultDisplay().getWidth() / 6;
                int btnWidth = ivFenxiang.getWidth() / 2;
                //设置默认获取焦点
                mPopUpWindow.setFocusable(true);
                //以某个控件的x和y的偏移量位置开始显示窗口
                mPopUpWindow.showAsDropDown(ivFenxiang, btnWidth - width, 20);
                //如果窗口存在，则更新
                mPopUpWindow.update();

                break;
        }
    }


    /**
     * 商品规格
     */
    private void loadGuiGe() {
        OkGo.<String>post(HttpManager.spec)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("goods_id", goods_id)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        SpecBean bean = gson.fromJson(response.body().toString(), SpecBean.class);
                        if (bean.getData() != null) {
                            if (bean.getData().size() != 0) {
                                specBeanList.addAll(bean.getData());
                            }
                        } else {
                            ToastUtils.showToast(ProductdetailsActivity.this, bean.getMsg());
                        }
                    }
                });
    }


    private String keyname = "";//规格名称

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:  //产品
                    nums = Integer.parseInt(msg.obj.toString().split("\\ ")[0]);
                    itemid = msg.obj.toString().split("\\ ")[1];
                    keyname = msg.obj.toString().split("\\ ")[2];
                    tvProGuige.setText(keyname + " x " + nums);
                    break;
                case 2: //立即购买
                    nums = Integer.parseInt(msg.obj.toString().split("\\ ")[0]);
                    itemid = msg.obj.toString().split("\\ ")[1];
                    keyname = msg.obj.toString().split("\\ ")[2];

//                    BuyActivity.instance.finish();
                    startActivity(new Intent(ProductdetailsActivity.this, BuyActivity.class));
                    ProductdetailsActivity.instance.finish();
                    break;
            }
        }

    };


    //头部轮播图
    private void initpage() {
        //设置图片加载器
        ivTiceHead.setImageLoader(new GlideImageLoader());
        //设置图片集合
        ivTiceHead.setImages(networkImages);
        ivTiceHead.setDelayTime(4000);
        //banner设置方法全部调用完毕时最后调用
        ivTiceHead.start();
    }

    /**
     * 收藏接口
     */
    private void collection() {
        isshow = !isshow;
        if (isshow) {
            OkGo.<String>post(HttpManager.collect)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(this))
                    .params("goods_id", goods_id)
                    .execute(new DialogCallback<String>(this) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                            AppUtil.setThemeColor(ProductdetailsActivity.this, collectImg, R.mipmap.pro_collection);
                            tvPro.setText("已收藏");
                            tvPro.setTextColor(ContextCompat.getColor(ProductdetailsActivity.this, R.color.home_read));
                            ToastUtils.showToast(ProductdetailsActivity.this, bean.getMsg());
                        }
                    });
        } else {
            OkGo.<String>post(HttpManager.delCollectGoods)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(this))
                    .params("goods_id", goods_id)
                    .execute(new DialogCallback<String>(this) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                            tvPro.setText("收藏");
                            tvPro.setTextColor(ContextCompat.getColor(ProductdetailsActivity.this, R.color.textDarkGray));
                            AppUtil.setThemeColor2(ProductdetailsActivity.this, collectImg, R.mipmap.pro_collection);
                            ToastUtils.showToast(ProductdetailsActivity.this, bean.getMsg());
                        }
                    });
        }
    }

    class OnClickLintener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_btn1: //首页
                    if (mPopUpWindow != null) {
                        mPopUpWindow.dismiss();
                        LookShopActivity.instance.finish();
                        ProductdetailsActivity.this.finish();
                        MainActivity.instance.finish();
                        startActivity(new Intent(ProductdetailsActivity.this, MainActivity.class)
                                .putExtra("typeid", 1));
                    }
                    break;
                case R.id.ll_btn2://个人中心
                    if (mPopUpWindow != null) {
                        mPopUpWindow.dismiss();
                        LookShopActivity.instance.finish();
                        ProductdetailsActivity.this.finish();
                        MainActivity.instance.finish();
                        startActivity(new Intent(ProductdetailsActivity.this, MainActivity.class)
                                .putExtra("typeid", 2));
                    }
                    break;
                case R.id.ll_btn3://分享
                    if (mPopUpWindow != null) {
                        mPopUpWindow.dismiss();
                        new SharePopUpWindow(ProductdetailsActivity.this, llParent, ProductdetailsActivity.this);
                    }
                    break;
            }
        }
    }


    /**
     * 评论数据接口
     */
    private void loadpingLun(final int page) {
        OkGo.<String>post(HttpManager.Goodscomment)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("goods_id", goods_id)
                .params("page", page)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        PingJiaBean bean = gson.fromJson(response.body().toString(), PingJiaBean.class);
                        if (bean.getData() != null) {

                            if (bean.getData().size() == 0 && page != 1) {
                                Toast.makeText(ProductdetailsActivity.this,
                                        "只有这么多了~",
                                        Toast.LENGTH_SHORT).show();
                            }

                            if (page == 1) {
                                mdatas.clear();
                            }

                            mdatas.addAll(bean.getData());

                            pinglunadapter.setList(mdatas);


                            if (mdatas.size() == 0) {
                                llLiebiao.setVisibility(View.GONE);
                                llWpl.setVisibility(View.VISIBLE);
                            } else {
                                llLiebiao.setVisibility(View.VISIBLE);
                                llWpl.setVisibility(View.GONE);
                            }

                            twRefresh.finishLoadmore();

                        } else {
                            ToastUtils.showToast(ProductdetailsActivity.this, bean.getMsg());
                        }

                    }
                });
    }

    @Override
    public void onRefresh() {
        twRefresh.finishRefreshing();
    }

    @Override
    public void onLoad() {
        loadpingLun(page++);
    }


}
