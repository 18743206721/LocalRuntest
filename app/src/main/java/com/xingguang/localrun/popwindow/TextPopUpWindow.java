/**
 * @param
 */
package com.xingguang.localrun.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xingguang.localrun.R;


/**
 * @param
 */
public class TextPopUpWindow extends PopupWindow {

    private TextView title;
    private TextView title1;

    /**
     * 弹出样式是，底下一个按钮的
     */
//    public TextPopUpWindow(final Context context, View parent, final String content, String rightTv, OnClickListener onClickListener) {
//        View view = View.inflate(context, R.layout.pop_textonebtn, null);
//        title = (TextView) view.findViewById(R.id.popup_content);
//        TextView commit = (TextView) view.findViewById(R.id.popupwindow_content_commit);
//        commit.setText(rightTv);
//        title.setText(content);
//
//        setWidth(LayoutParams.MATCH_PARENT);
//        setHeight(LayoutParams.MATCH_PARENT);
//        setBackgroundDrawable(new ColorDrawable(0x99000000));
//        setFocusable(true);
//        setOutsideTouchable(true);
//        setContentView(view);
//        showAtLocation(parent, Gravity.CENTER, 0, 0);
//        update();
//        commit.setOnClickListener(onClickListener);
//    }

    /**
     * 弹出样式是，底下一个按钮的
     */
//    public TextPopUpWindow(final Context context, View parent, String rightTv, OnClickListener onClickListener) {
//        View view = View.inflate(context, R.layout.pop_textonebtns, null);
//        title = (TextView) view.findViewById(R.id.popup_content);
//        TextView commit = (TextView) view.findViewById(R.id.popupwindow_content_commit);
//        commit.setText(rightTv);
//
//        setWidth(LayoutParams.MATCH_PARENT);
//        setHeight(LayoutParams.MATCH_PARENT);
//        setBackgroundDrawable(new ColorDrawable(0x99000000));
//        setFocusable(true);
//        setOutsideTouchable(true);
//        setContentView(view);
//        showAtLocation(parent, Gravity.CENTER, 0, 0);
//        update();
//        commit.setOnClickListener(onClickListener);
//    }


    public TextPopUpWindow(final Context context, View parent, final String content, String leftTv, String rightTv, OnClickListener onClickListener) {
        View view = View.inflate(context, R.layout.pop_text, null);
        title = (TextView) view.findViewById(R.id.popup_content);
        TextView dismiss = (TextView) view
                .findViewById(R.id.popupwindow_content_dismiss);
        dismiss.setText(leftTv);
        TextView commit = (TextView) view
                .findViewById(R.id.popupwindow_content_commit);
        commit.setText(rightTv);
        title.setText(content);

        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x99000000));
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.CENTER, 0, 0);
        update();
        dismiss.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        commit.setOnClickListener(onClickListener);
    }


    public TextPopUpWindow(final Context context, View parent, final String content, String leftTv, String rightTv, OnClickListener onClickListenerL, OnClickListener onClickListener) {
        View view = View.inflate(context, R.layout.pop_text, null);
        title1 = (TextView) view.findViewById(R.id.popup_content);
        TextView dismiss = (TextView) view
                .findViewById(R.id.popupwindow_content_dismiss);
        dismiss.setText(leftTv);
        TextView commit = (TextView) view
                .findViewById(R.id.popupwindow_content_commit);

        commit.setText(rightTv);
        title1.setText(content);

        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x99000000));
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.CENTER, 0, 0);
        update();

        dismiss.setOnClickListener(onClickListenerL);
        commit.setOnClickListener(onClickListener);
    }




}
