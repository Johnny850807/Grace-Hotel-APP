package com.ood.clean.waterball.gracehotel.View;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;

import java.util.List;
import java.util.Stack;

public interface QuestionnaireView {
    /**
     * @param answer the created answer of the question.
     * @param question successfully committed question.
     */
    public void onAnswerCommittingSuccessfully(Answer answer, QuestionModel question);

    /**
     * @param question which question had a error occurs when committed.
     */
    public void onAnswerCommittingError(QuestionModel question);

    /**
     * @param err if any exception occurs. Might be IOException stands for a internet error.
     */
    public void onError(Exception err);

    /**
     * @param questionnaire the loaded questionnaire.
     */
    public void onQuestionnaireLoaded(Questionnaire questionnaire);

    /**
     * @param questionModels the question group stack, each list item in the stack stands for one question group,
     *  there will be certain questions in each list.
     */
    public void onQuestionModelsLoaded(Stack<List<QuestionModel>> questionModels);
}
