package com.ood.clean.waterball.gracehotel.Presenter;

import android.util.Log;

import com.ood.clean.waterball.gracehotel.Model.datamodel.QuestionModel;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Model.sprite.SpritePrototypeFactory;
import com.ood.clean.waterball.gracehotel.View.GameView;

import java.util.LinkedList;
import java.util.List;

public class GamePresenter {
	private static final String TAG = "GamePresenter";
	private GameView gameView;
	private boolean running;
	private SpritePrototypeFactory prototypeFactory = SpritePrototypeFactory.getInstance();
	private Sprite background;
	private List<Sprite> gameItems;
	private List<Delta> unhandleScrollRequesnt = new LinkedList<Delta>();

	public GamePresenter(){}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void gameStart() {
		background = prototypeFactory.createSprite(SpriteName.BACKGROUND);
		running = true;
		gameCycleThread.start();
	}

	private Thread gameCycleThread = new Thread(){
		@Override
		public void run() {
			try {
				while (running)
				{
					gameView.onGameStatusUpdated(background, gameItems);
					Thread.sleep(35);
				}
			} catch (InterruptedException e) {
				Log.d(TAG, "Game cycle thread interrupted.");
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
		unhandleScrollRequesnt.add(new Delta(dx, dy));
	}

	public void executeItemEffect(Sprite gameItem) {

	}

	public void fillQuestion(QuestionModel questionModel) {

	}



	private static class Delta{
		int x;
		int y;

		public Delta(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
