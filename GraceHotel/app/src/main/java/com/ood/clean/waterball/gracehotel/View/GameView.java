package com.ood.clean.waterball.gracehotel.View;


import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;

import java.util.List;

public interface GameView {

	public void onGameStatusUpdated(Background background);

	public void onShowQuestionnaire(List<QuestionModel> questionModels);

	public void onMoneyEarned(Sprite sprite, int money);
}
