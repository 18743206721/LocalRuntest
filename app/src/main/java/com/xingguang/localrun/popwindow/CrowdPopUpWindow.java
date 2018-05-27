/**
 * @param
 */
package com.xingguang.localrun.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.xingguang.localrun.R;

/**
 * 实现功能： 翻译详情界面，分享弹出窗口
 * 类名称：PopWinShare
 * 类描述：(该类的主要功能)
 * 创建人:LiuYu
 * 创建时间：2017-10-24
 */
public class CrowdPopUpWindow extends PopupWindow {
    private View mainView;
    private LinearLayout ll_btn1,ll_btn2,ll_btn3;
    private Context context;

    public CrowdPopUpWindow(Activity paramActivity, View.OnClickListener paramOnClickListener) {
        super(paramActivity);
        context=paramActivity;
        //窗口布局
        mainView = LayoutInflater.from(paramActivity).inflate(R.layout.popwin_crowd_funding, null);
        ll_btn1 = ((LinearLayout) mainView.findViewById(R.id.ll_btn1));
        ll_btn2 = (LinearLayout) mainView.findViewById(R.id.ll_btn2);
        ll_btn3 = (LinearLayout) mainView.findViewById(R.id.ll_btn3);

        //设置每个子布局的事件监听器
        if (paramOnClickListener != null) {
            ll_btn1.setOnClickListener(paramOnClickListener);
            ll_btn2.setOnClickListener(paramOnClickListener);
            ll_btn3.setOnClickListener(paramOnClickListener);
        }
        setContentView(mainView);
        WindowManager wm = paramActivity.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        //设置宽度
        setWidth(width / 3);
        //设置高度
//        setHeight(paramInt2);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }

//    public void setTextColor(String crowdState) {
//        if ("2".equals(crowdState)) {
//            ingBtn.setTextColor(context.getResources().getColor(R.color.theme_color));
//            endBtn.setTextColor(context.getResources().getColor(R.color.textBlack));
//        } else {
//            ingBtn.setTextColor(context.getResources().getColor(R.color.textBlack));
//            endBtn.setTextColor(context.getResources().getColor(R.color.theme_color));
//        }
//    }
}