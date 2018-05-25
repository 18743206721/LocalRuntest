package com.xingguang.localrun.maincode.home.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseActivity;
import com.xingguang.localrun.maincode.home.view.adapter.HistoryListAdapter;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.SharedPreferencesUtils;
import com.xingguang.localrun.utils.ToastUtils;
import com.xingguang.localrun.view.ClearEditText;
import com.xingguang.localrun.view.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/19
 * 描述: 首页搜索页面
 * 作者:LiuYu
 */
public class HomeSearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tab_all)
    XTabLayout tabAll;
    @BindView(R.id.rv_tag)
    TagCloudLayout rv_tag;
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.iv_hostiry_dele)
    ImageView ivHostiryDele;
    @BindView(R.id.ll_history_search)
    LinearLayout llHistorySearch;
    @BindView(R.id.ll_search)
    LinearLayout ll_search;
    Timer timer = new Timer();

    HistoryListAdapter listAdapter;
    //搜索历史的集合
    private List<String> historList = new ArrayList<>();
    String search = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void initView() {

        listAdapter = new HistoryListAdapter(HomeSearchActivity.this, historList);
        rv_tag.setAdapter(listAdapter);
        huixian();
        initTab();
    }

    private void huixian() {
        String[] historSearch;
        if (!TextUtils.isEmpty((String) SharedPreferencesUtils.get(this, SharedPreferencesUtils.TOGETHERPLAY, ""))) {
            historSearch = ((String) SharedPreferencesUtils.get(this, SharedPreferencesUtils.TOGETHERPLAY, "")).split(",");
        } else {
            historSearch = new String[]{};
        }
        historList.clear();
        for (int i = 0, j = historSearch.length; i < j; i++) {
            historList.add(historSearch[i]);
        }
        listAdapter.setList(historList);
    }

    private void initTab() {
        tabAll.addTab(tabAll.newTab().setText("商品"));
        tabAll.addTab(tabAll.newTab().setText("店铺"));
        tabAll.addTab(tabAll.newTab().setText("代办"));

        tabAll.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
            }
        });

    }

    @OnClick({R.id.iv_finish, R.id.iv_hostiry_dele, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish://返回
                finish();
                break;
            case R.id.iv_hostiry_dele://删除历史记录
                listAdapter.removeList(historList);
                break;
            case R.id.tv_search: //搜索
                historyload();
                break;
        }
    }

    private void historyload() {
        if (!etSearch.getText().toString().equals("")) { //输入不为空
            initxianshi();

            search = etSearch.getText().toString();
            historList.add(search);
            listAdapter.setList(historList);

            saveHistory(historList);

        } else {//输入为空
            ToastUtils.showToast(HomeSearchActivity.this, "请输入搜索内容!");
        }

    }

    private void saveHistory(List<String> historList) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : historList) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        String a = result.toString();
        a = a.replaceAll("\\\\", "/");
        SharedPreferencesUtils.put(HomeSearchActivity.this, SharedPreferencesUtils.TOGETHERPLAY, a);
    }

    /**
     * 显示activity的搜索，隐藏键盘
     */
    private void initxianshi() {
        llHistorySearch.setVisibility(View.VISIBLE);//设置activity的搜索显示
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AppUtil.hideSoftInput(HomeSearchActivity.this);
            }
        }, 500);
    }


}
