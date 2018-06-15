package com.xingguang.localrun.maincode.home.model;

/**
 * 创建日期：2018/6/14
 * 描述:代办详情
 * 作者:LiuYu
 */
public class TaskdetailBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : {"id":"1","title":"代办1","describe":"阿萨斯打扫打扫打扫的","cover":"/Uploads/Picture/2018-05-09/5af289f434fa8.jpg","phone":"17743142373","start_time":"2018-05-31 08:30:00","end_time":"2018-06-02 16:30:00","is_recommend":"0","sort":"50","last_update":"1527666267","create_time":"1525844501"}
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
        /**
         * id : 1
         * title : 代办1
         * describe : 阿萨斯打扫打扫打扫的
         * cover : /Uploads/Picture/2018-05-09/5af289f434fa8.jpg
         * phone : 17743142373
         * start_time : 2018-05-31 08:30:00
         * end_time : 2018-06-02 16:30:00
         * is_recommend : 0
         * sort : 50
         * last_update : 1527666267
         * create_time : 1525844501
         */

        private String id;
        private String title;
        private String describe;
        private String cover;
        private String phone;
        private String start_time;
        private String end_time;
        private String is_recommend;
        private String sort;
        private String last_update;
        private String create_time;

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(String is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
