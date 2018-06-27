package com.xingguang.localrun.maincode.home.view.activity;

import android.view.View;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;

/**
 *立即购买页面
 * */
public class BuyActivity extends ToolBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initView() {
        setToolBarTitle("确认订单");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        load();

    }


    private void load() {

    }


}
