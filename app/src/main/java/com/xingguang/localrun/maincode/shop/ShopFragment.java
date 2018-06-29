package com.xingguang.localrun.maincode.shop;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.xingguang.localrun.maincode.home.model.SpecBean;
import com.xingguang.localrun.maincode.home.view.activity.BuyActivity;
import com.xingguang.localrun.maincode.home.view.adapter.PurchaseTagAdapter;
import com.xingguang.localrun.maincode.shop.model.GoodInfo;
import com.xingguang.localrun.maincode.shop.view.adapter.ShopCarAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.TagCloudLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;

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

    String itemid = "";
    String goodsid = "";

    List<String> checkid = new ArrayList<>(); //checkid
    //商品规格列表
    private ArrayList<SpecBean.DataBean> specBeanList = new ArrayList<>();
    String original_img = ""; //规格里的图片
    private String storgeNum = "";//商品详情里的规格库存
    private String keyname = "";//商品规格

    private boolean isFlag;
    private int checkpos = 0; //checkbox点击的position
    private String a = ""; //选中的cartlistid

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
        initListener();
    }

//        OkGo.<String>post(HttpManager.GoodsDetail)
//                .tag(this)
//                .cacheKey("cachePostKey")
//                .cacheMode(CacheMode.DEFAULT)
//                .params("token", AppUtil.getUserId(getActivity()))
//                .params("goods_id", shoplist.get(position).getGoods_id())
//                .execute(new DialogCallback<String>(getActivity()) {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Gson gson = new Gson();
//                        GoodsDetailsBean bean = gson.fromJson(response.body().toString(), GoodsDetailsBean.class);
//                        if (bean.getData() != null) {
//
//                        }
//                    }
//                });

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
                loadGoodGuige(position);
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
                popde.dismiss();
                loaddeleted(currentPositon);
            }
        };
    }

    /**
     * 商品规格,
     * 修改购物车数量接口
     */
    private void loadGoodGuige(final int position) {
        OkGo.<String>post(HttpManager.spec)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("goods_id", shoplist.get(position).getGoods_id())
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        SpecBean bean = gson.fromJson(response.body().toString(), SpecBean.class);
                        if (bean.getData() != null) {
                            if (bean.getData().size() != 0) { //有规格时
                                specBeanList.clear();
                                specBeanList.addAll(bean.getData());
                                for (int i = 0, j = specBeanList.size(); i < j; i++) {
                                    if (itemid.equals(specBeanList.get(i).getItem_id())) {
                                        specBeanList.get(i).setIsClick("1");
                                    } else {
                                        specBeanList.get(i).setIsClick("0");
                                    }
                                }
                                showPopwindow(position, 1);
                            } else {
                                //添加购物车接口，无规格时，不传itemid
                                showPopwindow(position, 2);
                            }
                        } else {
                            ToastUtils.showToast(getActivity(), bean.getMsg());
                        }
                    }
                });
    }

    //弹出popwindwo
    private void showPopwindow(final int postion, final int type) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_now_buy, null, false);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT, true);
        view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.pop_enter_anim));
        //图片  减号   加号
        ImageView title_img = (ImageView) view.findViewById(R.id.title_img);
        ImageView subtract_btn = (ImageView) view.findViewById(R.id.subtract_btn);
        ImageView plus_btn = (ImageView) view.findViewById(R.id.plus_btn);

        //价钱  库存  取消  数量  确定
        final TextView money = (TextView) view.findViewById(R.id.money);
        final TextView inventory = (TextView) view.findViewById(R.id.inventory);
        ImageView cancel = (ImageView) view.findViewById(R.id.cancel);
        final TextView num_tv = (TextView) view.findViewById(R.id.num_tv);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        //规格
        TagCloudLayout id_flowlayout = (TagCloudLayout) view.findViewById(R.id.id_flowlayout);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.update();

        original_img = HttpManager.INDEX + shoplist.get(postion).getGoods().getOriginal_img();
        storgeNum = shoplist.get(postion).getGoods().getStore_count();
        ImageLoader.getInstance().initGlide(getActivity()).loadImage(original_img, title_img);
        inventory.setText("库存：" + storgeNum);
        num_tv.setText(nums + "");

        //规格
        if (type == 1) {
            final PurchaseTagAdapter mAdapter = new PurchaseTagAdapter(getActivity(), specBeanList);
            id_flowlayout.setAdapter(mAdapter);
            mAdapter.setUpdateClick(new PurchaseTagAdapter.UpdateListener() {
                @Override
                public void UpdateClick(int position) {
                    for (int i = 0, j = specBeanList.size(); i < j; i++) {
                        specBeanList.get(i).setIsClick("0");
                    }
                    specBeanList.get(position).setIsClick("1");
                    money.setText(specBeanList.get(position).getPrice());
                    storgeNum = specBeanList.get(position).getStore_count();
                    itemid = specBeanList.get(position).getItem_id();
                    inventory.setText("库存：" + specBeanList.get(position).getStore_count());
                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        subtract_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nums > 1) {
                    nums = nums - 1;
                }
                num_tv.setText(nums + "");
            }
        });
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!storgeNum.equals("")) {
                    if (nums < Integer.parseInt(storgeNum)) {
                        nums = nums + 1;
                    }
                    num_tv.setText(nums + "");
                }
            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    if ("".equals(itemid)) {
                        ToastUtils.showToast(getActivity(), "请选择规格！");
                    } else {
                        for (int i = 0, j = specBeanList.size(); i < j; i++) {
                            if ("1".equals(specBeanList.get(i).getIsClick())) {
                                keyname = specBeanList.get(i).getKey_name();
                                itemid = specBeanList.get(i).getItem_id();
                                goodsid = specBeanList.get(i).getGoods_id();
                            }
                        }
                        OkGo.<String>post(HttpManager.CartupdateCart)
                                .tag(this)
                                .cacheKey("cachePostKey")
                                .cacheMode(CacheMode.DEFAULT)
                                .params("token", AppUtil.getUserId(getActivity()))
                                .params("id", shoplist.get(postion).getId())
                                .params("goods_num", nums)
                                .params("item_id", itemid)
                                .execute(new DialogCallback<String>(getActivity()) {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        Gson gson = new Gson();
                                        CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                                        popupWindow.dismiss();
                                        ToastUtils.showToast(getActivity(), bean.getMsg());
                                        for (int i = 0; i < shoplist.size(); i++) {
                                            if (postion == i) {
                                                shoplist.get(i).setGoods_num(nums + "");
                                                shoplist.get(i).setSpec_key_name(keyname);
                                            }
                                        }
                                        shopCarAdapter.setList(shoplist);
                                    }
                                });
                    }


                } else if (type == 2) { //商品无规格
                    OkGo.<String>post(HttpManager.CartupdateCart)
                            .tag(this)
                            .cacheKey("cachePostKey")
                            .cacheMode(CacheMode.DEFAULT)
                            .params("token", AppUtil.getUserId(getActivity()))
                            .params("id", shoplist.get(postion).getId())
                            .params("goods_num", nums)
                            .params("item_id", "")
                            .execute(new DialogCallback<String>(getActivity()) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                                    popupWindow.dismiss();
                                    ToastUtils.showToast(getActivity(), bean.getMsg());
                                    for (int i = 0; i < shoplist.size(); i++) {
                                        if (postion == i) {
                                            shoplist.get(i).setGoods_num(nums + "");
                                        }
                                    }

                                    shopCarAdapter.setList(shoplist);
                                }
                            });
                }


            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        load(1);

