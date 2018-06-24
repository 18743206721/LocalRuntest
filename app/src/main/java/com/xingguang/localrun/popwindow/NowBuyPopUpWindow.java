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

import com.xingguang.localrun.R;
import com.xingguang.localrun.main.view.MainActivity;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.maincode.home.view.adapter.ProductTagAdapter;
import com.xingguang.localrun.maincode.shop.model.GoodInfo;
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
    //限购份数
    private String purchasenum, newPrice, oldPrice;

    private Context context;

    private ProductTagAdapter mAdapter;
    private ArrayList<GoodInfo.DataBean.CartListBean> lists;

    private int nums = 1;
    private int type = 0;
    private int position;

    public NowBuyPopUpWindow(Context contexts, View parent, ArrayList<GoodInfo.DataBean.CartListBean> listss, int nums, int position, int type) {
        this.context = contexts;
        this.lists = listss;
        this.nums = nums;
        this.position = position;
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

//        for (int i = 0, j = lists.size(); i < j; i++) {
//            if ("1".equals(lists.get(i).getIsClick())) {
//                ImageLoader.loadRoundImage(context, lists.get(i).getImg(), title_img, 10);
//                money.setText(lists.get(i).getNewprice());
//                inventory.setText("库存：" + lists.get(i).getStock());
//                purchasenum = lists.get(i).getStock() + "";
//                newPrice = lists.get(i).getNewprice();
//                oldPrice = lists.get(i).getOldPrice();
//            }
//        }

        //规格
//        mAdapter = new PurchaseTagAdapter(context, lists);
//        id_flowlayout.setAdapter(mAdapter);
//        mAdapter.setUpdateClick(new PurchaseTagAdapter.UpdateListener() {
//            @Override
//            public void UpdateClick(int position) {
//                for (int i = 0, j = lists.size(); i < j; i++) {
//                    lists.get(i).setIsClick("0");
//                }
//                lists.get(position).setIsClick("1");
//                ImageLoader.loadRoundImage(context, lists.get(position).getImg(), title_img, 10);
//                money.setText(lists.get(position).getNewprice());
//                inventory.setText("库存：" + lists.get(position).getStock());
//                purchasenum = lists.get(position).getStock() + "";
//                newPrice = lists.get(position).getNewprice();
//                oldPrice = lists.get(position).getOldPrice();
//                mAdapter.notifyDataSetChanged();
//            }
//        });

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
            case R.id.commit: //确认购买

                if (type == 1) { //产品
                    ProductdetailsActivity activity = (ProductdetailsActivity) context;

                    Message msg1 = activity.handler
                            .obtainMessage();
                    msg1.what = 1;
                    String specificationId = "";
                    String specification = "";
//                for (int i = 0, j = lists.size(); i < j; i++) {
//                    if ("1".equals(lists.get(i).getIsClick())) {
//                        specificationId = lists.get(i).getId();
//                        specification = lists.get(i).getName();
//                    }
//                }
                    msg1.obj = nums + " " + specificationId + " " + specification
                            + " " + newPrice + " " + oldPrice;
                    ProductdetailsActivity.instance.handler.sendMessage(msg1);
                    dismiss();

                } else if (type == 2) { //购物车
                    MainActivity activity = (MainActivity) context;

                    Message msg1 = activity.handler.obtainMessage();
                    msg1.what = 1;
                    String itemid = "";
                    String goodsid = "";
                    for (int i = 0; i < lists.size(); i++) {
                        if (position == i) {
                            itemid = lists.get(i).getId();
                            goodsid = lists.get(i).getGoods_id();
                        }
                    }
                    msg1.obj = nums + " " + itemid + " " + goodsid;
//                            + " " + newPrice + " " + oldPrice;
                    MainActivity.instance.handler.sendMessage(msg1);

                    dismiss();

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
//                if (nums < Integer.parseInt(purchasenum)) {
                    nums = nums + 1;
//                }
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
