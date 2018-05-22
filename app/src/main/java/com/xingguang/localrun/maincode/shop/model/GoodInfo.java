package com.xingguang.localrun.maincode.shop.model;

/**
 * 创建日期：2018/5/22
 * 描述:购物车商品信息
 * 作者:LiuYu
 */
public class GoodInfo {

    protected String Id;
    protected String name;
    boolean isChoose;
    private double price;
    private String count;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }


}
