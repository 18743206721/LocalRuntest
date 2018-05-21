package com.xingguang.localrun.maincode.mine.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.mine.view.adapter.MyAttentionAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyGuanzhuActivity extends ToolBarActivity {

    @BindView(R.id.rv_attention)
    RecyclerView rvAttention;
    @BindView(R.id.ll_myattention)
    LinearLayout ll_myattention;

    MyAttentionAdapter attenAdapter;
    private List<String> attenList = new ArrayList<>();
    private int currentPositon = 0;
    private TextPopUpWindow popde;
    private View.OnClickListener node;
    private View.OnClickListener yesde;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_guanzhu;
    }

    @Override
    protected void initView() {
        getToolbarTitle().setText("关注");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        attenAdapter = new MyAttentionAdapter(MyGuanzhuActivity.this,attenList);
        LinearLayoutManager lmg = new LinearLayoutManager(MyGuanzhuActivity.this);
        rvAttention.setLayoutManager(lmg);
        rvAttention.setAdapter(attenAdapter);

        initListener();

        load();
    }

    private void initListener() {
        attenAdapter.setOnItemCancelClickLitener(new MyAttentionAdapter.OnItemCancelClickLitener() {
            @Override
            public void onItemCancelClick(TextView view, int position) {

                currentPositon = position;
                popde = new TextPopUpWindow(MyGuanzhuActivity.this, ll_myattention, "是否取消关注?", "取消", "确定", node, yesde);
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
                loadcancel(currentPositon);
            }
        };



    }

    /**
     * 取消关注接口
     * */
    private void loadcancel(int currentPositon) {
        ToastUtils.showToast(MyGuanzhuActivity.this,"已取消关注");
        popde.dismiss();

    }


    private void load() {

    }


}
