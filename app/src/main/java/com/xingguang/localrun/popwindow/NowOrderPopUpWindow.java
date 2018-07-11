/**
 * @param
 */
package com.xingguang.localrun.popwindow;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.GoodsDetailsBean;
import com.xingguang.localrun.maincode.home.view.activity.BuyActivity;
import com.xingguang.localrun.maincode.home.view.activity.HomeSearchActivity;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;

/**
 * @param
 */
public class NowOrderPopUpWindow extends PopupWindow implements View.OnClickListener {

    private final String goodsid;//店铺id
    //图片  减号   加号
    private ImageView title_img, subtract_btn, plus_btn;
    //价钱  库存   数量  确定 ,“规格”字体，“请选择规格”字体
    private TextView money, inventory, num_tv, commit, tv_selectedguige,textview1;
    //取消
    private ImageView cancel;

    private Context context;
    private int nums = 1;
    private String original_img; //图片
    int type; //1是立即购买
    String storgeNum = "";//库存
    GoodsDetailsBean.DataBean dataBean;

    public NowOrderPopUpWindow(Context contexts, View parent, String original_img, String goodsid, String storgeNum, GoodsDetailsBean.DataBean dataBean, int type) {
        this.context = contexts;
        this.original_img = original_img;
        this.goodsid = goodsid;
        this.storgeNum = storgeNum;
        this.type = type;
        this.dataBean = dataBean;

        View view = View.inflate(context, R.layout.popup_now_buy, null);

        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.pop_enter_anim));

        //图片  减号   加号
        title_img = (ImageView) view.findViewById(R.id.title_img);
        subtract_btn = (ImageView) view.findViewById(R.id.subtract_btn);
        plus_btn = (ImageView) view.findViewById(R.id.plus_btn);
        subtract_btn.setOnClickListener(this);
        plus_btn.setOnClickListener(this);
        tv_selectedguige = view.findViewById(R.id.tv_selectedguige);

        //价钱  库存  取消  数量  确定
        money = (TextView) view.findViewById(R.id.money);
        inventory = (TextView) view.findViewById(R.id.inventory);
        cancel = (ImageView) view.findViewById(R.id.cancel);
        num_tv = (TextView) view.findViewById(R.id.num_tv);
        commit = (TextView) view.findViewById(R.id.commit);
        textview1 = view.findViewById(R.id.textview1);


        ImageLoader.getInstance().initGlide(contexts).loadImage(original_img, title_img);
        money.setText(dataBean.getShop_price());
        inventory.setText("库存：" + dataBean.getStore_count());
        tv_selectedguige.setText("此商品暂无规格");
        textview1.setVisibility(View.GONE);

        changeData();

        //确认购买
        commit.setOnClickListener(this);

        //取消
        cancel.setOnClickListener(this);

        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.MATCH_PARENT);
        // setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.CENTER, 0, 0);
        update();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit: //确认
                if (type == 1) {  //无规格的时候，立即购买
                    final ProductdetailsActivity activity = (ProductdetailsActivity) context;
                    OkGo.<String>post(HttpManager.addCart)
                            .tag(this)
                            .cacheKey("cachePostKey")
                            .cacheMode(CacheMode.DEFAULT)
                            .params("token", AppUtil.getUserId(context))
                            .params("goods_id", goodsid)
                            .params("goods_num", nums)
                            .execute(new DialogCallback<String>(activity) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                                    if (bean.getStatus() == 1) {
                                        dismiss();
                                        context.startActivity(new Intent(context, BuyActivity.class));
                                        ((ProductdetailsActivity) context).finish();
//                                        BuyActivity.instance.finish();
                                    } else {
                                        ToastUtils.showToast(activity, bean.getMsg());
                                    }
                                }
                            });
                } else if (type == 2) { //商品详情 加入购物车
                    ProductdetailsActivity activity = (ProductdetailsActivity) context;
                    OkGo.<String>post(HttpManager.addCart)
                            .tag(this)
                            .cacheKey("cachePostKey")
                            .cacheMode(CacheMode.DEFAULT)
                            .params("token", AppUtil.getUserId(context))
                            .params("goods_id", goodsid)
                            .params("goods_num", nums)
                            .execute(new DialogCallback<String>(activity) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                                    dismiss();
                                    ToastUtils.showToast(context, bean.getMsg());
                                }
                            });

                } else if (type == 3) {  //无规格 搜索页面 加入购物车
                    HomeSearchActivity activity = (HomeSearchActivity) context;
                    OkGo.<String>post(HttpManager.addCart)
                            .tag(this)
                            .cacheKey("cachePostKey")
                            .cacheMode(CacheMode.DEFAULT)
                            .params("token", AppUtil.getUserId(context))
                            .params("goods_id", goodsid)
                            .params("goods_num", nums)
                            .execute(new DialogCallback<String>(activity) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                                    dismiss();
                                    ToastUtils.showToast(context, bean.getMsg());
                                }
                            });
                }
                break;
            case R.id.cancel: //取消
                dismiss();
                break;
            case R.id.subtract_btn: //减号
                if (nums > 1) {
                    nums = nums - 1;
                }
                num_tv.setText(nums + "");
                break;
            case R.id.plus_btn: //加号
                if (nums < Integer.parseInt(storgeNum)) {
                    nums = nums + 1;
                }
                num_tv.setText(nums + "");
                break;
            default:
                break;
        }
    }

    /**
     * 页面赋值
     */
    private void changeData() {
        num_tv.setText(nums + "");
    }


}
