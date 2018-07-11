/**
 *
 */
package com.xingguang.localrun.http;
/**
 *
 */

import android.app.Dialog;
import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.xingguang.localrun.R;


/**
 * @param
 * @author
 * @return
 */
public class CustomProgressDialog extends Dialog {

    private Context context = null;
    private static CustomProgressDialog customProgressDialog = null;

    /**
     * @param context
     */
    public CustomProgressDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static CustomProgressDialog createDialog(Context context) {
        customProgressDialog = new CustomProgressDialog(context,
                R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.loading_dialog);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        //最重要的一句话，一定要加上！要不然怎么设置都不行！
//        Window window = customProgressDialog.getWindow();
//        window.setBackgroundDrawableResource(android.R.color.transparent);
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        Display d = window.getWindowManager().getDefaultDisplay();
//        //获取屏幕宽
//        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
//        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度自适应
//        window.setAttributes(wlp);


        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();




        return customProgressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        if (customProgressDialog == null) {
            return;
        }

        AVLoadingIndicatorView iva = (AVLoadingIndicatorView) customProgressDialog
                .findViewById(R.id.loading);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView
//                .getBackground();
//        animationDrawable.start();
        iva.show();

    }

    /**
     * [Summary] setTitile ����
     *
     * @param strTitle
     * @return
     */
    public CustomProgressDialog setTitile(String strTitle) {
        return customProgressDialog;
    }


}
