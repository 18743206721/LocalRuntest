package com.xingguang.localrun.maincode.home;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.HttpFragment;
import com.xingguang.core.utils.LogUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.ClassType;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.http.MyShare;
import com.xingguang.localrun.main.view.MainActivity;
import com.xingguang.localrun.maincode.home.model.GlideImageLoader;
import com.xingguang.localrun.maincode.home.model.HIndexBean;
import com.xingguang.localrun.maincode.home.model.TjgoodsBean;
import com.xingguang.localrun.maincode.home.model.TjtaskBean;
import com.xingguang.localrun.maincode.home.view.activity.ClassifShopActivity;
import com.xingguang.localrun.maincode.home.view.activity.DaiBanDetailsActivity;
import com.xingguang.localrun.maincode.home.view.activity.DaiBanMoreActivity;
import com.xingguang.localrun.maincode.home.view.activity.HomeSearchActivity;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.maincode.home.view.adapter.HomeDaiBanAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.HomeDaiGouAdapter;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends HttpFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_main_fenlei)
    LinearLayout llMainFenlei;
    @BindView(R.id.ll_main_selecte)
    LinearLayout llMainSelecte;
    @BindView(R.id.ll_home_dijia)
    LinearLayout llHomeDijia;
    @BindView(R.id.ll_home_food)
    LinearLayout llHomeFood;
    @BindView(R.id.ll_outfit)
    LinearLayout llOutfit;
    @BindView(R.id.ll_supermarket)
    LinearLayout llSupermarket;
    @BindView(R.id.ll_home_fruit)
    LinearLayout llHomeFruit;
    @BindView(R.id.ll_home_hardware)
    LinearLayout llHomeHardware;
    @BindView(R.id.main_home_lv)
    RecyclerView mainHomeLv;
    @BindView(R.id.main_homelv_daiban)
    RecyclerView mainHomelvDaiban;
    @BindView(R.id.ll_sousuo_serch)
    LinearLayout llsousuo;
    @BindView(R.id.ll_daigou_more)
    LinearLayout ll_daigou_more;
    @BindView(R.id.ll_daiban_more)
    LinearLayout ll_daiban_more;
    @BindView(R.id.iv_img1)
    ImageView iv_img1;
    @BindView(R.id.iv_img2)
    ImageView iv_img2;
    @BindView(R.id.iv_img3)
    ImageView iv_img3;
    @BindView(R.id.iv_img4)
    ImageView iv_img4;
    @BindView(R.id.ll_daigoupage)
    LinearLayout ll_daigoupage;
    @BindView(R.id.ll_daibanpage)
    LinearLayout ll_daibanpage;
    private HomeDaiGouAdapter daigouadapter;
    private HomeDaiBanAdapter daibanadapter;
    private List<String> networkImages = new ArrayList<>();
    private Intent intent;
    private FragmentManager fm;
    private int page = 1; //代购分页
    private int count = 1; //代办分页

    //轮播图
    private List<HIndexBean.DataBean.Banner1Bean> bannerList = new ArrayList<>();
    //banner2
    private List<HIndexBean.DataBean.Banner2Bean> banner2BeanList = new ArrayList<>();
    //代购数据
    private List<TjgoodsBean.DataBean> daigoulist = new ArrayList<>();
    //代办数据
    private List<TjtaskBean.DataBean> daibanlist = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        fm = getFragmentManager();

        initAdapter();
        loadHeader();
        initDaiGou(1);
        initDaiBan(1);
        initListener();

    }

    /**
     * 代办接口
     */
    private void initDaiBan(final int count) {
        OkGo.<String>post(HttpManager.tjtask)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("page", count)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        TjtaskBean tjtaskBean = gson.fromJson(response.body().toString(), TjtaskBean.class);

                        if (tjtaskBean.getData().size() == 0 && count != 1) {
                            Toast.makeText(getActivity(),
                                    "只有这么多了~",
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (count == 1) {
                            daibanlist.clear();
                        }

                        daibanlist.addAll(tjtaskBean.getData());
                        daibanadapter.setList(daibanlist);

                    }
                });
    }

    /**
     * 代购接口
     */
    private void initDaiGou(final int page) {
        OkGo.<String>post(HttpManager.tjgoods)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("page", page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        TjgoodsBean tjgoodsBean = gson.fromJson(response.body().toString(), TjgoodsBean.class);


                        if (tjgoodsBean.getData().size() == 0 && page != 1) {
                            Toast.makeText(getActivity(),
                                    "只有这么多了~",
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (page == 1) {
                            daigoulist.clear();
                        }

                        daigoulist.addAll(tjgoodsBean.getData());

                        daigouadapter.setList(daigoulist);

                    }
                });
    }


    private void loadHeader() {
        OkGo.<String>post(HttpManager.Index)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Gson gson = new Gson();
                            HIndexBean hIndexBean = gson.fromJson(response.body().toString(), HIndexBean.class);
                            banner2BeanList.addAll(hIndexBean.getData().getBanner2());
                            for (int i = 0; i < hIndexBean.getData().getBanner1().size(); i++) {
                                networkImages.add(HttpManager.INDEX + hIndexBean.getData().getBanner1().get(i).getImage());
                            }
                            initpage();

                            bannerList.addAll(hIndexBean.getData().getBanner1());

                            for (int i = 0; i < banner2BeanList.size(); i++) {
                                if (i == 0) {
                                    ImageLoader.getInstance().initGlide(getActivity()).loadImage(HttpManager.INDEX + banner2BeanList.get(0).getImage(), iv_img1);
                                } else if (i == 1) {
                                    ImageLoader.getInstance().initGlide(getActivity()).loadImage(HttpManager.INDEX + banner2BeanList.get(1).getImage(), iv_img2);
                                } else if (i == 2) {
                                    ImageLoader.getInstance().initGlide(getActivity()).loadImage(HttpManager.INDEX + banner2BeanList.get(2).getImage(), iv_img3);
                                } else {
                                    ImageLoader.getInstance().initGlide(getActivity()).loadImage(HttpManager.INDEX + banner2BeanList.get(3).getImage(), iv_img4);
                                }
                            }

                            //获取分享里的头像和标题，下载地址；
                            MyShare.setTitle(hIndexBean.getData().getAndroid().getTitle());
                            MyShare.setLogo(HttpManager.INDEX + hIndexBean.getData().getAndroid().getLogo());
                            MyShare.setDownload(hIndexBean.getData().getAndroid().getDownload());
                            MyShare.setQr_code(hIndexBean.getData().getAndroid().getQr_code());//设置二维码图片
                        } catch (JsonSyntaxException e) {
                            ToastUtils.showToast(getActivity(), "网络异常!");
                        }

                    }
                });
    }


    private void initListener() {
        daigouadapter.setmOnItemClickListener(new HomeDaiGouAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //跳转到详情
                Intent intent = new Intent();
                intent.setClass(getActivity(), ProductdetailsActivity.class);
                intent.putExtra("goods_id", daigoulist.get(position).getGoods_id());
                intent.putExtra("lookshop", 1);
                startActivity(intent);
            }
        });

        daigouadapter.setmOnItemLookshopListener(new HomeDaiGouAdapter.OnItemLookshopListener() {
            @Override
            public void OnItemLookshop(TextView view, int position) {
                //跳转到店铺页面
                intent = new Intent(getActivity(), LookShopActivity.class);
                intent.putExtra("shopid", daigoulist.get(position).getShop_id());
                startActivity(intent);
                LogUtils.e("homefragment_shopid", daigoulist.get(position).getShop_id());
            }
        });

        daibanadapter.setmOnItemClickListener(new HomeDaiBanAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(TextView view, int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + daibanlist.get(position).getPhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        daigouadapter = new HomeDaiGouAdapter(getActivity(), daigoulist);
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 2);
        mainHomeLv.setLayoutManager(mgr);
        mainHomeLv.setAdapter(daigouadapter);
        mainHomeLv.setNestedScrollingEnabled(false);

        daibanadapter = new HomeDaiBanAdapter(getActivity(), daibanlist);
        LinearLayoutManager lmg = new LinearLayoutManager(getActivity());
        mainHomelvDaiban.setLayoutManager(lmg);
        mainHomelvDaiban.setAdapter(daibanadapter);
        mainHomelvDaiban.setNestedScrollingEnabled(false);

    }

    private void initpage() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(networkImages);
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //轮播图点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                for (int i = 0; i < bannerList.size(); i++) {
                    if (position == i) {
                        intentclassif(bannerList.get(i).getData_id(), bannerList.get(i).getData_type());
                    }
                }
            }
        });

    }


    private void intentclassif(String data_id, String data_type) {
        if (!data_id.equals("0")) {
            if (data_type.equals("1")) { //代办详情
                startActivity(new Intent(getActivity(), DaiBanDetailsActivity.class)
                        .putExtra("danbanid", data_id));
            } else if (data_type.equals("2")) { //店铺详情
                intent = new Intent(getActivity(), LookShopActivity.class);
                intent.putExtra("shopid", data_id);
                startActivity(intent);
            }
        }

    }

    @Override
    protected void lazyLoad() {
        //写网络请求
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @OnClick({R.id.ll_main_fenlei, R.id.ll_main_selecte, R.id.ll_home_dijia, R.id.ll_home_food,
            R.id.ll_outfit, R.id.ll_supermarket, R.id.ll_home_fruit, R.id.ll_home_hardware,
            R.id.ll_sousuo_serch, R.id.ll_daigou_more, R.id.ll_daiban_more,
            R.id.iv_img1, R.id.iv_img2, R.id.iv_img3, R.id.iv_img4, R.id.ll_daigoupage, R.id.ll_daibanpage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sousuo_serch://搜索
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), HomeSearchActivity.class));
                }
                break;
            case R.id.ll_main_fenlei://分类
                MainActivity.instance.setBg(2);
                MainActivity.instance.setToProjectFragment();
                break;
            case R.id.ll_main_selecte://精选
                ClassType.setLeixing("0");
                MainActivity.instance.setBg(2);
                MainActivity.instance.setToProjectFragment();
                break;
            case R.id.ll_home_dijia://低价
                ClassType.setLeixing("1");
                MainActivity.instance.setBg(2);
                MainActivity.instance.setToProjectFragment();
                break;
            case R.id.ll_home_food://美食
                startfenlei("美食", 1);
                break;
            case R.id.ll_outfit://美装
                startfenlei("美装", 2);
                break;
            case R.id.ll_supermarket://超市
                startfenlei("超市", 3);
                break;
            case R.id.ll_home_fruit://果蔬
                startfenlei("果蔬", 4);
                break;
            case R.id.ll_home_hardware://五金
                startfenlei("五金", 5);
                break;
            case R.id.ll_daigou_more: //代购更多跳转分类
                MainActivity.instance.setBg(2);
                MainActivity.instance.setToProjectFragment();
                break;
            case R.id.ll_daiban_more://代办更多跳转到代办
                startActivity(new Intent(getActivity(), DaiBanMoreActivity.class));
                break;
            case R.id.iv_img1://优速办1
                if (banner2BeanList.size() != 0) {
                    intentclassif(banner2BeanList.get(0).getData_id(), banner2BeanList.get(0).getData_type());
                }
                break;
            case R.id.iv_img2://优速办2
                if (banner2BeanList.size() != 0) {
                    intentclassif(banner2BeanList.get(0).getData_id(), banner2BeanList.get(0).getData_type());
                }
                break;
            case R.id.iv_img3://优速办3
                if (banner2BeanList.size() != 0) {
                    intentclassif(banner2BeanList.get(0).getData_id(), banner2BeanList.get(0).getData_type());
                }
                break;
            case R.id.iv_img4://优速办4
                if (banner2BeanList.size() != 0) {
                    intentclassif(banner2BeanList.get(0).getData_id(), banner2BeanList.get(0).getData_type());
                }
                break;
            case R.id.ll_daigoupage://代购加载更多
                initDaiGou(page++);
                break;
            case R.id.ll_daibanpage://代办加载更多
                initDaiBan(count++);
                break;
        }
    }

    private void startfenlei(String text, int id) {
        startActivity(new Intent(getActivity(), ClassifShopActivity.class)
                .putExtra("name", text)
                .putExtra("id", id)
        );
    }


}
