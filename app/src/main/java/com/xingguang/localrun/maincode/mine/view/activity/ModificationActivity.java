package com.xingguang.localrun.maincode.mine.view.activity;

import android.view.View;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/21
 * 描述:修改设置界面的名称
 * 作者:LiuYu
 */
public class ModificationActivity extends ToolBarActivity {

    @BindView(R.id.et_modif_bianliang)
    ClearEditText etModifBianliang;
    @BindView(R.id.tv_zhuxiao)
    TextView tvZhuxiao;
    String type ="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modification;
    }

    @Override
    protected void initView() {

        type = getIntent().getStringExtra("type");
        if (type.equals("1")){
            getToolbarTitle().setText(getIntent().getStringExtra("huiyuan"));
        }else {
            getToolbarTitle().setText(getIntent().getStringExtra("phone"));
        }
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick(R.id.tv_zhuxiao)
    public void onViewClicked() {
        load();
    }


    private void load() {

    }


}
