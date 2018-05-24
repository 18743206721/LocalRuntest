package com.xingguang.localrun.maincode.home.view.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.home.model.ProductDetailsBean;
import com.xingguang.localrun.popwindow.NowBuyPopUpWindow;
import com.xingguang.localrun.utils.MyListView;
import com.xingguang.localrun.view.TiceScrollview;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/23
 * 描述:商品详情
 * 作者:LiuYu
 */
public class ProductdetailsActivity extends ToolBarActivity implements TiceScrollview.onScrollChangedListener {

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
    MyListView rvComment;
    @BindView(R.id.ll_wpl)
    LinearLayout llWpl;
    @BindView(R.id.sc_head_view)
    TiceScrollview scHeadView;
    @BindView(R.id.title)
    RelativeLayout title;
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
    LinearLayout bottom;
    @BindView(R.id.ll_parent)
    RelativeLayout llParent;
    @BindView(R.id.ll_guige)
    LinearLayout ll_guige;

    private int mHeight;
    public static ProductdetailsActivity instance;

    private ArrayList<ProductDetailsBean> lists = new ArrayList<ProductDetailsBean>();

    //购买件数
    private int nums = 1;

    //规格ID   类型  提醒
    private String id, specificationId, type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_productdetails;
    }

    @Override
    protected void initView() {
        instance = this;
        getToolbarTitle().setText("商品详情");
        setSubImg(R.mipmap.pro_more);

        initListener();

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
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //更多
        getSubImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    @OnClick({R.id.ll_shop, R.id.collect, R.id.add_shopcar, R.id.commit,R.id.ll_guige})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shop://店铺
                break;
            case R.id.collect://收藏
                break;
            case R.id.add_shopcar: //添加购物车
                break;
            case R.id.commit://立即购买
                break;
            case R.id.ll_guige://选择规格
                new NowBuyPopUpWindow(ProductdetailsActivity.this, llParent, lists, nums);
                break;
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


}
