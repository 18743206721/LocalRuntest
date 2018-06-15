package com.xingguang.localrun.maincode.mine.model;

import java.util.List;

/**
 * 创建日期：2018/6/13
 * 描述:
 * 作者:LiuYu
 */
public class MineApplyBean {

    /**
     * status : 1
     * msg : SUCCESS
     * data : [{"id":"5","pid":"0","name":"超市","describe":"超市","sort":"50"},{"id":"4","pid":"0","name":"果蔬","describe":"果蔬","sort":"50"},{"id":"3","pid":"0","name":"美装","describe":"美装","sort":"50"},{"id":"2","pid":"0","name":"五金","describe":"五金","sort":"50"},{"id":"1","pid":"0","name":"美食","describe":"美食","sort":"50"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * pid : 0
         * name : 超市
         * describe : 超市
         * sort : 50
         */

        private String id;
        private String pid;
        private String name;
        private String describe;
        private String sort;
        private  boolean isChoose;

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
