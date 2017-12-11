package com.ood.clean.waterball.gracehotel.Model;

import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;

import java.io.IOException;

public interface QuestionnaireRepository {

	public Questionnaire getLastedQuestionnaire(String language) throws IOException;

	public Answer fillAnswer(Answer ans) throws IOException;

}
