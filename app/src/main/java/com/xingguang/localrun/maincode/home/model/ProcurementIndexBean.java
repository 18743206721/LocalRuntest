package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/7
 * 描述:
 * 作者:LiuYu
 */
public class ProcurementIndexBean {


    private List<ImgListBean> imgList;
    private List<CategoryListBean> categoryList;

    public List<ImgListBean> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgListBean> imgList) {
        this.imgList = imgList;
    }

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public static class ImgListBean {
        /**
         * img : http://192.168.51.73:8080/userfiles/1/images/qingbang/2017/11/timg(1).jpg
         * procurementID : 2
         */

        private String img;
        private String procurementID;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getProcurementID() {
            return procurementID;
        }

        public void setProcurementID(String procurementID) {
            this.procurementID = procurementID;
        }
    }

    public static class CategoryListBean {
        /**
         * categoryName : 全部
         * categoryID : 0
         */

        private String categoryName;
        private String categoryID;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(String categoryID) {
            this.categoryID = categoryID;
        }
    }

}
