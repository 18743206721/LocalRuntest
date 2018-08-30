package com.xingguang.localrun.http;

/**
 * 创建日期：2018/5/22
 * 描述:
 * 作者:LiuYu
 */
public class HttpManager {

    //线上
    public static final String BASE_URL = "http://140.143.248.102/index.php/";
    //本地测试
//    public static final String BASE_URL = "http://192.168.0.230/index.php/";

    public static final String WX_APP_ID = "wxbe852bea37d724f9"; //微信支付的appid



    //登录接口
    public static final String Login= BASE_URL+"Public/login";

    //发送验证码
    public static final String sendSms= BASE_URL+"Public/sendSms";

    //注册
    public static final String register= BASE_URL+"Public/register";

    //退出
    public static final String logout= BASE_URL+"Public/logout";

    //申请入驻
    public static final String apply= BASE_URL+"User/apply";

    //申请入驻类别,获取店铺分类
    public static final String applyindex = BASE_URL+"Goods/index";

    //修改头像
    public static final String update_avatar= BASE_URL+"User/update_avatar";

    //修改昵称
    public static final String update_nickname= BASE_URL+"User/update_nickname";

    //修改手机号
    public static final String update_mobile= BASE_URL+"User/update_mobile";

    //首页轮播图
    public static final String Index= BASE_URL+"Index/index";

    //忘记密码
    public static final String forgotPassword= BASE_URL+"Public/forgotPassword";

    //推荐商品
    public static final String tjgoods= BASE_URL+"Index/tjgoods";

    //推荐代办
    public static final String tjtask= BASE_URL+"Index/tjtask";

    //代办列表
    public static final String Taskindex= BASE_URL+"Task/index";

    //代办详情
    public static final String Taskdetail= BASE_URL+"Task/detail";

    //添加地址
    public static final String Addadres= BASE_URL+"User/addAddress";

    //收货地址列表
    public static final String ListAddress= BASE_URL+"User/address";

    //收货地址详情
    public static final String UserADSdetail= BASE_URL+"User/address_detail";

    //删除收货地址
    public static final String deletedads= BASE_URL+"User/delAddress";

    //设置默认收货地址
    public static final String usersetAds= BASE_URL+"User/setAddress";

    //编辑收货地址
    public static final String editAddress= BASE_URL+"User/editAddress";

    //关注店铺列表
    public static final String collectShop= BASE_URL+"User/collectShop";

    //取消关注店铺
    public static final String cancelCollectShop= BASE_URL+"User/cancelCollectShop";

    //关注店铺
    public static final String Shopcollect= BASE_URL+"Shop/collect";

    //足迹列表
    public static final String visit= BASE_URL+"User/visit";

    //删除足迹
    public static final String delVisit= BASE_URL+"User/delVisit";

    //店铺首页——轮播图
    public static final String Shopbanner= BASE_URL+"Shop/banner";

    //店铺首页——推荐新品
    public static final String Shopindex= BASE_URL+"Shop/index";

    //店铺首页——店铺简介
    public static final String Shopjianjie= BASE_URL+"Shop/detail";

    //店铺全部宝贝
    public static final String Shopgoods= BASE_URL+"Shop/goods";

    //店铺新品
    public static final String shopnews= BASE_URL+"Shop/newGoods";

    //收藏商品列表
    public static final String collectGoods= BASE_URL+"User/collectGoods";

    //取消收藏
    public static final String delCollectGoods= BASE_URL+"User/delCollectGoods";

    //商品详情
    public static final String GoodsDetail= BASE_URL+"Goods/detail";

    //购物车列表
    public static final String Cartcart2 = BASE_URL+"Cart/cart2";

    //修改购物车商品
    public static final String updateCart = BASE_URL+"Cart/updateCart";

    //收藏商品
    public static final String collect = BASE_URL+"Goods/collect";

    //商品规格
    public static final String spec = BASE_URL+"Goods/spec";

    //加入购物车
    public static final String addCart = BASE_URL+"Cart/addCart";

    //根据分类ID获取店铺列表
    public static final String classifshop = BASE_URL+"Goods/shop";

    //搜索
    public static final String search = BASE_URL+"Index/search";

    //购物车结算
    public static final String Cart = BASE_URL+"Cart/cart3";

    //删除购物车
    public static final String Cartdelete = BASE_URL+"Cart/delete";

    //单选购物车接口
    public static final String CartselectCart = BASE_URL+"Cart/selectCart";

    //修改购物车商品规格
    public static final String CartupdateCart = BASE_URL+"Cart/updateCart";

    //提交订单
    public static final String cart4 = BASE_URL+"Cart/cart4";

    //订单列表
    public static final String Userorder = BASE_URL+"User/order";

    //删除订单
    public static final String delOrder = BASE_URL+"Order/delOrder";

    //取消订单
    public static final String cancelOrder = BASE_URL+"Order/cancelOrder";

    //确认收货
    public static final String trueOrder = BASE_URL+"User/confirmOrder";

    //订单支付
    public static final String topay = BASE_URL+"User/topay";

    //添加商品评价
    public static final String addComment = BASE_URL+"Goods/addComment";

    //商品评价
    public static final String Goodscomment = BASE_URL+"Goods/comment";

    //精选/低价
    public static final String Goodsspecial = BASE_URL+"Goods/special";

    //三方登录
    public static final String Publicthird = BASE_URL+"Public/third";

    //绑定手机号
    public static final String bindMobile = BASE_URL+"Public/bindMobile";

    //文章
    public static final String Indexarticle = BASE_URL+"Index/article";

    //收藏，关注，足迹数量
    public static final String profile = BASE_URL+"User/profile";

    //index.php
    public static final String INDEX = "http://140.143.248.102/";

    public static final String UPdata = BASE_URL+"Index/version";


}
