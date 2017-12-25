package com.ood.clean.waterball.gracehotel.View;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;

public class OptionBinding {
    private static final String TAG = "OptionBinding";
    private RadioButton radioButton;
    private RadioGroupQuestion.Option option;

    public OptionBinding(RadioGroupQuestion.Option option, RadioButton radioButton) {
        this.radioButton = radioButton;
        this.option = option;
        this.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                Log.d(TAG, "Option " + option.getOptionName() + " set value " + checked);
                option.setValue(checked);
            }
        });
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public RadioGroupQuestion.Option getOption() {
        return option;
    }

    public void setOption(RadioGroupQuestion.Option option) {
        this.option = option;
    }
}
