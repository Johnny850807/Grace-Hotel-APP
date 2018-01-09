package com.ood.clean.waterball.gracehotel.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ood.clean.waterball.gracehotel.R;


public class ViewUtils {

    public static int convertPixelToDp(float px, Context context){
        return (int) (px / getDensity(context));
    }

    public static int convertDpToPixel(float dp, Context context){
        return (int) (dp * getDensity(context));
    }

    public static float getDensity(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }

    public static AlertDialog simpleMessageEdittextDialog(Context context, String title, OnMessageSendOnClickListener onclick){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        RelativeLayout viewGroup = new RelativeLayout(context);
        viewGroup.setPadding(40, 20, 40, 20);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        EditText msgEd = new EditText(context);
        msgEd.setLayoutParams(params);
        viewGroup.addView(msgEd);

        return new AlertDialog.Builder(context)
                .setIcon(R.drawable.grace_mid_icon)
                .setTitle(title)
                .setView(viewGroup)
                .setPositiveButton(R.string.send, (d, i)->{
                    onclick.onMessageSendOnClick(msgEd.getText().toString());
                    //hide the showing keyboard
                    imm.hideSoftInputFromWindow(msgEd.getWindowToken(), 0);
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    public interface OnMessageSendOnClickListener{
        void onMessageSendOnClick(String message);
    }
}
