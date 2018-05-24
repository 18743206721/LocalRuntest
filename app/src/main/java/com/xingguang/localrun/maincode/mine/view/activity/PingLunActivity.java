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

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.mine.view.adapter.PinglunPhotosAdapter;
import com.xingguang.localrun.utils.ToastUtils;

import java.util.ArrayList;

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
    private ArrayList<String> photos = new ArrayList<>();
    PinglunPhotosAdapter adapter;


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

        initAdapter();
        initListener();

    }


    private void initAdapter() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        gvImages.setLayoutManager(manager);
        adapter = new PinglunPhotosAdapter(photos,PingLunActivity.this);
        gvImages.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initListener() {
        adapter.setAddPhotosListener(new PinglunPhotosAdapter.AddPhotosListener() {
            @Override
            public void addPhotos() {
                photos.clear();
                PhotoPicker.builder()
                        //设置选择个数
                        .setPhotoCount(5)
                        //选择界面第一个显示拍照按钮
                        .setShowCamera(true)
                        //选择时点击图片放大浏览
//                        .setPreviewEnabled(true)
                        //附带已经选中过的图片
//                        .setSelected(photos)
                        .start(PingLunActivity.this);
            }
        });


    }

    @OnClick({R.id.rl_photo, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_photo: //选择图片
                PhotoPicker.builder()
                        //设置选择个数
                        .setPhotoCount(5)
                        //选择界面第一个显示拍照按钮
                        .setShowCamera(true)
                        //选择时点击图片放大浏览
                        .setPreviewEnabled(true)
                        .start(PingLunActivity.this);
                break;
            case R.id.tv_commit: //提交
                load();
                break;
        }
    }



    private void load() {
        ToastUtils.showToast(PingLunActivity.this, "提交成功");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                gvImages.setVisibility(View.VISIBLE);
                rlPhoto.setVisibility(View.GONE);
                adapter.setPhotos(photos);
            } else {
                gvImages.setVisibility(View.GONE);
                rlPhoto.setVisibility(View.VISIBLE);
            }
        }
    }



}
