package com.xingguang.localrun.main.view;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.igexin.sdk.PushManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.AppManager;
import com.xingguang.core.utils.LogUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.main.model.UpdateBean;
import com.xingguang.localrun.maincode.classify.ClassifFragment;
import com.xingguang.localrun.maincode.home.HomeFragment;
import com.xingguang.localrun.maincode.mine.MineFragment;
import com.xingguang.localrun.maincode.shop.ShopFragment;
import com.xingguang.localrun.push.IntentService;
import com.xingguang.localrun.utils.AppUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_one)
    LinearLayout tabOne;
    @BindView(R.id.tab_two)
    LinearLayout tabTwo;
    @BindView(R.id.tab_four)
    LinearLayout tabFour;
    @BindView(R.id.tab_three)
    LinearLayout tabThree;
    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.tab_three_img)
    ImageView tabThreeImg;
    @BindView(R.id.tab_one_img)
    ImageView tabOneImg;
    @BindView(R.id.tab_four_img)
    ImageView tabFourImg;
    @BindView(R.id.tab_two_txt)
    TextView tabTwoTxt;
    @BindView(R.id.tab_three_txt)
    TextView tabThreeTxt;
    @BindView(R.id.tab_four_txt)
    TextView tabFourTxt;
    @BindView(R.id.tab_one_txt)
    TextView tabOneTxt;
    @BindView(R.id.tab_two_img)
    ImageView tabTwoImg;
    //首页
    private HomeFragment homeFragment;
    //分类
    private ClassifFragment classifFragment;
    //购物车
    private ShopFragment shopFragment;
    //我的
    private MineFragment myFragment;
    // 用来判断 两次返回键退出app
    private boolean isExit = false;
    private FragmentManager fm;
    public static MainActivity instance;
    private int typeid;

    private final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1003;
    private PackageInfo packageInfo;
    private DownloadManager downloadManager;
    private long mTaskId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        AppUtil.translucentStatusBar(MainActivity.this);//设置沉浸式
        instance = this;
        fm = getSupportFragmentManager();
        setToNewsFragment();
        setThemeColor(tabOneImg,R.drawable.home_icon);
        tabOneTxt.setTextColor(getResources().getColor(R.color.text_color_red));

        //推送
        PushManager.getInstance().initialize(this.getApplicationContext(), com.xingguang.localrun.push.PushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), IntentService.class);

        typeid = getIntent().getIntExtra("typeid",0);
        if (typeid == 1){
            MainActivity.instance.setBg(1);
            MainActivity.instance.setToNewsFragment();
        }else if (typeid == 2){
            MainActivity.instance.setBg(4);
            MainActivity.instance.setToInvestmentFragment();
        }

        checkAppVersion();

    }

    /**
     * 自动检测app的版本
     * 直接检测是否有新的版本，然后进行更新
     */
    private void checkAppVersion() {
        if (Build.VERSION.SDK_INT >= 21) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
            } else {
                loadcheck();
            }
        } else {
            loadcheck();
        }

    }
    /**
     * 检查更新版本
     */
    private void loadcheck() {
        OkGo.<String>post(HttpManager.UPdata)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        UpdateBean bean = gson.fromJson(response.body().toString(), UpdateBean.class);
                        try {
                            if (bean.getData()!=null) {
                                if (bean.getData().getVersion_num()!=null) {
                                    packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

                                    if (AppUtil.compareVersion(AppUtil.getVersionName(MainActivity.this),
                                            bean.getData().getVersion_num()) == -1) {
                                        showDialog(bean);
                                    }
                                }
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void showDialog(final UpdateBean bean) {
        final Dialog dialog = new Dialog(this, R.style.update_dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_updata, null);

        dialog.setContentView(view);
        dialog.setCancelable(true);//设置点击屏幕消失
        Window window = dialog.getWindow();

//        dialog.getWindow().setWindowAnimations(R.style.AnimBottom);
        //最重要的一句话，一定要加上！要不然怎么设置都不行！
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setGravity(Gravity.CENTER);
        //设置dialog能适配各个手机屏幕
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        //获取屏幕宽
        wlp.width = (int) (d.getWidth());
        //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度自适应
        window.setAttributes(wlp);


        TextView mTvCanel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView mTvUpdate = (TextView) view.findViewById(R.id.tv_update);
        TextView tv_info = (TextView) view.findViewById(R.id.tv_info);
        TextView tv_version = (TextView) view.findViewById(R.id.tv_version);

        tv_version.setText("版本:" + bean.getData().getVersion_num());
        tv_info.setText(bean.getData().getVersion_describe().replace(",", "\n"));


        mTvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(MainActivity.this, "正在下载中,请稍后...");
                dialog.dismiss();
                downloadAPK(bean.getData().getVersion_file(), packageInfo.versionName);
            }
        });

        mTvCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 使用系统下载器下载
     */
    public void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        //漫游网络是否可以下载
        request.setAllowedOverRoaming(false);

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir("/download/", versionName);
//        request.setDestinationInExternalPublicDir(getExternalCacheDir().getAbsolutePath(), "download"); 解析包错问题（未下载）
//        request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);
        //注册广播接收者，监听下载状态
        MainActivity.this.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }


    /**
     * 广播接受者，接收下载状态
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };

    /**
     * 检查下载状态
     */
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);
        //筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    LogUtils.i(">>>下载暂停");
                    break;
                case DownloadManager.STATUS_PENDING:
                    LogUtils.i(">>>下载延迟");
                    break;
                case DownloadManager.STATUS_RUNNING:
                    LogUtils.i(">>>正在下载");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    LogUtils.i(">>>下载完成");
                    //下载完成安装APK
                    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                            + File.separator + AppUtil.getVersionName(MainActivity.this);
                    installAPK(new File(downloadPath));
                    break;
                case DownloadManager.STATUS_FAILED:
                    LogUtils.i(">>>下载失败");
                    break;
                default:
                    break;
            }
        }
    }


    protected void installAPK(File file) {
        Log.i("大小", "installAPK: " + file.length());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (file != null) {
                Uri contentUri = FileProvider.getUriForFile(this, this.getPackageName() + ".provider", file);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                this.startActivity(intent);
            }
        } else {
            Uri downloadFileUri;
            if (file != null) {
                String path = file.getAbsolutePath();
                downloadFileUri = Uri.parse("file://" + path);
                intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    loadcheck();

                } else {
                    // Permission Denied
                    ToastUtils.showToast(MainActivity.this, "请到设置中开放对本引用的权限");
                }
            } else {
//                MobclickAgent.reportError(MainActivity.this, "grantResults.length:<0");
            }

        }

    }
    /**
     * 沉浸式状态栏
     */
