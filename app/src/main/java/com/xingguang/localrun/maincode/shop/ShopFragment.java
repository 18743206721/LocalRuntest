package com.xingguang.localrun.maincode.shop;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarFragment;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.shop.model.GoodInfo;
import com.xingguang.localrun.maincode.shop.view.adapter.ShopCarAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.utils.AppUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 购物车
 */
public class ShopFragment extends ToolBarFragment implements ShopCarAdapter.CheckInterface {

    @BindView(R.id.rv_cart)
    RecyclerView rvCart;
    @BindView(R.id.all_chekbox)
    CheckBox allChekbox;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_go_to_pay)
    TextView tvGoToPay;
    @BindView(R.id.ll_cart)
    LinearLayout llCart;
    @BindView(R.id.fragment_ll_shop)
    LinearLayout fragment_ll_shop;


    ShopCarAdapter shopCarAdapter;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    ArrayList<GoodInfo.DataBean.CartListBean> shoplist = new ArrayList<>();
    private boolean flag = false;

    private int currentPositon = 0;
    private TextPopUpWindow popde;
    private View.OnClickListener node;
    private View.OnClickListener yesde;
    private ArrayList<GoodInfo.DataBean.CartListBean> lists = new ArrayList<>();
    //购买件数
    private int nums = 1;
    //规格ID   类型  提醒
    private String id, specificationId, type;
    TextView tvGuige;//规格

    String itemid;
    String goodsid;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {
        setToolBarTitle("购物车");
        setSubTitle("编辑");

        getSubTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = !flag;
                if (flag) {
                    setSubTitle("完成");
                    shopCarAdapter.isShow(false);
                } else {
                    setSubTitle("编辑");
                    shopCarAdapter.isShow(true);
                }

            }
        });

        shopCarAdapter = new ShopCarAdapter(getActivity(), shoplist);
        LinearLayoutManager lmg = new LinearLayoutManager(getActivity());
        rvCart.setLayoutManager(lmg);
        rvCart.setAdapter(shopCarAdapter);

        shopCarAdapter.setCheckInterface(this);

        load();
        initListener();
    }


    private void initListener() {
        shopCarAdapter.setmOnItemClickListener(new ShopCarAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //跳转到商品详情页面
                ToastUtils.showToast(getActivity(), "跳转到商品详情" + position);

            }
        });

        //修改
        shopCarAdapter.setmOnItemEditListener(new ShopCarAdapter.OnItemEditListener() {
            @Override
            public void OnItemEdit(TextView edit, TextView tv_shop_guige, int position) {
                tvGuige = tv_shop_guige;

                nums = Integer.parseInt(shoplist.get(position).getGoods_num());
//                new NowBuyPopUpWindow(getActivity(), fragment_ll_shop, shoplist, nums, position, 2);
            }
        });

        //删除
        shopCarAdapter.setmOnItemDeletedListener(new ShopCarAdapter.OnItemDeletedListener() {
            @Override
            public void OnItemDeleted(TextView deleted, int position) {
                currentPositon = position;
                popde = new TextPopUpWindow(getActivity(), fragment_ll_shop, "是否删除该商品?", "取消", "确定", node, yesde);
            }
        });

        node = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popde.dismiss();
            }
        };
        yesde = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaddeleted(currentPositon);
            }
        };
        //选中
//        shopCarAdapter.setmOnItemCheckListener(new ShopCarAdapter.OnItemCheckListener() {
//            @Override
//            public void OnItemCheck(CheckBox check, int position) {
//                ToastUtils.showToast(getActivity(),"选中"+position);
//            }
//        });
    }

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    nums = Integer.parseInt(msg.obj.toString().split("\\ ")[0]);
                    itemid = msg.obj.toString().split("\\ ")[1];
                    goodsid = msg.obj.toString().split("\\ ")[2];

