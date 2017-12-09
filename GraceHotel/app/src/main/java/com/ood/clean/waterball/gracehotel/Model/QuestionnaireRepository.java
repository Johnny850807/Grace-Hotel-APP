package com.ood.clean.waterball.gracehotel.Model;

import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Questionnaire;

public interface QuestionnaireRepository {

	public abstract Questionnaire getQuestionnaire();

	public abstract void fillAnswer(Answer ans);

}
