package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by user on 2017/12/18.
 */

public class ViewComponentFactory {
    private Context context;
    public ViewComponentFactory(Context context){
        this.context = context;
    }

    public TextView getTextView(Context context,String text){
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(15);
        return textView;
    }
    public RadioGroup getRadioGroup(Context context,int counts){
        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0 ; i < counts; i++){
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText(String.valueOf(i+1));
            radioGroup.addView(radioButton);
        }
        return radioGroup;
    }
    public EditText getEditText(Context context,String hint){
        EditText editText = new EditText(context);

        return  editText;
    }


}
