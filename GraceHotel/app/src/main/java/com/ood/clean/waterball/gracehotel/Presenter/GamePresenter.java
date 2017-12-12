package com.ood.clean.waterball.gracehotel.Presenter;

import android.util.Log;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Model.sprite.SpritePrototypeFactory;
import com.ood.clean.waterball.gracehotel.View.GameView;

import java.util.List;

public class GamePresenter {
	private static final String TAG = "GamePresenter";
	private GameView gameView;
	private boolean running;
	private SpritePrototypeFactory prototypeFactory = SpritePrototypeFactory.getInstance();
	private Background background;
	private List<Sprite> gameItems;

	public GamePresenter(){}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void gameStart() {
		background = (Background) prototypeFactory.createSprite(SpriteName.BACKGROUND);
		testGameItem(); //TODO
		running = true;
		gameCycleThread.start();
	}

	private void testGameItem(){
		Sprite money1 = prototypeFactory.createSprite(SpriteName.MONEY);
		money1.setX(150);
		money1.setY(200);
		Sprite money2 = prototypeFactory.createSprite(SpriteName.MONEY);
		money2.setX(3250);
		money2.setY(1000);
		Sprite money3 = prototypeFactory.createSprite(SpriteName.MONEY);
		money3.setX(2000);
		money3.setY(300);
		Sprite treasure = prototypeFactory.createSprite(SpriteName.TREASURE);
		treasure.setX(-4200);
		treasure.setY(540);
		background.addGameItem(money1);
		background.addGameItem(money3);
		background.addGameItem(money2);
		background.addGameItem(treasure);
	}

	private Thread gameCycleThread = new Thread(){
		@Override
		public void run() {
			try {
				while (running)
				{
					background.update();
					gameView.onGameStatusUpdated(background);
					Thread.sleep(25);
				}
			} catch (InterruptedException e) {
				Log.w(TAG, "Game cycle thread interrupted.");
			}
		}
	};

	public Thread getGameCycleThread() {
		return gameCycleThread;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void scrollBackground(int dx, int dy) {
		background.tryMove(dx*-1, dy*-1);
		gameView.onGameStatusUpdated(background);
	}

	public void touch(int x, int y) {

	}

	public void fillQuestion(QuestionModel questionModel) {

	}




}
