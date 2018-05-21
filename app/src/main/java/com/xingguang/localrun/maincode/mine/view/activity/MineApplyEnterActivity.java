package com.xingguang.localrun.maincode.mine.view.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/21
 * 描述:申请入驻
 * 作者:LiuYu
 */
public class MineApplyEnterActivity extends ToolBarActivity {

    @BindView(R.id.apply_name)
    ClearEditText applyName;
    @BindView(R.id.apply_phone)
    ClearEditText applyPhone;
    @BindView(R.id.apply_company)
    ClearEditText applyCompany;
    @BindView(R.id.apply_list)
    RecyclerView applyList;
    @BindView(R.id.apply_pullimg)
    ClearEditText applyPullimg;
    @BindView(R.id.tv_shangchuan_img)
    TextView tvShangchuanImg;
    @BindView(R.id.rl_shangchuan_img)
    RelativeLayout rlShangchuanImg;
    @BindView(R.id.apply_jieshao)
    ClearEditText applyJieshao;
    @BindView(R.id.tv_jieshao)
    TextView tvJieshao;
    @BindView(R.id.tv_apply)
    TextView tvApply;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_apply_enter;
    }

    @Override
    protected void initView() {
        setToolBarTitle("申请入驻");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @OnClick({R.id.rl_shangchuan_img, R.id.tv_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shangchuan_img: //上传图片
                break;
            case R.id.tv_apply: //申请入驻
                break;
        }
    }




}
