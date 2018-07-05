package com.xingguang.localrun.http;

/**
 * 创建日期：2018/7/5
 * 描述: 分享全局变量
 * 作者:LiuYu
 */
public class MyShare {

    private static String title;

    private static String logo;

    private static String download;

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        MyShare.title = title;
    }

    public static String getLogo() {
        return logo;
    }

    public static void setLogo(String logo) {
        MyShare.logo = logo;
    }

    public static String getDownload() {
        return download;
    }

    public static void setDownload(String download) {
        MyShare.download = download;
    }


    



}