//        for (int i = 0; i < checkid.size(); i++) {
//            shoplist.get(i).setChoose(true);
//            if (isAllCheck())
//                allChekbox.setChecked(true);
//            else
//                allChekbox.setChecked(false);
//        }
//        shopCarAdapter.notifyDataSetChanged();
//        statistics();

    }

    /**
     * 删除该商品
     */
    private void loaddeleted(final int pos) {
        OkGo.<String>post(HttpManager.Cartdelete)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .params("cart_id", shoplist.get(pos).getId())
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                        shopCarAdapter.setRemove(pos);
                        ToastUtils.showToast(getActivity(), bean.getMsg());
                        tvTotalPrice.setText(0+"");
                        statistics();
                        load(1);
                    }
                });
    }

    /**
     * 购物车列表
     */
    private void load(final int i) {
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
                            shoplist.clear();
                            shoplist.addAll(jianjieBean.getData().getCartList());

                            if (jianjieBean.getData().getCartList().size() == 0) {
                                ll_empty.setVisibility(View.VISIBLE);
                                llCart.setVisibility(View.GONE);
                            } else {
                                ll_empty.setVisibility(View.GONE);
                                llCart.setVisibility(View.VISIBLE);
                            }

                            if (i == 2) {

                                String str = a;
                                List<String> resulist = Arrays.asList(str.split(","));

                                for (int j = 0; j < resulist.size(); j++) {
                                    for (int k = 0; k < shoplist.size(); k++) {
                                        if (resulist.get(j).equals(shoplist.get(k).getId())) {
                                            shoplist.get(k).setChoose(true);
                                            if (isAllCheck())
                                                allChekbox.setChecked(true);
                                            else
                                                allChekbox.setChecked(false);
                                            shopCarAdapter.notifyDataSetChanged();
                                        }
                                    }

                                }

                                tvTotalPrice.setText(jianjieBean.getData().getCartPriceInfo().getTotal_fee()+"");

                            } else if (i == 3) {
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
                                tvTotalPrice.setText("" + jianjieBean.getData().getCartPriceInfo().getTotal_fee());
                            }

                            shopCarAdapter.setList(jianjieBean.getData().getCartList());
                        } else {
                            ToastUtils.showToast(getActivity(), jianjieBean.getMsg());
                        }
                    }
                });
    }

    @Override
    protected void lazyLoad() {
    }

    @OnClick({R.id.tv_go_to_pay, R.id.all_chekbox})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_chekbox://全选
                if (shoplist.size() != 0) {
                    if (allChekbox.isChecked()) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            shoplist.get(i).setChoose(true);
                            checkid.add(shoplist.get(i).getId());
                        }
                        shopCarAdapter.notifyDataSetChanged();
                    } else {
//                        loadcheck(position, isChecked,checkid);
                        for (int i = 0; i < shoplist.size(); i++) {
                            shoplist.get(i).setChoose(false);
                            checkid.remove(shoplist.get(i).getId());
                        }
                        shopCarAdapter.notifyDataSetChanged();
                    }
                    StringBuilder result = new StringBuilder();
                    boolean first = true;
                    //第一个前面不拼接","
                    for (String string : checkid) {
                        if (first) {
                            first = false;
                        } else {
                            result.append(",");
                        }
                        result.append(string);
                    }
                    String a = result.toString();
                    a = a.replaceAll("\\\\", "/");
                    OkGo.<String>post(HttpManager.CartselectCart)
                            .tag(this)
                            .cacheKey("cachePostKey")
                            .cacheMode(CacheMode.DEFAULT)
                            .params("token", AppUtil.getUserId(getActivity()))
                            .params("id", a)
                            .execute(new DialogCallback<String>(getActivity()) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    load(3);
                                }
                            });
                }
