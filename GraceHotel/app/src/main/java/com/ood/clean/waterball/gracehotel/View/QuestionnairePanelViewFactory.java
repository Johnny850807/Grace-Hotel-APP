package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;
import com.ood.clean.waterball.gracehotel.utils.ViewUtils;

import static com.ood.clean.waterball.gracehotel.utils.ViewUtils.convertDpToPixel;

public class QuestionnairePanelViewFactory {
    private static final String TAG = "QuestionnairePanelViewFactory";
    private Context context;

    public QuestionnairePanelViewFactory(Context context){
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

    public View createFilling(QuestionModel questionModel){
        TextInputLayout textInputLayout = new TextInputLayout(context);
        int padding = convertDpToPixel(6, context);
        textInputLayout.setPadding(padding, padding, padding, padding);
        TextInputEditText textInputEditText = new TextInputEditText(context);
        textInputEditText.setMinWidth(convertDpToPixel(300, context));
        textInputLayout.addView(textInputEditText);
        textInputEditText.setHint(questionModel.getQuestion());
        textInputEditText.setOnClickListener((v)->{
            ViewUtils.simpleMessageEdittextDialog(context,
                    questionModel.getQuestion(), textInputEditText::setText).show();
        });
        textInputEditText.requestFocus();

        FillingQuestion fillingQuestion = (FillingQuestion) questionModel;
        FillingAnswerBinding.bind(fillingQuestion, textInputEditText);
        return textInputLayout;
    }


}
