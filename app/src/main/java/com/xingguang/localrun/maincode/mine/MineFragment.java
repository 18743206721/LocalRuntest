package com.xingguang.localrun.maincode.mine;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragment;
import com.xingguang.localrun.utils.AppUtil;

import butterknife.BindView;

public class MineFragment extends BaseFragment {


    @BindView(R.id.AppFragment_Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.AppFragment_AppBarLayout)
    AppBarLayout mAppBarLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

        //設置Toolbar透明
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //設置appbar的滑動颜色渐变效果
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mToolbar.setBackgroundColor(AppUtil.changeAlpha(getResources().getColor(R.color.colorPrimary), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
            }
        });

    }

    @Override
    protected void lazyLoad() {
        //写网络请求

    }


}
