package com.ood.clean.waterball.gracehotel.Presenter;


import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.domain.QAModelFactory;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.QuestionnaireView;

import java.io.IOException;

public class QuestionnairePresenter {
    private ThreadExecutor threadExecutor;
    private QuestionnaireView questionnaireView;
    private User user;
    private String language;
    private QuestionnaireRepository questionnaireRepository;

    public QuestionnairePresenter(ThreadExecutor threadExecutor,
                                  User user,
                                  QuestionnaireRepository questionnaireRepository,
                                  String language) {
        this.threadExecutor = threadExecutor;
        this.user = user;
        this.questionnaireRepository = questionnaireRepository;
        this.language = language;
    }

    public void loadQuestionnaire(){
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Questionnaire questionnaire = questionnaireRepository.getLastedQuestionnaire(language);
                    sendQuestionnaireToView(questionnaire);
                } catch (IOException e) {
                    questionnaireView.onError(e);
                }
            }
        });
    }

    private void sendQuestionnaireToView(final Questionnaire questionnaire){
        threadExecutor.executeOnMainThread(new Runnable() {
            @Override
            public void run() {
                questionnaireView.onQuestionnaireLoaded(questionnaire);
            }
        });
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

    private void commitAnswer(final Answer answer, final QuestionModel questionModel){
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Answer newAnswer = questionnaireRepository.fillAnswer(answer);
                    notifySuccessToView(newAnswer, questionModel);
                } catch (IOException e) {
                    questionnaireView.onAnswerCommittingError(questionModel);
                    questionnaireView.onError(e);
                }
            }
        });
    }

    private void notifySuccessToView(final Answer answer, final QuestionModel questionModel){
        threadExecutor.executeOnMainThread(new Runnable() {
            @Override
            public void run() {
                questionnaireView.onAnswerCommittingSuccessfully(answer, questionModel);
            }
        });
    }
}
