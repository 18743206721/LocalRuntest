package com.xingguang.localrun.maincode.classify;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.HttpFragment;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.ClassType;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.classify.model.ClassifBean;
import com.xingguang.localrun.maincode.classify.view.adapter.LeftListAdapter;
import com.xingguang.localrun.maincode.classify.view.adapter.RightAdapter;
import com.xingguang.localrun.maincode.mine.model.MineApplyBean;
import com.xingguang.localrun.refresh.RefreshUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassifFragment extends HttpFragment implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.right_listView)
    ListView rightListView;
    @BindView(R.id.tw_refresh)
    TwinklingRefreshLayout twRefresh;
    @BindView(R.id.empty)
    ImageView empty;

    private boolean[] flagArray;

    private LeftListAdapter leftListAdapter;
    RightAdapter rightListAdapter;


    List<MineApplyBean.DataBean> leftList = new ArrayList<>();
    List<ClassifBean.DataBean> rightList = new ArrayList<>();

    private boolean isScroll = true;
    private boolean shouldSet;
    private int page = 1;
    private boolean isRefresh = false;
    private String classifId = "";//左面列表的分类id

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classif;
    }

    @Override
    protected void initView() {
        twRefresh.setEnableRefresh(false);
        twRefresh.setBottomView(new LoadingView(getActivity()));
        twRefresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());

        rightListAdapter = new RightAdapter(getActivity(), rightList);
        rightListView.setAdapter(rightListAdapter);

        leftListAdapter = new LeftListAdapter(getActivity(), leftList, flagArray);
        leftListview.setAdapter(leftListAdapter);

        initListener();
        loadleft();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {// 重新显示到最前端中,相当于onresume
            if (ClassType.getLeixing() != null) {
                if (ClassType.getLeixing().equals("0")) {//精选
                    int buffSize = leftList.size();
                    flagArray = new boolean[buffSize];
                    for (int i = 0; i < flagArray.length; i++) {
                        if (i == 0) {
                            flagArray[i] = true;
                        } else {
                            flagArray[i] = false;
                        }
                    }
                    leftListAdapter.setList(leftList);
                    leftListAdapter.setFLagArray(flagArray);
                    loadspecial(leftList.get(0).getId(), page);
                } else { //低价
                    int buffSize = leftList.size();
                    flagArray = new boolean[buffSize];
                    for (int i = 0; i < flagArray.length; i++) {
                        if (i == 1) {
                            flagArray[i] = true;
                        } else {
                            flagArray[i] = false;
                        }
                    }
                    leftListAdapter.setList(leftList);
                    leftListAdapter.setFLagArray(flagArray);
                    loadspecial(leftList.get(1).getId(), page);
                }
            }
        }
        super.onHiddenChanged(hidden);
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

                if (position == 0) {
                    classifId = leftList.get(position).getId();
                    loadspecial(classifId, page);
                } else if (position == 1) {
                    classifId = leftList.get(position).getId();
                    loadspecial(classifId, page);
                } else {
                    classifId = leftList.get(position).getId();
                    loadRight(classifId, page);
                }


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
                if (isScroll) {
                    if (shouldSet) {
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
     * @param id
     */
    private void loadRight(String id, final int page) {
        OkGo.<String>post(HttpManager.classifshop)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", id)
                .params("page", page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ClassifBean bean = gson.fromJson(response.body().toString(), ClassifBean.class);
                        if (bean.getData() != null) {

                            if (bean.getData().size() == 0 && page != 1) {
                                Toast.makeText(getActivity(),
                                        "只有这么多了~",
                                        Toast.LENGTH_SHORT).show();
                            }

                            if (page == 1) {
                                rightList.clear();
                            }

                            rightList.addAll(bean.getData());
                            rightListAdapter.setList(bean.getData());

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

                        } else {
                            ToastUtils.showToast(getActivity(), bean.getMsg());
                        }
                    }
                });
    }

    /**
     * 左面的数据
     */
    private void loadleft() {
        OkGo.<String>post(HttpManager.applyindex)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().toString());
                            JSONArray array = jsonObject.getJSONArray("data");
                            for (int i = 0; i < 2; i++) {
                                MineApplyBean.DataBean bean = new MineApplyBean.DataBean();
                                if (i == 0) {
                                    bean.setId("1");
                                    bean.setName("精选");
                                } else if (i == 1) {
                                    bean.setId("2");
                                    bean.setName("低价");
                                }
                                leftList.add(bean);
                            }
                            for (int i = 0; i < array.length(); i++) {
                                MineApplyBean.DataBean bean = new MineApplyBean.DataBean();
                                JSONObject obj1 = (JSONObject) array.get(i);
                                bean.setId(obj1.getString("id"));
                                bean.setName(obj1.getString("name"));
                                leftList.add(bean);
                            }
                            int buffSize = leftList.size();
                            flagArray = new boolean[buffSize];
                            for (int i = 0; i < flagArray.length; i++) {
                                if (i == 0) {
                                    flagArray[i] = true;
                                } else {
                                    flagArray[i] = false;
                                }
                            }
                            leftListAdapter.setList(leftList);
                            leftListAdapter.setFLagArray(flagArray);
                            loadspecial(leftList.get(0).getId(), page);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    /**
     * 点击精选和低价走的接口
     */
    private void loadspecial(String id, final int page) {
        OkGo.<String>post(HttpManager.Goodsspecial)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("type", id)
                .params("page", page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ClassifBean bean = gson.fromJson(response.body().toString(), ClassifBean.class);
                        if (bean.getData() != null) {

                            if (page == 1) {
                                rightList.clear();
                            }

                            rightList.addAll(bean.getData());
                            if (bean.getData().size() == 0) {
                                ToastUtils.showToast(getActivity(), "暂无更多!");
                            } else {
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

                        } else {
                            ToastUtils.showToast(getActivity(), bean.getMsg());
                        }
                    }
                });
    }


    @Override
    public void onLoad() {
        isRefresh = false;
        page++;
        loadRight(classifId, page);

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
    }


}
