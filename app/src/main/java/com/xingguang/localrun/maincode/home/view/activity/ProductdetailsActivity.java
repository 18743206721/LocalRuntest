package com.xingguang.localrun.maincode.home.view.activity;

import android.view.View;
import android.widget.Toolbar;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.view.TiceScrollview;

import butterknife.BindView;


/**
 * 创建日期：2018/5/23
 * 描述:商品详情
 * 作者:LiuYu
 */
public class ProductdetailsActivity extends ToolBarActivity implements TiceScrollview.onScrollChangedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int mHeight;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_productdetails;
    }

    @Override
    protected void initView() {
        getToolbarTitle().setText("商品详情");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initListener();

    }

    private void initListener() {

        // 获取顶部图片高度后，设置滚动监听
//        ll_parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                ll_parent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                mHeight = iv_tice_head.getHeight() / 2;
//                onScrollChanged(sc_head_view.getScrollY());
//            }
//        });


    }


    @Override
    public void onScrollChanged(int y) {
//        if (y <= 0) {//未滑动
//            toolbar.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
//            ctleft.setImageDrawable(getResources().getDrawable(R.mipmap.back_white_1));
//            ctRightImg.setImageDrawable(getResources().getDrawable(R.mipmap.video_share_white));
//        } else if (y > 0 && y <= mHeight) { //滑动过程中 并且在mHeight之内
//            float scale = (float) y / mHeight;
//            float alpha = (255 * scale);
//            ct_center_text.setText(ticemodule.getName());
//            ct_center_text.setVisibility(View.VISIBLE);
//            ctleft.setImageDrawable(getResources().getDrawable(R.mipmap.back));
//            ctRightImg.setImageDrawable(getResources().getDrawable(R.mipmap.shared_black));
//            toolbar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//        } else {//超过mHeight
//            toolbar.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//        }

    }




}
