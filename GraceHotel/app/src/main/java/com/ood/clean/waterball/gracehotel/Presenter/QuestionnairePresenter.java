package com.ood.clean.waterball.gracehotel.Presenter;


import android.util.Log;

import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRepository;
import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionGroupModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.domain.QAModelFactory;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Question;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionGroup;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.QuestionnaireView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuestionnairePresenter {
    private ThreadExecutor threadExecutor;
    private QuestionnaireView questionnaireView;
    private User user;
    private String language;
    private UserRepository userRepository;
    private QuestionnaireRepository questionnaireRepository;

    public QuestionnairePresenter(ThreadExecutor threadExecutor,
                                  User user,
                                  UserRepository userRepository,
                                  QuestionnaireRepository questionnaireRepository,
                                  String language) {
        this.userRepository = userRepository;
        this.threadExecutor = threadExecutor;
        this.user = user;
        this.questionnaireRepository = questionnaireRepository;
        this.language = language;
    }

    public void loadQuestionnaire(){
        threadExecutor.execute(()-> {
            try {
                Questionnaire questionnaire = questionnaireRepository.getLastedQuestionnaire(language);
                threadExecutor.executeOnMainThread(()->questionnaireView.onQuestionnaireLoaded(questionnaire));
            } catch (IOException e) {
                questionnaireView.onError(e);
            }
        });
    }

    /**
     * Create the model from the questionnaire. When the model has been created, it will be send to the callback method
         * onQuestionModelsLoaded(model). In this method, first we filter off any question groups which the user has filled and
     * commited to the repository. And reserve others for creating the models.
     * @param questionnaire
     */
    public void createModels(Questionnaire questionnaire){
        threadExecutor.execute(()->{
            List<QuestionGroup> unfilledQuestionGroups = getUnfilledQuestionGroups(questionnaire);
            LinkedList<QuestionGroupModel> questionGroupList = makeQuestionGroupModels(unfilledQuestionGroups);
            threadExecutor.executeOnMainThread(()->questionnaireView.onQuestionModelsLoaded(questionGroupList));
        });
    }

    private LinkedList<QuestionGroupModel> makeQuestionGroupModels(List<QuestionGroup> questionGroups){
        LinkedList<QuestionGroupModel> questionGroupList = new LinkedList<>();
        for (QuestionGroup questionGroup : questionGroups)  //convert each question group into a model
        {
            List<QuestionModel> questionModels = new ArrayList<>();
            for(Question question : questionGroup.getQuestions())  // convert each question into a model
                questionModels.add(QAModelFactory.createQuestionModel(questionGroup, question));

            questionGroupList.add(new QuestionGroupModel(questionGroup.getId(), questionGroup.getPartNumber(),
                    questionGroup.getName(), questionModels));
        }
        return questionGroupList;
    }

    private List<QuestionGroup> getUnfilledQuestionGroups(Questionnaire questionnaire){
        List<QuestionGroup> unfilledQuestionGroups = new ArrayList<QuestionGroup>();
        for (QuestionGroup questionGroup : questionnaire)
            if (!user.hasFilled(questionGroup))
                unfilledQuestionGroups.add(questionGroup);
        return unfilledQuestionGroups;
    }

    public void setQuestionnaireView(QuestionnaireView questionnaireView) {
        this.questionnaireView = questionnaireView;
    }

    public void commitResponse(RadioGroupQuestion questionModel){
        Answer answer = QAModelFactory.createAnswerFeedback(user.getDeviceId(),
                                                            user.getRoomNumber(),
                                                            "test",
                                                            questionModel);
        Log.d("Tag","Print Answer :" +answer.toString());
        commitAnswer(answer, questionModel);
    }

    public void commitResponse(FillingQuestion questionModel){
        Answer answer = QAModelFactory.createAnswerFeedback(user.getDeviceId(),
                                                            user.getRoomNumber(),
                                                            "test",
                                                            questionModel);
        commitAnswer(answer, questionModel);
    }

    private void commitAnswer(final Answer answer, final QuestionModel questionModel){
        threadExecutor.execute(()->{
                try {
                    Answer newAnswer = questionnaireRepository.fillAnswer(answer);
                    userRepository.addFilledQuestionGroupId(user, questionModel.getQuestionGroupId());
                    threadExecutor.executeOnMainThread(()->questionnaireView.onAnswerCommittingSuccessfully(newAnswer, questionModel));
                } catch (IOException | RuntimeException e) {
                    threadExecutor.executeOnMainThread(()-> questionnaireView.onAnswerCommittingError(answer, questionModel));
                } catch (Exception e){
                    threadExecutor.executeOnMainThread(()->questionnaireView.onError(e));
                }
        });
    }
}