//                    loadedit(nums, itemid, goodsid);
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
     * 修改购物车数量接口
     */
    public void loadedit(int nums, String itemid, String goodsid) {
        OkGo.<String>post(HttpManager.updateCart)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .params("id", goodsid)
                .params("goods_num", nums)
                .params("item_id", itemid)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean jianjieBean = gson.fromJson(response.body().toString(), CommonBean.class);
                        ToastUtils.showToast(getActivity(), jianjieBean.getMsg());
                        shopCarAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 商品规格
     */
    private void falshsaleCommoditySize(String id) {
//        lists.clear();
//        lists.addAll(model.getList());
    }

    /**
     * 删除该商品
     */
    private void loaddeleted(int pos) {
        OkGo.<String>post(HttpManager.Cartcart2)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        GoodInfo jianjieBean = gson.fromJson(response.body().toString(), GoodInfo.class);
                        if (jianjieBean.getData() != null) {
                            shoplist.addAll(jianjieBean.getData().getCartList());
                            shopCarAdapter.setList(jianjieBean.getData().getCartList());
                        } else {
                            ToastUtils.showToast(getActivity(), jianjieBean.getMsg());
                        }
                    }
                });



        shoplist.remove(pos);
        popde.dismiss();
        shopCarAdapter.notifyDataSetChanged();
        ToastUtils.showToast(getActivity(), "删除成功!" + pos);
        statistics();
    }

    /**
     * 购物车列表
     */
    private void load() {
        OkGo.<String>post(HttpManager.Cartcart2)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        GoodInfo jianjieBean = gson.fromJson(response.body().toString(), GoodInfo.class);
                        if (jianjieBean.getData() != null) {
                            shoplist.addAll(jianjieBean.getData().getCartList());
                            shopCarAdapter.setList(jianjieBean.getData().getCartList());
                        } else {
                            ToastUtils.showToast(getActivity(), jianjieBean.getMsg());
                        }
                    }
                });
    }

    @Override
    protected void lazyLoad() {
        //写网络请求

    }

    @OnClick({R.id.tv_go_to_pay, R.id.all_chekbox})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_chekbox://全选
                if (shoplist.size() != 0) {
                    if (allChekbox.isChecked()) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            shoplist.get(i).setChoose(true);
                        }
                        shopCarAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoplist.size(); i++) {
                            shoplist.get(i).setChoose(false);
                        }
                        shopCarAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
//                doCheckAll();
                break;
            case R.id.tv_go_to_pay: //结算
                lementOnder();
                break;
        }
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int j = 0; j < shoplist.size(); j++) {
            shoplist.get(j).setChoose(allChekbox.isChecked());
        }
        shopCarAdapter.notifyDataSetChanged();
//        calculate();
    }

    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoplist.get(position).setChoose(isChecked);
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        shopCarAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {
        for (GoodInfo.DataBean.CartListBean group : shoplist) {
            if (!group.isChoose())
                return false;
        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoplist.size(); i++) {
            GoodInfo.DataBean.CartListBean shoppingCartBean = shoplist.get(i);
            if (shoppingCartBean.isChoose()) {
                totalCount++;
                totalPrice += Double.parseDouble(shoppingCartBean.getGoods_price()) * Double.parseDouble(shoppingCartBean.getGoods_num());
            }
        }
        tvTotalPrice.setText("合计:" + totalPrice);
    }


    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        //选中的需要提交的商品清单
        for (GoodInfo.DataBean.CartListBean bean : shoplist) {
            boolean choosed = bean.isChoose();
            if (choosed) {
                String shoppingName = bean.getGoods_name();
                String count = bean.getGoods_num();
                double price = Double.parseDouble(bean.getGoods_price());
                String id = bean.getId();
                Log.d("shopfragment", id + "----id---" + shoppingName + "---" + count + "---" + price);
            }
        }
        ToastUtils.showToast(getActivity(), "总价：" + totalPrice);
        //跳转到支付界面
    }


}
