package com.ood.clean.waterball.gracehotel.View;


import com.ood.clean.waterball.gracehotel.Model.datamodel.AnswerModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;

public interface GameView {

	public abstract void onGameStatusUpdated(Sprite background, Sprite[] sprites);

	public abstract void onShowQuestionaire(QuestionModel[] qts);

	public abstract void onAnswerFillingSuccessfully(AnswerModel ans);

}
