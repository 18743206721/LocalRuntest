package com.xingguang.localrun.login.model;

/**
 * 创建日期：2018/6/12
 * 描述:
 * 作者:LiuYu
 */
public class RegisterBean {


    /**
     * status : 0
     * msg : 手机号已存在
     */

    private int status;
    private String msg;

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
