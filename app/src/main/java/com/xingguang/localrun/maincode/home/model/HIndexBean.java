package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/11
 * 描述:首页轮播图
 * 作者:LiuYu
 */
public class HIndexBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : {"banner1":[{"id":"3","type":"1","title":"轮播3","image":"/Uploads/Picture/2018-05-30/5b0e5de3a4cb8.png","data_type":"1","data_id":"1","sort":"50","create_time":"2018-05-11"},{"id":"2","type":"1","title":"轮播2","image":"/Uploads/Picture/2018-05-30/5b0e5de3a4cb8.png","data_type":"2","data_id":"1","sort":"50","create_time":"2018-05-11"},{"id":"1","type":"1","title":"轮播1","image":"/Uploads/Picture/2018-05-30/5b0e5de3a4cb8.png","data_type":"2","data_id":"2","sort":"50","create_time":"2018-05-11"}],"banner2":[{"id":"4","type":"2","title":"背景1","image":"/Uploads/Picture/2018-05-30/5b0e671381e20.png","data_type":"0","data_id":"1","sort":"50","create_time":"2018-05-15"},{"id":"5","type":"2","title":"背景2","image":"/Uploads/Picture/2018-05-30/5b0e67206c278.png","data_type":"0","data_id":"2","sort":"50","create_time":"2018-05-15"}]}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<Banner1Bean> banner1;
        private List<Banner2Bean> banner2;

        public List<Banner1Bean> getBanner1() {
            return banner1;
        }

        public void setBanner1(List<Banner1Bean> banner1) {
            this.banner1 = banner1;
        }

        public List<Banner2Bean> getBanner2() {
            return banner2;
        }

        public void setBanner2(List<Banner2Bean> banner2) {
            this.banner2 = banner2;
        }

        public static class Banner1Bean {
            /**
             * id : 3
             * type : 1
             * title : 轮播3
             * image : /Uploads/Picture/2018-05-30/5b0e5de3a4cb8.png
             * data_type : 1
             * data_id : 1
             * sort : 50
             * create_time : 2018-05-11
             */

            private String id;
            private String type;
            private String title;
            private String image;
            private String data_type;
            private String data_id;
            private String sort;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getData_type() {
                return data_type;
            }

            public void setData_type(String data_type) {
                this.data_type = data_type;
            }

            public String getData_id() {
                return data_id;
            }

            public void setData_id(String data_id) {
                this.data_id = data_id;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }

        public static class Banner2Bean {
            /**
             * id : 4
             * type : 2
             * title : 背景1
             * image : /Uploads/Picture/2018-05-30/5b0e671381e20.png
             * data_type : 0
             * data_id : 1
             * sort : 50
             * create_time : 2018-05-15
             */

            private String id;
            private String type;
            private String title;
            private String image;
            private String data_type;
            private String data_id;
            private String sort;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getData_type() {
                return data_type;
            }

            public void setData_type(String data_type) {
                this.data_type = data_type;
            }

            public String getData_id() {
                return data_id;
            }

            public void setData_id(String data_id) {
                this.data_id = data_id;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
