package com.xingguang.core.base;

import android.app.Application;
import android.content.Context;

/**
 * Application 基类
 *
 * @author LiuYu
 * @date 2016-11-25 10:23:13
 */

public class BaseApplication extends Application {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();

    }
}
