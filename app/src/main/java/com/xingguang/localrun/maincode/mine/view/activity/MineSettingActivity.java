package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.RoundImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;


/**
 * 创建日期：2018/5/21
 * 描述:设置界面
 * 作者:LiuYu
 */
public class MineSettingActivity extends ToolBarActivity {

    @BindView(R.id.img_set_user)
    RoundImageView imgSetUser;
    @BindView(R.id.rl_set_img)
    RelativeLayout rlSetImg;
    @BindView(R.id.ll_huiyuan)
    LinearLayout llHuiyuan;
    @BindView(R.id.ll_set_number)
    LinearLayout llSetNumber;
    @BindView(R.id.tv_zhuxiao)
    TextView tvZhuxiao;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private String img = ""; //图片url


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initView() {
        getToolbarTitle().setText("设置");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick({R.id.rl_set_img, R.id.ll_huiyuan, R.id.ll_set_number, R.id.tv_zhuxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_set_img: //修改头像
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(this, PhotoPicker.REQUEST_CODE);
                break;
            case R.id.ll_huiyuan://修改会员名字
                startActivity(new Intent(MineSettingActivity.this, ModificationActivity.class)
                        .putExtra("huiyuan", "修改会员名字").putExtra("type", "1"));
                break;
            case R.id.ll_set_number://修改手机号
                startActivity(new Intent(MineSettingActivity.this, ModificationActivity.class)
                        .putExtra("phone", "修改手机号").putExtra("type", "2"));
                break;
            case R.id.tv_zhuxiao://注销
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE){
            if (data != null) {
                selectedPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                if (selectedPhotos.size() != 0) {
                    img = selectedPhotos.get(0);
                }
                ImageLoader.loadCircleImage(MineSettingActivity.this, selectedPhotos.get(0), imgSetUser);

            }

        }
    }




}
