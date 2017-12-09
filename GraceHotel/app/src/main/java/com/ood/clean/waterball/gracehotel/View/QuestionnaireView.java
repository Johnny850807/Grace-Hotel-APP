package com.ood.clean.waterball.gracehotel.View;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;



public interface QuestionnaireView {

    public void onAnswerFillingSuccessfully(QuestionModel filledQuestion);
    public void onQuestionnaireCommitedSuccessfully();
    public void onQuestionnaireCommitedFailed();
}
