package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.xingguang.localrun.maincode.mine.view.adapter.PinglunPhotosAdapter;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.BitmapUtil;
import com.xingguang.localrun.view.Startwo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;


/**
 * 创建日期：2018/5/24
 * 描述:评论页面
 * 作者:LiuYu
 */
public class PingLunActivity extends ToolBarActivity {

    @BindView(R.id.et_pinglun_content)
    EditText etPinglunContent;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.gv_images)
    RecyclerView gvImages;
    @BindView(R.id.iv_selected)
    ImageView ivSelected;
    @BindView(R.id.tv_selected)
    TextView tvSelected;
    @BindView(R.id.rl_photo)
    RelativeLayout rlPhoto;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.iv_tice_star)
    Startwo iv_tice_star;

    private ArrayList<String> photos = new ArrayList<>();
    private ArrayList<String> photolist = new ArrayList<>(); //压缩后的图片路径
    PinglunPhotosAdapter adapter;
    private String recid;
    private double score = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ping_lun;
    }

    @Override
    protected void initView() {
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setToolBarTitle("添加评价");
        recid = getIntent().getStringExtra("rec_id");
        initAdapter();
        initListener();
    }


    private void initAdapter() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        gvImages.setLayoutManager(manager);
        adapter = new PinglunPhotosAdapter(photolist, PingLunActivity.this);
        gvImages.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setAddPhotosListener(new PinglunPhotosAdapter.AddPhotosListener() {
            @Override
            public void addPhotos() {
                photolist.clear();
                PhotoPicker.builder()
                        //设置选择个数
                        .setPhotoCount(3)
                        //选择界面第一个显示拍照按钮
                        .setShowCamera(true)
                        //选择时点击图片放大浏览
                        .setPreviewEnabled(true)
                        //附带已经选中过的图片
//                        .setSelected(photos)
                        .start(PingLunActivity.this);
            }
        });

        iv_tice_star.setStarChangeLister(new Startwo.OnStarChangeListener() {
            @Override
            public void onStarChange(Float mark) {
                score = mark;
            }
        });


    }

    @OnClick({R.id.rl_photo, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_photo: //选择图片
                PhotoPicker.builder()
                        //设置选择个数
                        .setPhotoCount(3)
                        //选择界面第一个显示拍照按钮
                        .setShowCamera(true)
                        //选择时点击图片放大浏览
                        .setPreviewEnabled(true)
                        .start(PingLunActivity.this);
                break;
            case R.id.tv_commit: //提交
                if (etPinglunContent.getText().length() == 0) {
                    ToastUtils.showToast(PingLunActivity.this, "请输入评论内容!");
                } else if (score == 0) {
                    ToastUtils.showToast(PingLunActivity.this, "请选择星级评级!");
                } else {
                    load();
                }
                break;
        }
    }


    private void load() {
        if (photolist.size() == 0) {
            OkGo.<String>post(HttpManager.addComment)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(PingLunActivity.this))
                    .params("content", etPinglunContent.getText().toString())
                    .params("score", score)
                    .params("rec_id", recid)
                    .execute(new DialogCallback<String>(this) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                            if (bean.getStatus() == 1) {
                                ToastUtils.showToast(PingLunActivity.this, bean.getMsg());
                                finish();
                            } else {
                                ToastUtils.showToast(PingLunActivity.this, bean.getMsg());
                            }
                        }
                    });
        } else {
            List<File> files = new ArrayList<File>();
            for (int i = 0; i < photolist.size(); i++) {
                files.add(new File(photolist.get(i)));
            }
            List<File> bs = new ArrayList<>();
            bs.addAll(files);
            pinglun(bs);
        }

    }

    private void pinglun(List<File> files) {
        OkGo.<String>post(HttpManager.addComment)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(PingLunActivity.this))
                .params("content", etPinglunContent.getText().toString())
                .params("score", score)
                .params("rec_id", recid)
                .addFileParams("img[]", files)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                        if (bean.getStatus() == 1) {
                            ToastUtils.showToast(PingLunActivity.this, bean.getMsg());
                            finish();
                        } else {
                            ToastUtils.showToast(PingLunActivity.this, bean.getMsg());
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                gvImages.setVisibility(View.VISIBLE);
                rlPhoto.setVisibility(View.GONE);
                for (int i = 0; i < photos.size(); i++) {
                    String imgurl = BitmapUtil.compressImage(photos.get(i));
                    photolist.add(imgurl);
                }
                adapter.setPhotos(photolist);
            } else {
                gvImages.setVisibility(View.GONE);
                rlPhoto.setVisibility(View.VISIBLE);
            }
        }
    }


}
