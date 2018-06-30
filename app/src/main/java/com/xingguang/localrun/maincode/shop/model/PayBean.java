package com.xingguang.localrun.maincode.shop.model;

/**
 * 创建日期：2018/6/30
 * 描述:支付bean
 * 作者:LiuYu
 */
public class PayBean {


    /**
     * status : 1
     * data : {"param":"alipay_sdk=alipay-sdk-php-20161101&app_id=2018062660487008&biz_content=%7B%22body%22%3A%22%E5%90%8C%E5%9F%8E%E5%BF%AB%E8%B7%91APP%E5%95%86%E5%9F%8E%22%2C%22subject%22%3A+%22%E5%90%8C%E5%9F%8E%E5%BF%AB%E8%B7%91APP%E5%95%86%E5%9F%8E%E4%BA%A7%E5%93%81%22%2C%22out_trade_no%22%3A+%22201806301234109107%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%2278.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22passback_params%22%3A%22parent_sn%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F140.143.248.102%2Findex.php%2FOrder%2FnotifyUrl%2Fpay_code%2Falipay&sign_type=RSA2&timestamp=2018-06-30+12%3A34%3A10&version=1.0&sign=Su3uwCjuWtGILjcJ1KGNlibHJSDFhXDmfC93cJJF0ZQMmR%2F4RWKitUncj16Du1N7qBIDw2TFpKfATUMU%2BCR18W5X5nw0XLRa6G16%2FRPHMBx8796ZJbWL3%2FqrIfAeLNDUN2UhUlG9122y2i0sEVaMjaB5jhhVQE7tKrVi6iOGvWkt1sGRqcqijUF%2FuOCfV5JTOVPUAefTzDEiOnuPsd8Kbh%2B527Q9QjC1flPpm%2BRPvQ7vJNtStFemUybsYI5plQ37KJwhub51FUQyP%2FeQBrivQLmZyjsNM9EThqSNnRvm6ULWEzb4fGh3xspdjj4E1qjFL3S13htkbQnq77ZeZw2p0w%3D%3D","order_amount":"78.00","order_sn":"201806301234109107","sn_type":"parent_sn","pay_code":"alipay"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * param : alipay_sdk=alipay-sdk-php-20161101&app_id=2018062660487008&biz_content=%7B%22body%22%3A%22%E5%90%8C%E5%9F%8E%E5%BF%AB%E8%B7%91APP%E5%95%86%E5%9F%8E%22%2C%22subject%22%3A+%22%E5%90%8C%E5%9F%8E%E5%BF%AB%E8%B7%91APP%E5%95%86%E5%9F%8E%E4%BA%A7%E5%93%81%22%2C%22out_trade_no%22%3A+%22201806301234109107%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%2278.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22passback_params%22%3A%22parent_sn%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F140.143.248.102%2Findex.php%2FOrder%2FnotifyUrl%2Fpay_code%2Falipay&sign_type=RSA2&timestamp=2018-06-30+12%3A34%3A10&version=1.0&sign=Su3uwCjuWtGILjcJ1KGNlibHJSDFhXDmfC93cJJF0ZQMmR%2F4RWKitUncj16Du1N7qBIDw2TFpKfATUMU%2BCR18W5X5nw0XLRa6G16%2FRPHMBx8796ZJbWL3%2FqrIfAeLNDUN2UhUlG9122y2i0sEVaMjaB5jhhVQE7tKrVi6iOGvWkt1sGRqcqijUF%2FuOCfV5JTOVPUAefTzDEiOnuPsd8Kbh%2B527Q9QjC1flPpm%2BRPvQ7vJNtStFemUybsYI5plQ37KJwhub51FUQyP%2FeQBrivQLmZyjsNM9EThqSNnRvm6ULWEzb4fGh3xspdjj4E1qjFL3S13htkbQnq77ZeZw2p0w%3D%3D
         * order_amount : 78.00
         * order_sn : 201806301234109107
         * sn_type : parent_sn
         * pay_code : alipay
         */

        private String param;
        private String order_amount;
        private String order_sn;
        private String sn_type;
        private String pay_code;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getSn_type() {
            return sn_type;
        }

        public void setSn_type(String sn_type) {
            this.sn_type = sn_type;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }
    }
}
