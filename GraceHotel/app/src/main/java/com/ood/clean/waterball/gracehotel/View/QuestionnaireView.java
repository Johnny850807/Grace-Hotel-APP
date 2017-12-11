package com.ood.clean.waterball.gracehotel.View;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;


public interface QuestionnaireView {

    public void onAnswerCommittingSuccessfully(Answer answer, QuestionModel question);
    public void onAnswerCommittingError(QuestionModel question);
    public void onError(Exception err);
    public void onQuestionnaireLoaded(Questionnaire questionnaire);
}
