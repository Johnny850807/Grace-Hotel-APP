package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionGroupModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import java.util.LinkedList;
import java.util.List;


public class MyQuestionnairePanel extends LinearLayout implements QuestionnaireView {
    private static final String TAG = "MyQuestionnairePanel";
    private QuestionnairePresenter questionnairePresenter;
    private Context context;
    private QuestionGroupModel questionGroupModel;

    public MyQuestionnairePanel(Context context, QuestionnairePresenter questionnairePresenter) {
        super(context);
        this.context = context;
        this.questionnairePresenter = questionnairePresenter;
        this.setOrientation(VERTICAL);

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(parms);
        questionnairePresenter.setQuestionnaireView(this);
        questionnairePresenter.loadQuestionnaire();
    }

    private void initView(QuestionGroupModel questionGroupModel){
        this.questionGroupModel = questionGroupModel;
        LinearLayoutFactory linearLayoutFactory = new LinearLayoutFactory(context);
        for (QuestionModel questionModel : questionGroupModel){
            Log.d(TAG,questionModel.toString());
            addView(linearLayoutFactory.createLinearLayout(questionModel));

        }
    }
    public boolean checkAndCommitRespone(){
        if( checkFulfill() ){
            for(QuestionModel questionModel: questionGroupModel){
                if(questionModel.getQuestionType() == QuestionType.RADIOGROUP)
                    questionnairePresenter.commitResponse((RadioGroupQuestion) questionModel);
                else
                    questionnairePresenter.commitResponse((FillingQuestion) questionModel);
            }
            return true;
        }
        return false;
    }
    private boolean checkFulfill(){
        boolean checkFulfill = false;
        for(QuestionModel questionModel: questionGroupModel){
            if(questionModel.getQuestionType() == QuestionType.RADIOGROUP)
                checkFulfill = checkRadioGroup((RadioGroupQuestion) questionModel);
            else
                checkFulfill = checkFilling((FillingQuestion) questionModel);
        }
        return checkFulfill;
    }
    private boolean checkRadioGroup(RadioGroupQuestion radioGroupQuestion){
        for(RadioGroupQuestion.Option option : radioGroupQuestion){
            if(option.getValue() == true)
                return true;
        }
    return false;
    }
    private boolean checkFilling(FillingQuestion fillingQuestion){
        if(fillingQuestion.getAnswer().isEmpty())
            return false;
        return true;
    }
    @Override
    public void onAnswerCommittingSuccessfully(Answer answer, QuestionModel question) {
    }

    @Override
    public void onAnswerCommittingError(QuestionModel question) {

    }

    @Override
    public void onError(Exception err) {

    }

    @Override
    public void onQuestionnaireLoaded(Questionnaire questionnaire) {
        questionnairePresenter.createModels(questionnaire);
    }

    @Override
    public void onQuestionModelsLoaded(LinkedList<QuestionGroupModel> questionModelList) {
        initView(questionModelList.getFirst());
    }

}
