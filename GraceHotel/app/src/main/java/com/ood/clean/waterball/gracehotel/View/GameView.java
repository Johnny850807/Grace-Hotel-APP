package com.ood.clean.waterball.gracehotel.View;


import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;

import java.util.List;

public interface GameView extends GameParentView{

	public void onGameStatusUpdated(Background background);

	public void onShowQuestionnaire(List<QuestionModel> questionModels);

	public void onNoGameItemsFound();
}
