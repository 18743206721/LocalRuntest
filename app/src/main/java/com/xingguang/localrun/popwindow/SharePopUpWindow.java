/**
 * @param
 */
package com.xingguang.localrun.popwindow;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xingguang.localrun.R;

/**
 * @param
 */
public class SharePopUpWindow extends PopupWindow implements View.OnClickListener {
    private LinearLayout sina;
    private LinearLayout wechat;
    private LinearLayout wechat_circle;
    private LinearLayout qq;
    private LinearLayout qzone;

    private Context context;
    private OnShareListener listener;
    private RelativeLayout scrn;

    public SharePopUpWindow(Activity context, View parent, OnShareListener listener) {
        this.context = context;
        this.listener = listener;
        View view = View.inflate(context, R.layout.dialog_share,
                null);
        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.pop_enter_anim));

        MobclickAgent.onEvent(context, "Course_Share");

        qq = (LinearLayout) view.findViewById(R.id.qq);
        sina = (LinearLayout) view.findViewById(R.id.sina);
        wechat = (LinearLayout) view.findViewById(R.id.wechat);
        wechat_circle = (LinearLayout) view.findViewById(R.id.wechat_circle);
        qzone = (LinearLayout) view.findViewById(R.id.qzone);
        scrn = (RelativeLayout) view.findViewById(R.id.scrn);

        qq.setOnClickListener(this);
        sina.setOnClickListener(this);
        wechat.setOnClickListener(this);
        wechat_circle.setOnClickListener(this);
        qzone.setOnClickListener(this);

        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.CENTER, 0, 0);
        update();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sina:
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listener.onShareListener(SHARE_MEDIA.SINA);
                        dismiss();
                    }
                }, 800);
                break;
            case R.id.wechat:
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listener.onShareListener(SHARE_MEDIA.WEIXIN);
                        dismiss();
                    }
                }, 800);
                break;
            case R.id.wechat_circle:
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listener.onShareListener(SHARE_MEDIA.WEIXIN_CIRCLE);
                        dismiss();
                    }
                }, 800);
                break;
            case R.id.qzone:
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listener.onShareListener(SHARE_MEDIA.QZONE);
                        dismiss();
                    }
                }, 800);
                break;
            case R.id.qq:
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listener.onShareListener(SHARE_MEDIA.QQ);
                        dismiss();
                    }
                }, 800);
                break;
            default:
                break;
        }
    }

    public interface OnShareListener {
        public void onShareListener(SHARE_MEDIA share_media);
    }

//    private void setStartAnimator(View view) {
//        view.setVisibility(View.VISIBLE);
//        ObjectAnimator ScaleAnimX1 = ObjectAnimator.ofFloat(view,
//                "scaleX", 0.1f
//                , 1.0f);
//        // 设置ScaleAnim1动画持续时间
//        ScaleAnimX1.setDuration(700);
//        // 设置ScaleAnim1动画的插值方式：减速插值
//        ScaleAnimX1.setInterpolator(new BounceInterpolator());
//
//        ObjectAnimator ScaleAnimY1 = ObjectAnimator.ofFloat(view,
//                "scaleY", 0.1f
//                , 1.0f);
//        // 设置ScaleAnim2动画持续时间
//        ScaleAnimY1.setDuration(700);
//        // 设置ScaleAnim2动画的插值方式：减速插值
//        ScaleAnimY1.setInterpolator(new BounceInterpolator());
//
//        // 定义一个AnimatorSet来组合动画
//        AnimatorSet animationSet = new AnimatorSet();
//        animationSet.play(ScaleAnimX1);
//        animationSet.play(ScaleAnimY1);
//        animationSet.start();
//    }

//    private void setEndAnimator(View view) {
//        view.setVisibility(View.VISIBLE);
//        ObjectAnimator ScaleAnimX1 = ObjectAnimator.ofFloat(view,
//                "scaleX", 1.0f
//                , 0.0f);
//        // 设置ScaleAnim1动画持续时间
//        ScaleAnimX1.setDuration(500);
//        // 设置ScaleAnim1动画的插值方式：减速插值
//        ScaleAnimX1.setInterpolator(new DecelerateInterpolator());
//
//        ObjectAnimator ScaleAnimY1 = ObjectAnimator.ofFloat(view,
//                "scaleY", 1.0f
//                , 0.0f);
//        // 设置ScaleAnim2动画持续时间
//        ScaleAnimY1.setDuration(500);
//        // 设置ScaleAnim2动画的插值方式：减速插值
//        ScaleAnimY1.setInterpolator(new DecelerateInterpolator());
//
//        //消失时间
//        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view
//                , "alpha", 0f, 0f);
//        // 设置动画持续时间
//        fadeAnim.setDuration(500);
//
//        //透明度动画
//        ObjectAnimator appearAnim = ObjectAnimator.ofFloat(view
//                , "alpha", 1f, 0.2f);
//        // 设置动画持续时间
//        appearAnim.setDuration(500);
//        // 设置appearAnim动画的插值方式：减速插值
//        appearAnim.setInterpolator(new DecelerateInterpolator());
//
//        // 定义一个AnimatorSet来组合动画
//        AnimatorSet animationSet = new AnimatorSet();
//        animationSet.play(appearAnim).with(ScaleAnimX1);
//        animationSet.play(appearAnim).with(ScaleAnimY1);
//        animationSet.play(appearAnim).before(fadeAnim);
//        animationSet.start();
//    }
}