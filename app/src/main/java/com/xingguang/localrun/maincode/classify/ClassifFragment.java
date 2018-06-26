package com.xingguang.localrun.maincode.classify;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarFragment;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.classify.model.ClassifBean;
import com.xingguang.localrun.maincode.classify.view.adapter.LeftListAdapter;
import com.xingguang.localrun.maincode.classify.view.adapter.RightAdapter;
import com.xingguang.localrun.maincode.mine.model.MineApplyBean;
import com.xingguang.localrun.refresh.RefreshUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassifFragment extends ToolBarFragment implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.right_listView)
    ListView rightListView;
    @BindView(R.id.tw_refresh)
    TwinklingRefreshLayout twRefresh;
    @BindView(R.id.empty)
    ImageView empty;

    private boolean[] flagArray = {true, false, false, false, false, false, false};

    private LeftListAdapter leftListAdapter;
    RightAdapter rightListAdapter;


    List<MineApplyBean.DataBean> leftList = new ArrayList<>();
    List<ClassifBean.DataBean> rightList = new ArrayList<>();

    private boolean isScroll = true;
    private boolean shouldSet;
    private int page = 1;
    private boolean isRefresh = false;
    private int pos = 0;
    private String classifId = "";//左面列表的分类id

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classif;
    }

    @Override
    protected void initView() {
//        twRefresh.setEnableOverScroll(false);
        twRefresh.setEnableRefresh(false);
        twRefresh.setBottomView(new LoadingView(getActivity()));
        twRefresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());


        setToolBarTitle("分类");

        rightListAdapter = new RightAdapter(getActivity(), rightList);
        rightListView.setAdapter(rightListAdapter);

        leftListAdapter = new LeftListAdapter(getActivity(), leftList, flagArray);
        leftListview.setAdapter(leftListAdapter);

        initListener();
        loadleft();
    }

    private void initListener() {
        leftListview.setItemChecked(0, true);

        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;
                for (int i = 0; i < leftList.size(); i++) {
                    if (i == position) {
                        flagArray[i] = true;
                    } else {
                        flagArray[i] = false;
                    }
                }
                leftListAdapter.notifyDataSetChanged();
                int rightSection = 0;
                rightListView.setSelection(rightSection);

                classifId = leftList.get(position).getId();
                loadRight(classifId,page);
            }

        });


        rightListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    shouldSet = false;
                } else {
                    shouldSet = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll){
                    if (shouldSet){
                        String title = rightListAdapter.getItem(firstVisibleItem).toString();
                        int section = leftListAdapter.indexOf(title);
                        if (section == -1) return;
                        leftListview.setItemChecked(section, true);
                        leftListview.smoothScrollToPosition(section);
                    }
                } else {
                    isScroll = true;
                }
            }
        });



    }

    /**
     * 右面的数据源
     *
     * @param id*/
    private void loadRight(String id, final int page) {
        OkGo.<String>post(HttpManager.classifshop)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id",id)
                .params("page",page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ClassifBean bean  = gson.fromJson(response.body().toString(), ClassifBean.class);
                        if (bean.getData()!=null) {

                            if (page == 1 ){
                                rightList.clear();
                            }

                            rightList.addAll(bean.getData());
                            if (bean.getData().size() == 0){
                                ToastUtils.showToast(getActivity(),"暂无更多!");
                            }else {
                                rightListAdapter.setList(bean.getData());
                            }

                            if (rightList.size() == 0) {
                                rightListView.setVisibility(View.GONE);
                                empty.setVisibility(View.VISIBLE);
                            } else {
                                empty.setVisibility(View.GONE);
                                rightListView.setVisibility(View.VISIBLE);
                            }


                            if (isRefresh) {
                                twRefresh.finishRefreshing();
                            } else {
                                twRefresh.finishLoadmore();
                            }

                        }else{
                            ToastUtils.showToast(getActivity(),bean.getMsg());
                        }
                    }
                });
    }


    /**左面的数据*/
    private void loadleft() {
        OkGo.<String>post(HttpManager.applyindex)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MineApplyBean mineApplyBean  = gson.fromJson(response.body().toString(), MineApplyBean.class);
                        leftList.addAll(mineApplyBean.getData());
                        leftListAdapter.setList(mineApplyBean.getData());

                        loadRight(leftList.get(0).getId(),page);
                    }
                });
    }


    @Override
    public void onLoad() {
        isRefresh = false;
        page++;
        loadRight(classifId,page);

    }

    @Override
    protected void lazyLoad() {}
    @Override
    public void onRefresh() {
        isRefresh = true;
    }


}
