package com.xingguang.localrun.main.view;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.AppManager;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.classify.ClassifFragment;
import com.xingguang.localrun.maincode.home.HomeFragment;
import com.xingguang.localrun.maincode.mine.MineFragment;
import com.xingguang.localrun.maincode.shop.ShopFragment;
import com.xingguang.localrun.push.IntentService;
import com.xingguang.localrun.utils.AppUtil;

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

//        initState();

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
