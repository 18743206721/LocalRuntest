package com.xingguang.localrun.maincode.classify;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarFragment;
import com.xingguang.localrun.maincode.classify.view.adapter.LeftListAdapter;
import com.xingguang.localrun.maincode.classify.view.adapter.MainSectionedAdapter;

import butterknife.BindView;

public class ClassifFragment extends ToolBarFragment {

    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.pinnedListView)
    ListView pinnedListView;
    private String[] leftStr = new String[]{"面食类", "盖饭", "寿司", "烧烤", "酒水"};
    private boolean[] flagArray = {true, false, false, false, false, false, false, false, false};
    private LeftListAdapter adapter;
    private boolean isScroll = true;
    private String[][] rightStr = new String[][]{{"热干面", "臊子面", "烩面"},
            {"番茄鸡蛋", "红烧排骨", "农家小炒肉"},
            {"芝士", "丑小丫", "金枪鱼"}, {"羊肉串", "烤鸡翅", "烤羊排"}, {"长城干红", "燕京鲜啤", "青岛鲜啤"}};

    MainSectionedAdapter sectionedAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classif;
    }

    @Override
    protected void initView() {
        setToolBarTitle("分类");

        final MainSectionedAdapter sectionedAdapter = new MainSectionedAdapter(getActivity(), leftStr, rightStr);
        pinnedListView.setAdapter(sectionedAdapter);

        adapter = new LeftListAdapter(getActivity(), leftStr, flagArray);
        leftListview.setAdapter(adapter);

        initListener();

    }

    private void initListener() {
        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;

                for (int i = 0; i < leftStr.length; i++) {
                    if (i == position) {
                        flagArray[i] = true;
                    } else {
                        flagArray[i] = false;
                    }
                }
                adapter.notifyDataSetChanged();
                int rightSection = 0;
//                for (int i = 0; i < position; i++) {
//                    rightSection += sectionedAdapter.getCount(i) + 1;
//                }
                pinnedListView.setSelection(rightSection);

            }

        });

    }

    @Override
    protected void lazyLoad() {
        //写网络请求

    }



}
