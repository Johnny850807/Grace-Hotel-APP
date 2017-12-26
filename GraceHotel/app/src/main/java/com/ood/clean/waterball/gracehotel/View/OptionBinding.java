package com.ood.clean.waterball.gracehotel.View;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;

import static android.R.attr.checked;

public class OptionBinding {
    private static final String TAG = "OptionBinding";

    public static void bind(RadioGroupQuestion.Option option, RadioButton radioButton){
        Log.d(TAG, "Option " + option.getOptionName() + " set value " + checked);
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                option.setValue(checked);
            }
        });
    }

}
