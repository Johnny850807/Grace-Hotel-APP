package com.ood.clean.waterball.gracehotel.View;


import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.proxy.TreasureProxy;

public interface GameView extends GameParentView{

	public void onGameStatusUpdated(Background background);

	/**
	 * his method will be invoke if the user has enough money to open the treasure.s
	 * @param price the price of openning the treasure
	 */
	public void onMoneyNotEnoughError(User user, int price);

	/**
	 * This method will be invoke if the user has enough money to open the treasure.
	 * ask the user whether to pay to open the treasure,
	 * if the user select yes, ask the presenter to open it.
	 */
	public void onAskingWhetherToOpenTreasure(User user, TreasureProxy.OpenTreasurePresenter openTreasurePresenter);

	/**
	 * @param hasReward if the treasure has a reward.
	 */
	public void onShowingRewardInTreasure(boolean hasReward);

	public void onNoGameItemsFound();
}
