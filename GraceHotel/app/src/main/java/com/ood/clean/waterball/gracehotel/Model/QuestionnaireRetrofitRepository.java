package com.ood.clean.waterball.gracehotel.Model;


import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;

import retrofit2.Retrofit;

public class QuestionnaireRetrofitRepository implements QuestionnaireRepository{
    private QuestionnaireAPI api;

    public QuestionnaireRetrofitRepository(Retrofit retrofit){
        api = retrofit.create(QuestionnaireAPI.class);
    }

    @Override
    public Questionnaire getQuestionnaire() {
        return null;
    }

    @Override
    public void fillAnswer(Answer ans) {

    }


    public interface QuestionnaireAPI{

    }
}
