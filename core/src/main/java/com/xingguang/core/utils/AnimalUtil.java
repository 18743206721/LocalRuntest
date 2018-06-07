/**
 * @param
 */
package com.xingguang.core.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.widget.ImageView;

/**
 * 动画工具类
 *
 * @author LiuYu
 * @date 2016-12-14 15:39:03
 */
public class AnimalUtil {

    /**
     * 按钮点击效果（Q弹）
     */
    public static void QTanAnimal(final View view, final int resources,
                                  final int time, final OnAnimalFinished animalFinished) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0.7f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0.9f);
        PropertyValuesHolder pvhF = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0.9f);
        ObjectAnimator obj = ObjectAnimator.ofPropertyValuesHolder(view, pvhX,
                pvhY, pvhF);
        obj.setDuration(time).start();
        obj.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (view instanceof ImageView) {
                    animalFinished.doSomething();
                    ((ImageView) view).setImageResource(resources);
                }
                QTanEndAnimal(view, time);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
    }

    public interface OnAnimalFinished {
        void doSomething();
    }

    private static void QTanEndAnimal(View view, int time) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0.7f,
                1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX",
                0.9f, 1.3f, 1f, 1.2f, 1f, 1.1f, 1f);
        PropertyValuesHolder pvhF = PropertyValuesHolder.ofFloat("scaleY",
                0.9f, 1.3f, 1f, 1.2f, 1f, 1.1f, 1f);
        ObjectAnimator obj = ObjectAnimator.ofPropertyValuesHolder(view, pvhX,
                pvhY, pvhF);
        obj.setDuration(time).start();
    }

}