//    private void initState() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//    }


    @OnClick({R.id.tab_one, R.id.tab_two, R.id.tab_three, R.id.tab_four})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tab_one:// 首页
                setBg(1);
                setToNewsFragment();
                break;
            case R.id.tab_two:// 分类
                setBg(2);
                setToProjectFragment();
                break;
            case R.id.tab_three:// 购物车
                if (AppUtil.isExamined(MainActivity.this)) {
                    setBg(3);
                    setToActivityFragment();
                }
                break;
            case R.id.tab_four:// 我的
                if (AppUtil.isExamined(MainActivity.this)) {
                    setBg(4);
                    setToInvestmentFragment();
                }
                break;
            default:
                break;
        }

    }

    public void setBg(int id) {
        switch (id) {
            case 1: // 首页
                setAllToGrey();
                setThemeColor(tabOneImg,R.drawable.home_icon);
                tabOneTxt.setTextColor(getResources().getColor(R.color.text_color_red));
                break;
            case 2: // 分类
                setAllToGrey();
                setThemeColor(tabTwoImg,R.drawable.classif_icon);
                tabTwoTxt.setTextColor(getResources().getColor(R.color.text_color_red));
                break;
            case 3: // 购物车
                setAllToGrey();
                setThemeColor(tabThreeImg,R.drawable.shop_icon);
                tabThreeTxt.setTextColor(getResources().getColor(R.color.text_color_red));
                break;
            case 4: // 我的
                setAllToGrey();
                setThemeColor(tabFourImg,R.drawable.mine_icon);
                tabFourTxt.setTextColor(getResources().getColor(R.color.text_color_red));
                break;
            default:
                break;
        }
    }

    private void setThemeColor(ImageView mImage, int icon) {
        //利用ContextCompat工具类获取drawable图片资源
        Drawable drawable = ContextCompat.getDrawable(this, icon);
        //简单的使用tint改变drawable颜色
        Drawable drawable1 = AppUtil.tintDrawable(drawable,ContextCompat.getColor(this, R.color.home_read));
        mImage.setImageDrawable(drawable1);
    }


    /**
     * 重置
     */
    private void setAllToGrey() {
        tabOneImg.setImageResource(R.drawable.home_icon);
        tabTwoImg.setImageResource(R.drawable.classif_icon);
        tabThreeImg.setImageResource(R.drawable.shop_icon);
        tabFourImg.setImageResource(R.drawable.mine_icon);

        tabOneTxt.setTextColor(getResources().getColor(R.color.textDarkGray));
        tabTwoTxt.setTextColor(getResources().getColor(R.color.textDarkGray));
        tabThreeTxt.setTextColor(getResources().getColor(R.color.textDarkGray));
        tabFourTxt.setTextColor(getResources().getColor(R.color.textDarkGray));

    }

    /**
     * 设置当前的Fragment 为首页
     */
    public void setToNewsFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        hideAll(transaction);
        if (homeFragment != null) {
            transaction.show(homeFragment);

        } else {
            homeFragment = new HomeFragment();
            transaction.add(R.id.main_frame, homeFragment, "homeFragment");
        }
        transaction.commit();
    }

    /**
     * 设置当前的Fragment 为分类
     */
    public void setToProjectFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        hideAll(transaction);
        if (classifFragment != null) {
            transaction.show(classifFragment);
        } else {
            classifFragment = new ClassifFragment();
            transaction.add(R.id.main_frame, classifFragment, "classifFragment");
        }
        transaction.commit();
    }

    /**
     * 设置当前的Fragment 为购物车
     */

    private void setToActivityFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        hideAll(transaction);
        if (shopFragment != null) {
            transaction.show(shopFragment);
        } else {
            shopFragment = new ShopFragment();
            transaction.add(R.id.main_frame, shopFragment, "shopFragment");
        }
        transaction.commit();
    }

    /**
     * 设置当前的Fragment 为我的
     */
    public void setToInvestmentFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        hideAll(transaction);
        if (myFragment != null) {
            transaction.show(myFragment);
        } else {
            myFragment = new MineFragment();
            transaction.add(R.id.main_frame, myFragment, "mineFragment");
        }
        transaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (classifFragment != null) {
            transaction.hide(classifFragment);
        }
        if (shopFragment != null) {
            transaction.hide(shopFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    /**
     * 按俩次back键退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                ToastUtils.showToast(this, "再按一次退出程序");
                Observable.timer(2000, TimeUnit.MILLISECONDS)
                       .subscribe(new Observer<Long>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(Long aLong) {
                               isExit = false;
                           }

                           @Override
                           public void onError(Throwable e) {

                           }

                           @Override
                           public void onComplete() {

                           }
                       });
                return false;
            }
            AppManager.AppExit(this);
        }
        return super.onKeyDown(keyCode, event);
    }




}
