package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.maincode.mine.model.MyCollectionBean;
import com.xingguang.localrun.maincode.mine.view.adapter.MyCollectionAdapter;
import com.xingguang.localrun.maincode.mine.view.adapter.OnItemClickListener;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.view.DeleteRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 创建日期：2018/5/21
 * 描述:收藏列表
 * 作者:LiuYu
 */
public class MyCollectionActivity extends ToolBarActivity {

    @BindView(R.id.rv_collection)
    DeleteRecyclerView rvCollection;
    @BindView(R.id.empty)
    ImageView empty;


    MyCollectionAdapter coladapter;
    List<MyCollectionBean.DataBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView() {

        getToolbarTitle().setText("收藏");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initAdapter();
        load();
        initListener();
    }

    private void initAdapter() {
        coladapter = new MyCollectionAdapter(MyCollectionActivity.this,list);
        LinearLayoutManager lmg = new LinearLayoutManager(MyCollectionActivity.this);
        rvCollection.setLayoutManager(lmg);
        rvCollection.setAdapter(coladapter);
    }

    private void load() {
        OkGo.<String>post(HttpManager.collectGoods)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MyCollectionBean bean = gson.fromJson(response.body().toString(), MyCollectionBean.class);
                        list.addAll(bean.getData());

                        if (list.size() == 0) {
                            rvCollection.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            rvCollection.setVisibility(View.VISIBLE);
                        }

                        coladapter.setList(list);
                    }
                });
    }


    private void initListener() {
        rvCollection.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(MyCollectionActivity.this, ProductdetailsActivity.class);
                intent.putExtra("goods_id", list.get(position).getGoods_id());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                loadcancel(position);
            }

            @Override
            public void onJinDianClick(int position) {
                Intent intent = new Intent(MyCollectionActivity.this, LookShopActivity.class);
                intent.putExtra("shopid", list.get(position).getShop_id());
                startActivity(intent);
            }
        });
    }

    /**取消收藏
     * @param position*/
    private void loadcancel(final int position) {
        OkGo.<String>post(HttpManager.delCollectGoods)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("goods_id",list.get(position).getGoods_id())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                        ToastUtils.showToast(MyCollectionActivity.this,bean.getMsg());
                        coladapter.removeItem(position);
                    }
                });
    }


}
