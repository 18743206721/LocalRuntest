package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.main.view.MainActivity;
import com.xingguang.localrun.maincode.home.model.ProductDetailsBean;
import com.xingguang.localrun.maincode.home.view.adapter.PinglunAdapter;
import com.xingguang.localrun.popwindow.CrowdPopUpWindow;
import com.xingguang.localrun.popwindow.NowBuyPopUpWindow;
import com.xingguang.localrun.popwindow.SharePopUpWindow;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.view.TiceScrollview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/23
 * 描述:商品详情
 * 作者:LiuYu
 */
public class ProductdetailsActivity extends BaseActivity implements TiceScrollview.onScrollChangedListener,
        SharePopUpWindow.OnShareListener {

    @BindView(R.id.iv_tice_head)
    ImageView ivTiceHead;
    @BindView(R.id.tv_pro_price)
    TextView tvProPrice;
    @BindView(R.id.ll_profenxinag)
    LinearLayout llProfenxinag;
    @BindView(R.id.tv_pro_name)
    TextView tvProName;
    @BindView(R.id.tv_pro_guige)
    TextView tvProGuige;
    @BindView(R.id.webView1)
    WebView webView1;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.ll_wpl)
    LinearLayout llWpl;
    @BindView(R.id.sc_head_view)
    NestedScrollView scHeadView;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.collect_img)
    ImageView collectImg;
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
    @BindView(R.id.ll_guige)
    LinearLayout ll_guige;
    @BindView(R.id.tv_pro)
    TextView tv_pro;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.iv_fenxiang)
    ImageView ivFenxiang;
    @BindView(R.id.ll_liebiao)
    LinearLayout llLiebiao;
    @BindView(R.id.line_two)
    TextView lineTwo;

    private boolean isshow;
    private int mHeight;
    public static ProductdetailsActivity instance;

    private ArrayList<ProductDetailsBean> lists = new ArrayList<ProductDetailsBean>();

    private List<String> mdatas = new ArrayList<>();
    //购买件数
    private int nums = 1;

    //规格ID   类型  提醒
    private String id, specificationId, type;
    private Intent intent;

    private CrowdPopUpWindow mPopUpWindow;

    private String goods_id; //商品id

    //分享
    private List<String> headlist = new ArrayList<>();
    private String shareUrl, shareTitle, shareImg, shareContent;
    private UMImage image;
    private UMWeb web;

    //微信支付
    private IWXAPI iwapi;
    PayReq request = new PayReq();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_productdetails;
    }

    @Override
    protected void initView() {
        goods_id = getIntent().getStringExtra("goods_id");
        ToastUtils.showToast(ProductdetailsActivity.this,"goods_id   "+goods_id);
        instance = this;
        iwapi = WXAPIFactory.createWXAPI(ProductdetailsActivity.this,null);
        iwapi.registerApp("xxx");

        initAdapter();
        initListener();
        loadshare();

    }

    private void loadshare() {
//        shareUrl = headlist.get(0);
//        shareTitle = nowApplyMainModel.getOutsourcing().getTitle();
//        shareImg = nowApplyMainModel.getOutsourcing().getUrl();
//        shareContent = nowApplyMainModel.getOutsourcing().getContent();
    }

    @Override
    public void onShareListener(SHARE_MEDIA share_media) {
        image = new UMImage(this, R.mipmap.icon_logo);//网络图片
        web = new UMWeb(shareUrl);
        web.setTitle(shareTitle);//标题
        web.setThumb(image);  //缩略图
        web.setDescription("点击查看更多详情");//描述
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
                        startActivity(new Intent(ProductdetailsActivity.this,MainActivity.class));
                    }
                    break;
                case R.id.ll_btn2://个人中心
                    if (mPopUpWindow != null) {

                        mPopUpWindow.dismiss();
                        LookShopActivity.instance.finish();
                        ProductdetailsActivity.this.finish();
                        MainActivity.instance.finish();

//                        Message msg1 = MainActivity.instance.handler
//                                .obtainMessage();
//                        msg1.what = 1;
//                        String a = "2";
//                        msg1.obj = a;
//                        MainActivity.instance.handler.sendMessage(msg1);
                        Intent intent = new Intent();
                        intent.setClass(ProductdetailsActivity.this,MainActivity.class);
//                        intent.putExtra("type","2");
                        startActivity(intent);


                    }
                    break;
                case R.id.ll_btn3://分享
                    if (mPopUpWindow != null) {
                        mPopUpWindow.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void initAdapter() {
        PinglunAdapter adapter = new PinglunAdapter(ProductdetailsActivity.this, mdatas);
        LinearLayoutManager manager = new LinearLayoutManager(ProductdetailsActivity.this);
        rvComment.setLayoutManager(manager);
        rvComment.setAdapter(adapter);
        rvComment.setNestedScrollingEnabled(false);
    }

    private void initListener() {
        // 获取顶部图片高度后，设置滚动监听
        llParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llParent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mHeight = ivTiceHead.getHeight() / 2;
                onScrollChanged(scHeadView.getScrollY());
            }
        });

    }

    @Override
    public void onScrollChanged(int y) {
//        if (y <= 0) {//未滑动
//            title.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
//            getToolbarBack().setImageResource(R.mipmap.pro_back);
//            setSubImg(R.mipmap.pro_more);
//        } else if (y > 0 && y <= mHeight) { //滑动过程中 并且在mHeight之内
//            float scale = (float) y / mHeight;
//            float alpha = (255 * scale);
//            getToolbarBack().setImageResource(R.mipmap.pro_back);
//            setSubImg(R.mipmap.pro_more);
//            title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//        } else {//超过mHeight
//            title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//        }

    }

    @OnClick({R.id.ll_shop, R.id.collect, R.id.add_shopcar, R.id.commit, R.id.ll_guige,R.id.back,
            R.id.iv_fenxiang,R.id.ll_profenxinag})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shop://店铺
                intent = new Intent();
                intent.setClass(ProductdetailsActivity.this, LookShopActivity.class);
                startActivity(intent);
                LookShopActivity.instance.finish();
                break;
            case R.id.collect://收藏
                collection();
                break;
            case R.id.add_shopcar: //添加购物车
                break;
            case R.id.ll_profenxinag: //友盟分享
                new SharePopUpWindow(ProductdetailsActivity.this, llParent, ProductdetailsActivity.this);
                break;
            case R.id.commit://立即购买

                request.appId="";
                iwapi.sendReq(request);

                break;
            case R.id.ll_guige://选择规格
                new NowBuyPopUpWindow(ProductdetailsActivity.this, llParent, lists, nums,1);
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

    private void collection() {
        isshow = !isshow;
        if (isshow) {
            setThemeColor(collectImg, R.mipmap.pro_collection);
            tv_pro.setText("已收藏");
            tv_pro.setTextColor(ContextCompat.getColor(ProductdetailsActivity.this, R.color.home_read));
            ToastUtils.showToast(ProductdetailsActivity.this, "已收藏!");
        } else {
            tv_pro.setText("收藏");
            tv_pro.setTextColor(ContextCompat.getColor(ProductdetailsActivity.this, R.color.textDarkGray));
            setThemeColor2(collectImg, R.mipmap.pro_collection);
            ToastUtils.showToast(ProductdetailsActivity.this, "已取消收藏!");
        }

    }

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    nums = Integer.parseInt(msg.obj.toString().split("\\ ")[0]);
                    specificationId = msg.obj.toString().split("\\ ")[1];
                    tvProGuige.setText(msg.obj.toString().split("\\ ")[2]);
//                    newPrice.setText(msg.obj.toString().split("\\ ")[3]);
//                    oldPrice.setText("¥" + msg.obj.toString().split("\\ ")[4]);
//                    oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
                    break;
                case 2:
                    //列表数据
                    falshsaleCommoditySize(id);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 商品规格
     */
    private void falshsaleCommoditySize(String id) {
//        lists.clear();
//        lists.addAll(model.getList());
    }

    private void setThemeColor(ImageView mImage, int icon) {
        //利用ContextCompat工具类获取drawable图片资源
        Drawable drawable = ContextCompat.getDrawable(this, icon);
        //简单的使用tint改变drawable颜色
        Drawable drawable1 = AppUtil.tintDrawable(drawable, ContextCompat.getColor(this, R.color.home_read));
        mImage.setImageDrawable(drawable1);
    }

    private void setThemeColor2(ImageView mImage, int icon) {
        //利用ContextCompat工具类获取drawable图片资源
        Drawable drawable = ContextCompat.getDrawable(this, icon);
        //简单的使用tint改变drawable颜色
        Drawable drawable1 = AppUtil.tintDrawable(drawable, ContextCompat.getColor(this, R.color.textGray));
        mImage.setImageDrawable(drawable1);
    }

}
