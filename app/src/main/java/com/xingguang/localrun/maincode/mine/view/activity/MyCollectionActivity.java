package com.xingguang.localrun.maincode.mine.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
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
    }

    private void initAdapter() {
        coladapter = new MyCollectionAdapter(MyCollectionActivity.this,list);
        LinearLayoutManager lmg = new LinearLayoutManager(MyCollectionActivity.this);
        rvCollection.setLayoutManager(lmg);
        rvCollection.setAdapter(coladapter);

        rvCollection.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast(MyCollectionActivity.this,"点击"+position);
            }

            @Override
            public void onDeleteClick(int position) {
                coladapter.removeItem(position);
            }

            @Override
            public void onJinDianClick(int position) {
                ToastUtils.showToast(MyCollectionActivity.this,"进店"+position);
            }
        });


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
                        coladapter.setList(list);
                    }
                });
    }


}
