package com.ood.clean.waterball.gracehotel.Presenter;


import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.domain.QAModelFactory;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.View.QuestionnaireView;

import java.io.IOException;

public class QuestionnairePresenter {
    private QuestionnaireView questionnaireView;
    private User user;
    private String language;
    private QuestionnaireRepository questionnaireRepository;

    public QuestionnairePresenter(User user,
                                  QuestionnaireRepository questionnaireRepository,
                                  String language) {
        this.user = user;
        this.questionnaireRepository = questionnaireRepository;
        this.language = language;
    }

    public void loadQuestionnaire(){

    }

    public void setQuestionnaireView(QuestionnaireView questionnaireView) {
        this.questionnaireView = questionnaireView;
    }

    public void commitResponse(RadioGroupQuestion questionModel){
        Answer answer = QAModelFactory.createAnswerFeedback(user.getDeviceId(),
                                                            user.getRoomNumber(),
                                                            user.getEmail(),
                                                            questionModel);
        commitAnswer(answer, questionModel);

    }

    public void commitResponse(FillingQuestion questionModel){
        Answer answer = QAModelFactory.createAnswerFeedback(user.getDeviceId(),
                                                            user.getRoomNumber(),
                                                            user.getEmail(),
                                                            questionModel);

        commitAnswer(answer, questionModel);
    }

    private void commitAnswer(Answer answer, QuestionModel questionModel){
        try {
            answer = questionnaireRepository.fillAnswer(answer);
            questionnaireView.onAnswerCommittingSuccessfully(answer, questionModel);
        } catch (IOException e) {
            questionnaireView.onAnswerCommittingError(questionModel, e);
        }
    }

}
