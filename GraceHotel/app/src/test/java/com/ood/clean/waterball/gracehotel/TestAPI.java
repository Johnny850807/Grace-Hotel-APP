package com.ood.clean.waterball.gracehotel;

import com.ood.clean.waterball.gracehotel.Model.QuestionnaireRetrofitRepository;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;

import org.junit.Test;


public class TestAPI {
    @Test
    public void apis() throws Exception {
        QuestionnaireRetrofitRepository repository = new QuestionnaireRetrofitRepository(MyApplication.getRetrofit());
        Questionnaire questionnaire = repository.getLastedQuestionnaire("CH");
        Answer answer = repository.fillAnswer(new Answer(1, "answer","testFromDevice","406","device@device.com"));
        String stub = "a";
    }
}