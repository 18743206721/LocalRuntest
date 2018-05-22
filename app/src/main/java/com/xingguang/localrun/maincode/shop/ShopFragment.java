package com.xingguang.localrun.maincode.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarFragment;
import com.xingguang.localrun.maincode.shop.model.GoodInfo;
import com.xingguang.localrun.maincode.shop.view.adapter.ShopCarAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.utils.ToastUtils;

import java.util.ArrayList;
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


    ShopCarAdapter shopCarAdapter;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    List<GoodInfo> shoplist = new ArrayList<>();
    private boolean flag = false;

    private int currentPositon = 0;
    private TextPopUpWindow popde;
    private View.OnClickListener node;
    private View.OnClickListener yesde;

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
        load();
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
            public void OnItemEdit(TextView edit, int position) {
                ToastUtils.showToast(getActivity(), "修改" + position);
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

    /**
     * 删除该商品
     */
    private void loaddeleted(int pos) {
        shoplist.remove(pos);
        popde.dismiss();
        shopCarAdapter.notifyDataSetChanged();
        ToastUtils.showToast(getActivity(), "删除成功!" + pos);
        statistics();

    }

    private void load() {
        for (int i = 0; i < 2; i++) {
            GoodInfo shoppingCartBean = new GoodInfo();
            shoppingCartBean.setName("保时捷商店");
            shoppingCartBean.setId(i + "");
            shoppingCartBean.setPrice(30.6);
            shoppingCartBean.setCount("1");
            shoplist.add(shoppingCartBean);
        }
        for (int i = 0; i < 2; i++) {
            GoodInfo shoppingCartBean = new GoodInfo();
            shoppingCartBean.setName("三国第二刑道荣");
            shoppingCartBean.setId(i + "");
            shoppingCartBean.setPrice(10.6);
            shoppingCartBean.setCount("1");
            shoplist.add(shoppingCartBean);
        }
        shopCarAdapter.setList(shoplist);
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
        for (GoodInfo group : shoplist) {
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
            GoodInfo shoppingCartBean = shoplist.get(i);
            if (shoppingCartBean.isChoose()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * Integer.parseInt(shoppingCartBean.getCount());
            }
        }
        tvTotalPrice.setText("合计:" + totalPrice);
    }


    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        //选中的需要提交的商品清单
        for (GoodInfo bean:shoplist ){
            boolean choosed = bean.isChoose();
            if (choosed){
                String shoppingName = bean.getName();
                String count = bean.getCount();
                double price = bean.getPrice();
                String id = bean.getId();
                Log.d("shopfragment",id+"----id---"+shoppingName+"---"+count+"---"+price);
            }
        }
        ToastUtils.showToast(getActivity(),"总价："+totalPrice);
        //跳转到支付界面
    }



}
