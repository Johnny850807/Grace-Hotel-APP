package com.ood.clean.waterball.gracehotel.View;

import android.content.Context;
import android.widget.LinearLayout;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionGroupModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;

import java.util.LinkedList;


public class MyQuestionnairePanel extends LinearLayout implements QuestionnaireView {
    private QuestionnairePresenter questionnairePresenter;
    private Context context;
    public MyQuestionnairePanel(Context context, QuestionnairePresenter questionnairePresenter) {
        super(context);
        this.context = context;
        this.questionnairePresenter = questionnairePresenter;
        questionnairePresenter.setQuestionnaireView(this);
        questionnairePresenter.loadQuestionnaire();
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

    }

}
