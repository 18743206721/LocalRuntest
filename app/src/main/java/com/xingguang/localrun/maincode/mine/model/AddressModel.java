package com.xingguang.localrun.maincode.mine.model;


import java.io.Serializable;

public class AddressModel implements Serializable {

    /**
     * id : db161e86d8b44ddbb5f67f3701cde32a
     * prov : 安徽省
     * city : 安庆市
     * dist : 枞阳县
     * address : 路路通
     * defAddress : 0
     * name : 路路通
     * phone : 55555
     */

    private String id;
    private String prov;
    private String city;
    private String dist;
    private String address;
    private String defAddress;
    private String name;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDefAddress() {
        return defAddress;
    }

    public void setDefAddress(String defAddress) {
        this.defAddress = defAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
