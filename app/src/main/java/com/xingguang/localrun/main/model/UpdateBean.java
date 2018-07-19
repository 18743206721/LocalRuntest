package com.xingguang.localrun.main.model;

/**
 * 创建日期：2018/7/19
 * 描述:更新bean
 * 作者:LiuYu
 */
public class UpdateBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : {"id":"2","version_num":"1.0.1","version_file":"http://140.143.248.102/Uploads/file/2018-07-19/5b4ffe4a33081.apk","version_describe":"1、解决崩溃\r\n2、解决发热严重\r\n3、解决卡","add_time":"1531969997"}
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
         * id : 2
         * version_num : 1.0.1
         * version_file : http://140.143.248.102/Uploads/file/2018-07-19/5b4ffe4a33081.apk
         * version_describe : 1、解决崩溃
         2、解决发热严重
         3、解决卡
         * add_time : 1531969997
         */

        private String id;
        private String version_num;
        private String version_file;
        private String version_describe;
        private String add_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion_num() {
            return version_num;
        }

        public void setVersion_num(String version_num) {
            this.version_num = version_num;
        }

        public String getVersion_file() {
            return version_file;
        }

        public void setVersion_file(String version_file) {
            this.version_file = version_file;
        }

        public String getVersion_describe() {
            return version_describe;
        }

        public void setVersion_describe(String version_describe) {
            this.version_describe = version_describe;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}

