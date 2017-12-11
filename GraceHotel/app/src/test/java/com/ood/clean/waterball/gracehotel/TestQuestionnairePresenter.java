package com.ood.clean.waterball.gracehotel;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;
import com.ood.clean.waterball.gracehotel.Presenter.QuestionnairePresenter;
import com.ood.clean.waterball.gracehotel.View.QuestionnaireView;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;


public class TestQuestionnairePresenter implements QuestionnaireView{
    QuestionnairePresenter presenter;
    User user;
    boolean testing = true;
    boolean success = true;

    @Before
    public void setup(){
        user = new User("304", "testFromDevice", "test@gmail.com");
        presenter = new QuestionnairePresenter(new TestThreadExecutor(),
                user, MyApplication.getQuestionnaireRepository(),
                MyApplication.getLanguage());
        presenter.setQuestionnaireView(this);
    }

    @Test
    public void test(){
        presenter.loadQuestionnaire();
        while(testing){}
        assertTrue(success);
    }

    @Override
    public void onAnswerCommittingSuccessfully(Answer answer, QuestionModel question) {
        testing = false;
    }

    @Override
    public void onAnswerCommittingError(QuestionModel question) {
        fail();
    }

    @Override
    public void onError(Exception err) {
        fail();
    }

    @Override
    public void onQuestionnaireLoaded(Questionnaire questionnaire) {
        success = "葛瑞絲初版問卷".equals(questionnaire.getName());
        testing = false;
    }
}
