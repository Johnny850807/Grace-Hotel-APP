package com.ood.clean.waterball.gracehotel.View;


import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;

import java.util.List;

public interface GameView {

	public void onGameStatusUpdated(Sprite background, List<Sprite> sprites);

	public void onShowQuestionnaire(List<QuestionModel> questionModels);

}
