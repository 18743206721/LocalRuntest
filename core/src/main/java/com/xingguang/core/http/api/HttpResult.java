package com.xingguang.core.http.api;

/**
 * 请求结果统一封装类,可以根据后台返回的数据自定义字段类型
 *
 * @author LiuYu
 * @date 2016-12-5
 */
public class HttpResult<T> {

    // 判断标示
    private String state;
    // 提示信息
    private String msg;
    // 显示数据（用户需要关心的数据）
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
