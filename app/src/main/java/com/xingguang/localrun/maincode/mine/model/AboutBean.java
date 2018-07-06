package com.xingguang.localrun.maincode.mine.model;

/**
 * 创建日期：2018/7/6
 * 描述:
 * 作者:LiuYu
 */
public class AboutBean {


    /**
     * status : 1
     * msg : SUCCESS
     * data : {"id":"1","title":"关于同城快跑","describe":"关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑","content":"<span style=\"font-size:14px;\">&nbsp; &nbsp; &nbsp; &nbsp;关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑<\/span>","cover":null,"sort":"50","click":"4","create_time":"2018-07-06","update_time":"2018-07-06 09:43:56"}
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
         * title : 关于同城快跑
         * describe : 关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑
         * content : <span style="font-size:14px;">&nbsp; &nbsp; &nbsp; &nbsp;关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑关于同城快跑</span>
         * cover : null
         * sort : 50
         * click : 4
         * create_time : 2018-07-06
         * update_time : 2018-07-06 09:43:56
         */

        private String id;
        private String title;
        private String describe;
        private String content;
        private Object cover;
        private String sort;
        private String click;
        private String create_time;
        private String update_time;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getCover() {
            return cover;
        }

        public void setCover(Object cover) {
            this.cover = cover;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getClick() {
            return click;
        }

        public void setClick(String click) {
            this.click = click;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
