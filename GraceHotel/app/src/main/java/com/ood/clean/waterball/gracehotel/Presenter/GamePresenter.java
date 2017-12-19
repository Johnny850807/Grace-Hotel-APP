package com.ood.clean.waterball.gracehotel.Presenter;

import android.util.Log;

import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.domain.TimeItemPool;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Model.sprite.SpritePrototypeFactory;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.GameView;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter {
	private static final String TAG = "GamePresenter";
	private GameView gameView;
	private boolean running;
	private SpritePrototypeFactory prototypeFactory = SpritePrototypeFactory.getInstance();
	private User user;
	private UserRepository userRepository;
	private ThreadExecutor threadExecutor;
	private Background background;

	public GamePresenter(User user,
						 UserRepository userRepository,
						 ThreadExecutor threadExecutor){
		this.user = user;
		this.userRepository = userRepository;
		this.threadExecutor = threadExecutor;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void gameStart() {
		background = (Background) prototypeFactory.createSprite(SpriteName.BACKGROUND);
		arrangeItems();
        if (background.size() == 0)
            gameView.onNoGameItemsFound();
		running = true;
		gameCycleThread.start();
	}

	private void arrangeItems() {
		List<TimeItemPool> timeItemPools = user.getCurrentTimeItemPools();
		for (TimeItemPool timeItemPool : timeItemPools)
			background.arrangeItemRandomly(createSpriteFromTimePool(timeItemPool));
	}

	private List<Sprite> createSpriteFromTimePool(TimeItemPool timeItemPool){
		List<Sprite> sprites = new ArrayList<>();
		List<SpriteProxy> proxies = timeItemPool.getSpriteProxys();
		for (SpriteProxy proxy : proxies)
		{
			Sprite sprite = prototypeFactory.createSprite(proxy.getSpriteName());
			sprite.setSpriteProxy(proxy);
			sprites.add(sprite);
		}
		return sprites;
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
		Sprite money4 = prototypeFactory.createSprite(SpriteName.MONEY);
		money3.setX(-4500);
		money3.setY(150);
		Sprite money5 = prototypeFactory.createSprite(SpriteName.MONEY);
		money3.setX(-2200);
		money3.setY(850);
		Sprite treasure = prototypeFactory.createSprite(SpriteName.TREASURE);
		treasure.setX(-4200);
		treasure.setY(540);
		background.addGameItem(money1);
		background.addGameItem(money3);
		background.addGameItem(money2);
		background.addGameItem(money4);
		background.addGameItem(money5);
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
		background.tryMove(dx*-2, dy*-2);
		gameView.onGameStatusUpdated(background);
	}

	public synchronized void touchScreen(int x, int y) {
		for (Sprite sprite : background)
			if (sprite.isTouched(x, y)) {
				Log.d(TAG, "Sprite " + sprite.getSpriteName() + " touched.");
				threadExecutor.execute(() -> sprite.getSpriteProxy().execute(background, sprite,
						threadExecutor, user, userRepository, gameView));
				break;
			}
	}

}
