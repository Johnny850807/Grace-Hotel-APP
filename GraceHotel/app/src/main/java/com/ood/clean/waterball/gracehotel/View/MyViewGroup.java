package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;

import java.util.List;
import java.util.Stack;



public class MyViewGroup extends LinearLayout implements QuestionnaireView{
    private QuestionnairePresenter questionnairePresenter;
    private TextView textView;
    public MyViewGroup(Context context, QuestionnairePresenter questionnairePresenter) {
        super(context);
        this.questionnairePresenter = questionnairePresenter;
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

    }

    @Override
    public void onQuestionModelsLoaded(Stack<List<QuestionModel>> questionModels) {

    }
}
