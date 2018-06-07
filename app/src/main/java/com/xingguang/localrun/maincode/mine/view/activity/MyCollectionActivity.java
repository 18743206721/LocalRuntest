package com.xingguang.localrun.maincode.mine.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.mine.view.adapter.MyCollectionAdapter;
import com.xingguang.localrun.maincode.mine.view.adapter.OnItemClickListener;
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
    List<String> list = new ArrayList<>();

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
        for (int i = 0; i < 10; i++) {

            list.add(i + "");
        }

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

    }


}
