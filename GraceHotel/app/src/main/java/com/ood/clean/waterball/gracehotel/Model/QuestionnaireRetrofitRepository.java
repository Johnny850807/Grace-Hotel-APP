package com.ood.clean.waterball.gracehotel.Model;


import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class QuestionnaireRetrofitRepository implements QuestionnaireRepository{
    private QuestionnaireAPI api;

    public QuestionnaireRetrofitRepository(Retrofit retrofit){
        api = retrofit.create(QuestionnaireAPI.class);
    }

    @Override
    public Questionnaire getLastedQuestionnaire(String language) throws IOException {
        Response<Questionnaire> response = api.getLastedQuestionnaire(language).execute();
        validateResponse(response);
        return response.body();
    }

    @Override
    public Answer fillAnswer(Answer ans) throws IOException {
        Response<Answer> response = api.postAnswer(ans.getQuestionId(), ans.getDeviceUID(), ans.getRoomNumber(),
                ans.getEmail(), ans.getResponses()).execute();
        validateResponse(response);
        return response.body();
    }

    private void validateResponse(Response<?> response){
        if (response.code() == 400)
            throw new RuntimeException("BadRequest Response");
    }

    private interface QuestionnaireAPI{
        String RESOURCE = "questionnaire";

        @GET(RESOURCE + "/lasted")
        public Call<Questionnaire> getLastedQuestionnaire(@Query("language") String language);

        @FormUrlEncoded
        @POST(RESOURCE + "/answer")
        public Call<Answer> postAnswer(@Field("questionId") int questionId,
                                              @Field("deviceUID") String deviceUID,
                                              @Field("roomNumber") String roomNumber,
                                              @Field("email") String email,
                                              @Field("responses") String responses);
    }
}
