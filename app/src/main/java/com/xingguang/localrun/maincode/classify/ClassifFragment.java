package com.xingguang.localrun.maincode.classify;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarFragment;
import com.xingguang.localrun.maincode.classify.view.adapter.LeftListAdapter;
import com.xingguang.localrun.maincode.classify.view.adapter.RightAdapter;

import butterknife.BindView;

public class ClassifFragment extends ToolBarFragment {

    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.right_listView)
    ListView rightListView;
    private String[] leftStr = new String[]{"精选", "低价", "美食", "美装", "超市","果蔬","五金"};
    private boolean[] flagArray = {true, false, false, false, false, false, false};
    private String[] rightStr = new String[]{"番茄鸡蛋", "红烧排骨", "农家小炒肉","番茄鸡蛋", "红烧排骨", "番茄鸡蛋", "红烧排骨", "农家小炒肉","农家小炒肉"};

    private LeftListAdapter leftListAdapter;
    RightAdapter rightListAdapter;

    private boolean isScroll = true;
    private boolean shouldSet;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classif;
    }

    @Override
    protected void initView() {
        setToolBarTitle("分类");

        rightListAdapter = new RightAdapter(getActivity(), rightStr);
        rightListView.setAdapter(rightListAdapter);

        leftListAdapter = new LeftListAdapter(getActivity(), leftStr, flagArray);
        leftListview.setAdapter(leftListAdapter);

        initListener();

    }

    private void initListener() {
        leftListview.setItemChecked(0, true);

        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
//                isScroll = false;
//                leftListview.setItemChecked(position, true);
//                int section = rightListAdapter.indexOf(leftListAdapter.getItem(position).toString());
//                if (section == -1) return;
//                rightListView.setSelection(section);


                isScroll = false;

                for (int i = 0; i < leftStr.length; i++) {
                    if (i == position) {
                        flagArray[i] = true;
                    } else {
                        flagArray[i] = false;
                    }
                }
                leftListAdapter.notifyDataSetChanged();
                int rightSection = 0;
//                for (int i = 0; i < position; i++) {
//                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
//                }
                rightListView.setSelection(rightSection);



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

    @Override
    protected void lazyLoad() {
        //写网络请求

    }



}
