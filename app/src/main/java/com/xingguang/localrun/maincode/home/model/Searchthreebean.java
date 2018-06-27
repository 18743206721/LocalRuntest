package com.xingguang.localrun.maincode.home.model;

import java.util.List;

/**
 * 创建日期：2018/6/27
 * 描述:搜索代办
 * 作者:LiuYu
 */
public class Searchthreebean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : {"type":3,"keyword":"代办","sort":1,"data":[{"id":"22","title":"代办22","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"21","title":"代办21","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"20","title":"代办20","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"19","title":"代办19","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"18","title":"代办18","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"17","title":"代办17","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"16","title":"代办16","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"15","title":"代办15","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"14","title":"代办14","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"13","title":"代办13","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"}]}
     */

    private int status;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * type : 3
         * keyword : 代办
         * sort : 1
         * data : [{"id":"22","title":"代办22","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"21","title":"代办21","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"20","title":"代办20","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"19","title":"代办19","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"18","title":"代办18","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"17","title":"代办17","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"16","title":"代办16","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"15","title":"代办15","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"14","title":"代办14","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"},{"id":"13","title":"代办13","describe":"阿萨斯打扫打扫打扫的","phone":"17743142373"}]
         */

        private int type;
        private String keyword;
        private int sort;
        private List<DataBean> data;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 22
             * title : 代办22
             * describe : 阿萨斯打扫打扫打扫的
             * phone : 17743142373
             */

            private String id;
            private String title;
            private String describe;
            private String phone;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
