package com.ood.clean.waterball.gracehotel.View;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;

/**
 * Created by user on 2017/12/28.
 */

public class FillingAnswerBinding {
    private static final String TAG = "FillingAnswerBinding";

    public static void bind(FillingQuestion fillingQuestion, EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG,"BeforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG,editText.getText().toString());
                fillingQuestion.setAnswer(editText.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG,"AfterTextChanged");
            }
        });
    }
}
