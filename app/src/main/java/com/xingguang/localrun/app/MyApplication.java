package com.xingguang.localrun.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.xingguang.core.base.BaseApplication;

/**
 * Application 基类
 *
 * @author LiuYu
 * @date 2017-4-25
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5b1a2da9f29d9848930000c4","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");

//        PlatformConfig.setWeixin("wx84ca153aa536ac9b", "c33a2d97edd2c5bba9ff0d86b2f0fad5");
        PlatformConfig.setQQZone("1106595089", "KEYpUll27j3djgCR4jY");
//        PlatformConfig.setSinaWeibo("2163751088", "f4ac147246f089774295ab3a5480a765", "http://sns.whalecloud.com");

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
