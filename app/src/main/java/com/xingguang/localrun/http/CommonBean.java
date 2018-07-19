package com.xingguang.localrun.http;

/**
 * 创建日期：2018/6/13
 * 描述:
 * 作者:LiuYu
 */
public class CommonBean {

    /**
     * status : 0
     * msg : 您已申请过店铺入驻
     */

    private int status;
    private String msg;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
