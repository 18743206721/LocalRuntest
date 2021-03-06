package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.SharedPreferencesUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.RoundImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    @BindView(R.id.tv_setname)
    TextView tvSetname;
    @BindView(R.id.tv_setphone)
    TextView tvSetphone;
    @BindView(R.id.parent)
    LinearLayout parent;
    private TextPopUpWindow pop;
    private View.OnClickListener no;
    private View.OnClickListener yes;

    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private Intent intent;

    public static MineSettingActivity instance;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initView() {
        instance = this;
        getToolbarTitle().setText("设置");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSetname.setText(AppUtil.getUserName(this));
        tvSetphone.setText(AppUtil.getUserPhone(this));
        ImageLoader.loadCircleImage(MineSettingActivity.this, AppUtil.getUserImage(this), imgSetUser);

        initListener();

    }

    private void initListener() {
        no = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        };
        yes = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadbacklogin();
            }
        };

    }

    /**
     * 退出登录
     */
    private void loadbacklogin() {
        OkGo.<String>post(HttpManager.logout)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SharedPreferencesUtils.clear(MineSettingActivity.this);
                        pop.dismiss();
                        ToastUtils.showToast(MineSettingActivity.this, "已退出登录");
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
                intent = new Intent(MineSettingActivity.this, ModificationActivity.class);
                intent.putExtra("huiyuan", "修改会员名字");
                intent.putExtra("type", "1");
                startActivityForResult(intent, 100);
                break;
            case R.id.ll_set_number://修改手机号
                intent = new Intent(MineSettingActivity.this, ModificationActivity.class);
                intent.putExtra("phone", "修改手机号");
                intent.putExtra("type", "2");
                startActivityForResult(intent, 101);
                break;
            case R.id.tv_zhuxiao://注销
                pop = new TextPopUpWindow(this, parent, "确定退出登录?", "取消", "确定", no, yes);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                imgjiancai(data);
            }
        } else if (requestCode == 100 && resultCode == 200) { //名字
            tvSetname.setText(data.getStringExtra("content"));
        } else if (requestCode == 101 && resultCode == 201) {
            tvSetphone.setText(data.getStringExtra("content"));
        } else if (requestCode == 102){
            //裁剪后
            if (data!=null) {
                Bundle bundle = data.getExtras();
                Bitmap face = bundle.getParcelable("data");
                Log.e("caijianhou", "onActivityResult: " + face);

                imgSetUser.setImageBitmap(face);
                try {
                    loadimg(saveBitmap(face),face);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Bitmap headShot = BitmapFactory.decodeStream(getContentResolver().openInputStream(cropImageUri));
            }
        }

    }

    /**
     * 图片剪裁
     */
    private void imgjiancai(Intent data) {
        selectedPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
        Bitmap image = BitmapFactory.decodeFile(selectedPhotos.get(0));
        File faceFile;
        try {
            faceFile = saveBitmap(image);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Uri fileUri = Uri.fromFile(faceFile);
        routeToCrop(fileUri); //跳转到图片裁剪



    }


    private void routeToCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 102);
    }


    private File saveBitmap(Bitmap bitmap) throws IOException {
        File file = new File(getExternalCacheDir(), "face-cache");
        if (!file.exists()) file.createNewFile();
        try (OutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        return file;
    }


    /**
     * 上传图片到服务器
     * @param file
     * @param face
     */
    private void loadimg(File file, final Bitmap face) {
        OkGo.<String>post(HttpManager.update_avatar)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("avatar",
//                        new File(selectedPhotos.get(0))
                        file
                )
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(), CommonBean.class);
                        SharedPreferencesUtils.put(MineSettingActivity.this, SharedPreferencesUtils.USERIMAGE,
//                                selectedPhotos.get(0)
                                HttpManager.INDEX+commonBean.getData()
                        );
                        imgSetUser.setImageBitmap(face);
//                        ImageLoader.loadCircleImage(MineSettingActivity.this, selectedPhotos.get(0), imgSetUser);
                        ToastUtils.showToast(MineSettingActivity.this, commonBean.getMsg());
                    }
                });

    }


}
