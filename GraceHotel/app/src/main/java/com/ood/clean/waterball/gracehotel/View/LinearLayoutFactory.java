package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionGroupModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.entity.Question;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;

/**
 * Created by user on 2017/12/18.
 */

public class LinearLayoutFactory {
    private static final String TAG = "LinearLayoutFactory";
    private Context context;
    public LinearLayoutFactory(Context context){
        this.context = context;

    }
    public LinearLayout createLinearLayout(QuestionModel questionModel){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        if(questionModel.getQuestionType() == QuestionType.RADIOGROUP){
            linearLayout. addView(createTextView(questionModel.getQuestion()));
            linearLayout.addView(createRadioGroup(questionModel));
        }
        else{
            linearLayout.addView(createTextView(questionModel.getQuestion()));
            linearLayout.addView(createFilling(questionModel));
        }
        return linearLayout;
    }
    public  TextView createTextView(String text){
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(15);
        return textView;
    }
    public  RadioGroup createRadioGroup(QuestionModel questionModel){

        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);

        RadioGroupQuestion radioGroupQuestion =  (RadioGroupQuestion) questionModel;
        int counts = radioGroupQuestion.getOptions().size();
        for (int i = 0 ; i < counts; i++){
            RadioButton radioButton = new RadioButton(context);
            OptionBinding.bind(radioGroupQuestion.getOption(i),radioButton);
            radioButton.setText(radioGroupQuestion.getOption(i).getOptionName());
            radioGroup.addView(radioButton);
        }
        return radioGroup;
    }
    public  EditText createFilling(QuestionModel questionModel){
        EditText editText = new EditText(context);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();


        FillingQuestion fillingQuestion = (FillingQuestion) questionModel;
        FillingAnswerBinding.bind(fillingQuestion,editText);
        return  editText;
    }


}
