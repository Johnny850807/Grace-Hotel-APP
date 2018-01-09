package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionGroupModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import com.ood.clean.waterball.gracehotel.R;

import java.util.LinkedList;


public class QuestionnairePanel extends LinearLayout implements QuestionnaireView {
    private static final String TAG = "MyQuestionnairePanel";
    private static final int EARNED_MONEY = 70;
    private QuestionnaireDialogFragment questionnaireDialogFragment;
    private QuestionnairePresenter questionnairePresenter;
    private Context context;
    private QuestionGroupModel questionGroupModel;
    private int commitSuccessfullyAmount = 0;
    private ProgressBar progressBar;

    public QuestionnairePanel(Context context,
                              QuestionnairePresenter questionnairePresenter,
                              QuestionnaireDialogFragment questionnaireDialogFragment,
                              OnUserUpdatedListener onUserUpdatedListener) {
        super(context);
        this.questionnaireDialogFragment = questionnaireDialogFragment;
        this.context = context;
        this.questionnairePresenter = questionnairePresenter;
        this.setOrientation(VERTICAL);

        progressBar = createLoadingProgressBar();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        addView(progressBar);

        questionnairePresenter.setOnUserUpdatedListener(onUserUpdatedListener);
        questionnairePresenter.setQuestionnaireView(this);
        questionnairePresenter.loadQuestionnaire();
    }

    private ProgressBar createLoadingProgressBar(){
        ProgressBar progressBar = new ProgressBar(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(6, 6, 6, 6);
        progressBar.setLayoutParams(params);
        progressBar.setVisibility(VISIBLE);
        return progressBar;
    }

    @Override
    public void onQuestionnaireLoaded(Questionnaire questionnaire) {
        questionnairePresenter.createModels(questionnaire);
    }

    @Override
    public void onQuestionModelsLoaded(LinkedList<QuestionGroupModel> questionModelList) {
        if(questionModelList.isEmpty()){
            TextView textView = new TextView(context);
            textView.setText(getResources().getString(R.string.thanksFulfill));
            textView.setTextSize(30);
            addView(textView);
        }
        else
            initView(questionModelList.getFirst());
    }

    private void initView(QuestionGroupModel questionGroupModel){
        this.questionGroupModel = questionGroupModel;
        addView(createQuestionGroupTitleTextView(questionGroupModel.getTitle()));
        QuestionnairePanelViewFactory questionnairePanelViewFactory = new QuestionnairePanelViewFactory(context);
        for (QuestionModel questionModel : questionGroupModel){
            Log.d(TAG,questionModel.toString());
            addView(questionnairePanelViewFactory.createLinearLayout(questionModel));
        }
        progressBar.setVisibility(GONE);
    }

    private TextView createQuestionGroupTitleTextView(String title){
        TextView questionGroupTitleTxt = new TextView(context);
        questionGroupTitleTxt.setText(title);
        questionGroupTitleTxt.setTextColor(Color.BLUE);
        questionGroupTitleTxt.setTextSize(20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, convertDpToPixel(15));
        params.gravity = Gravity.CENTER_HORIZONTAL;
        questionGroupTitleTxt.setLayoutParams(params);
        return questionGroupTitleTxt;
    }

    public boolean checkAndCommitResponse(){
        progressBar.setVisibility(VISIBLE);
        if(checkFulfill()){
            for(QuestionModel questionModel: questionGroupModel){
                if(questionModel.getQuestionType() == QuestionType.RADIOGROUP)
                    questionnairePresenter.commitResponse((RadioGroupQuestion) questionModel);
                else
                    questionnairePresenter.commitResponse((FillingQuestion) questionModel);
            }
            return true;
        }
        progressBar.setVisibility(GONE);
        return false;
    }

    private boolean checkFulfill(){
        for(QuestionModel questionModel: questionGroupModel){
            if(questionModel.getQuestionType() == QuestionType.RADIOGROUP){
                if (!checkRadioGroup((RadioGroupQuestion) questionModel))
                    return false;
            }
            else if (questionModel.getQuestionType() == QuestionType.FILLING){
                if(!checkFilling((FillingQuestion) questionModel))
                    return false;
            }
        }
        return true;
    }

    private boolean checkRadioGroup(RadioGroupQuestion radioGroupQuestion){
        for(RadioGroupQuestion.Option option : radioGroupQuestion)
            if(option.getValue())
                return true;
    return false;
    }

    private boolean checkFilling(FillingQuestion fillingQuestion){
        Log.d(TAG,"checkFilling Answer :" + fillingQuestion.getAnswer());
        return fillingQuestion.getAnswer() != null && fillingQuestion.getAnswer().length() != 0;
    }

    @Override
    public void onAnswerCommittingSuccessfully(Answer answer, QuestionModel question) {
        Log.d(TAG,"Answer Committing Successfully : " + answer.toString());
        if (++commitSuccessfullyAmount == questionGroupModel.getQuestionModels().size())
        {
            progressBar.setVisibility(GONE);
            questionnairePresenter.earnMoneyFromFillingTheQuestionnaire(EARNED_MONEY);
            questionnaireDialogFragment.dismiss();
        }
    }

    @Override
    public void onAnswerCommittingError(Answer answer, QuestionModel question) {
        Log.d(TAG,"Answer Committing Error the error answer is : " + answer.toString());
    }

    @Override
    public void onError(Exception err) {
        Log.e(TAG, "error occurs in my questionnaire panel.", err);
        progressBar.setVisibility(GONE);
    }

    public int convertPixelToDp(float px){
        return (int) (px / getDensity());
    }

    public int convertDpToPixel(float dp){
        return (int) (dp * getDensity());
    }

    public float getDensity(){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }
}