//                statistics();
                break;
            case R.id.tv_go_to_pay: //结算
                lementOnder();
                break;
        }
    }

    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {

//        shoplist.get(position).setChoose(isChecked);
//        if (isAllCheck())
//            allChekbox.setChecked(true);
//        else
//            allChekbox.setChecked(false);
//        shopCarAdapter.notifyDataSetChanged();
//        statistics();

        checkpos = position;
        isFlag = isChecked;
        shoplist.get(position).setChoose(isChecked);
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        shopCarAdapter.notifyDataSetChanged();

        if (checkid.size() != 0) {
            if (shoplist.get(position).isChoose()) {
                checkid.add(shoplist.get(position).getId());
            } else {
                checkid.remove(shoplist.get(checkpos).getId());
            }
        } else {
            checkid.add(shoplist.get(position).getId());
        }

        loadcheck(position, isChecked, checkid);
    }

    /**
     * 单选接口
     */
    private void loadcheck(final int position, final boolean isChecked, List<String> checklist) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : checklist) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        a = result.toString();
        a = a.replaceAll("\\\\", "/");
        OkGo.<String>post(HttpManager.CartselectCart)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .params("id", a)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        load(2);
                    }
                });
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
        tvTotalPrice.setText(totalPrice+"");
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
        //跳转到订单结算界面
        startActivity(new Intent(getActivity(), BuyActivity.class)
                .putExtra("totalPrice", tvTotalPrice.getText().toString())
        );

    }


}
