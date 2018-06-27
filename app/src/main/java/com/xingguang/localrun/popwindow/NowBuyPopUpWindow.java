/**
 * @param
 */
package com.xingguang.localrun.popwindow;

import android.content.Context;
import android.os.Message;
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
import com.xingguang.localrun.maincode.home.model.SpecBean;
import com.xingguang.localrun.maincode.home.view.activity.HomeSearchActivity;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.maincode.home.view.adapter.PurchaseTagAdapter;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.TagCloudLayout;

import java.util.ArrayList;

/**
 * @param
 */
public class NowBuyPopUpWindow extends PopupWindow implements View.OnClickListener {

    //图片  减号   加号
    private ImageView title_img, subtract_btn, plus_btn;
    //价钱  库存   数量  确定
    private TextView money, inventory, num_tv, commit;
    //取消
    private ImageView cancel;

    //规格
    private TagCloudLayout id_flowlayout;
    //购买数量
    private String purchasenum;

    private Context context;
    //规格id
    String itemid = "";
    String store_count = "";//库存


    private PurchaseTagAdapter mAdapter;
    private ArrayList<SpecBean.DataBean> lists = new ArrayList<>();
    private int nums = 1;
    private String original_img; //图片
    private String keyname; //规格名字
    int type; //1是商品详情，2是搜索
    String goodsId = "";//店铺id


    public NowBuyPopUpWindow(Context contexts, View parent, ArrayList<SpecBean.DataBean> listss, String original_img, int nums,int type) {
        lists.clear();
        this.context = contexts;
        this.lists = listss;
        this.original_img = original_img;
        this.nums = nums;
        this.type = type;

        View view = View.inflate(context, R.layout.popup_now_buy, null);

        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.pop_enter_anim));

        //图片  减号   加号
        title_img = (ImageView) view.findViewById(R.id.title_img);
        subtract_btn = (ImageView) view.findViewById(R.id.subtract_btn);
        plus_btn = (ImageView) view.findViewById(R.id.plus_btn);
        subtract_btn.setOnClickListener(this);
        plus_btn.setOnClickListener(this);

        //价钱  库存  取消  数量  确定
        money = (TextView) view.findViewById(R.id.money);
        inventory = (TextView) view.findViewById(R.id.inventory);
        cancel = (ImageView) view.findViewById(R.id.cancel);
        num_tv = (TextView) view.findViewById(R.id.num_tv);
        commit = (TextView) view.findViewById(R.id.commit);
        //规格
        id_flowlayout = (TagCloudLayout) view.findViewById(R.id.id_flowlayout);

        changeData();

        //确认购买
        commit.setOnClickListener(this);

        //取消
        cancel.setOnClickListener(this);

        ImageLoader.getInstance().initGlide(contexts).loadImage(original_img, title_img);


        for (int i = 0, j = lists.size(); i < j; i++) {
            if ("1".equals(lists.get(i).getIsClick())) {
                money.setText(lists.get(i).getPrice());
                inventory.setText("库存：" + lists.get(i).getStore_count());
                store_count = lists.get(i).getStore_count();
                purchasenum = nums + "";
            }
        }

        //规格
        mAdapter = new PurchaseTagAdapter(context, lists);
        id_flowlayout.setAdapter(mAdapter);
        mAdapter.setUpdateClick(new PurchaseTagAdapter.UpdateListener() {
            @Override
            public void UpdateClick(int position) {
                for (int i = 0, j = lists.size(); i < j; i++) {
                    lists.get(i).setIsClick("0");
                }
                lists.get(position).setIsClick("1");
                money.setText(lists.get(position).getPrice());
                store_count = lists.get(position).getStore_count();
                inventory.setText("库存：" + lists.get(position).getStore_count());
                mAdapter.notifyDataSetChanged();
            }
        });

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

                if (type == 1) { //产品
                ProductdetailsActivity activity = (ProductdetailsActivity) context;
                Message msg1 = activity.handler.obtainMessage();
                msg1.what = 1;
                for (int i = 0, j = lists.size(); i < j; i++) {
                    if ("1".equals(lists.get(i).getIsClick())) {
                        keyname = lists.get(i).getKey_name();
                        itemid = lists.get(i).getItem_id();
                    }
                }
                msg1.obj = nums + " " + itemid + " " + keyname + " " ;
                ProductdetailsActivity.instance.handler.sendMessage(msg1);
                dismiss();

                } else if (type == 2) { //搜索

                    HomeSearchActivity activity = (HomeSearchActivity) context;
                    Message msg1 = activity.handler.obtainMessage();
                    msg1.what = 1;
                    for (int i = 0, j = lists.size(); i < j; i++) {
                        if ("1".equals(lists.get(i).getIsClick())) {
                            keyname = lists.get(i).getKey_name();
                            itemid = lists.get(i).getItem_id();
                            goodsId = lists.get(i).getGoods_id();
                        }
                    }
                    msg1.obj = nums + " " + itemid + " " + keyname + " " ;
                    HomeSearchActivity.instance.handler.sendMessage(msg1);

                    OkGo.<String>post(HttpManager.addCart)
                            .tag(this)
                            .cacheKey("cachePostKey")
                            .cacheMode(CacheMode.DEFAULT)
                            .params("token", AppUtil.getUserId(context))
                            .params("goods_id", goodsId)
                            .params("goods_num", nums)
                            .params("item_id", itemid)
                            .execute(new DialogCallback<String>(HomeSearchActivity.instance) {
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
                if (nums < Integer.parseInt(store_count)) {
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

//    /**
//     * 添加到购物车
//     * @param nums
//     * @param itemid
//     */
//    private void loadaddCar(int nums, String itemid) {
//
//    }


}
