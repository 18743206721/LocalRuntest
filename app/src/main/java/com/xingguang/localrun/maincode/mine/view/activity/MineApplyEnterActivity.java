package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.xingguang.localrun.maincode.mine.model.MessageEvent;
import com.xingguang.localrun.maincode.mine.model.MineApplyBean;
import com.xingguang.localrun.maincode.mine.view.adapter.MineApplyAdapter;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.ClearEditText;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;


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
    TextView applyPullimg;
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
    @BindView(R.id.iv_img)
    ImageView iv_img;

    MineApplyAdapter adapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private String img = ""; //图片url
    private List<MineApplyBean.DataBean> list = new ArrayList<>();
    List<MineApplyBean.DataBean> selected = new ArrayList<>();
    StringBuilder result;

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
        adapter = new MineApplyAdapter(MineApplyEnterActivity.this,list);
        GridLayoutManager mgr = new GridLayoutManager(MineApplyEnterActivity.this, 4);
        applyList.setLayoutManager(mgr);
        applyList.setAdapter(adapter);
        loadList();
    }

    //传过来的标签集合数据
    public void setSelectedNum(List<MineApplyBean.DataBean> selectedlist) {
        //先将传过来的数据转换成String
        List<String> adselist = new ArrayList<>();
        for (int i = 0; i < selectedlist.size(); i++) {
            String b = selectedlist.get(i).getId();
            adselist.add(b);
        }
        //在将list结合，拼接成String类型的字符串
        result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : adselist) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        Log.e("setSelectedNum", "setSelectedNum: "+result.toString());
    }

    /**
     * 获取申请入驻的类别
     */
    private void loadList() {
        OkGo.<String>post(HttpManager.applyindex)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MineApplyBean mineApplyBean  = gson.fromJson(response.body().toString(), MineApplyBean.class);
                        adapter.setList(mineApplyBean.getData());
                        selected = adapter.getSelectList();
                    }
                });
    }


    @OnClick({R.id.rl_shangchuan_img, R.id.tv_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shangchuan_img: //上传图片
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(this, PhotoPicker.REQUEST_CODE);
                break;
            case R.id.tv_apply: //申请入驻
                load();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                selectedPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (selectedPhotos.size() != 0) {
                    img = selectedPhotos.get(0);
                }
                rlShangchuanImg.setVisibility(View.GONE);
                iv_img.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().initGlide(this).loadImage(selectedPhotos.get(0), iv_img);
            }
        }
    }

    private void load() {
        selected = adapter.getSelectList();
        Log.e("selected", "selected: "+adapter.getSelectList().size());
        setSelectedNum(selected);
        OkGo.<String>post(HttpManager.apply)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("true_name", applyName.getText().toString())
                .params("mobile", applyPhone.getText().toString())
                .params("company_name", applyCompany.getText().toString())
                .params("licence", new File(selectedPhotos.get(0))) //营业执照
                .params("company_describe", applyJieshao.getText().toString()) //公司介绍
                .params("cat_id", result.toString()) //入驻分类
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(),CommonBean.class);
                        MessageEvent messageEvent = new MessageEvent();
                        messageEvent.setMsg("1");
                        EventBus.getDefault().post(messageEvent);
                        ToastUtils.showToast(MineApplyEnterActivity.this,commonBean.getMsg());
                        finish();
                    }
                });
    }


}
