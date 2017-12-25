package com.ood.clean.waterball.gracehotel.Model.sprite.event;


import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.FadingTextEffect;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Model.sprite.SpritePrototypeFactory;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.GameView;

public class TreasureProxy extends BaseSpriteProxy  {
    public static final int PRICE = 50;
    private boolean hasReward;

    public TreasureProxy(boolean hasReward) {
        super(SpriteName.TREASURE);
        this.hasReward = hasReward;
    }

    public TreasureProxy() {
        super(SpriteName.TREASURE);
    }


    @Override
    public void execute(Background background, Sprite sprite, ThreadExecutor threadExecutor, User user, UserRepository userRepository, GameView gameView) {
        threadExecutor.executeOnMainThread(()-> {
            if (user.getMoney() < PRICE)
                gameView.onMoneyNotEnoughError(user, PRICE);
            else
                gameView.onAskingWhetherToOpenTreasure(user, new OpenTreasurePresenter(hasReward, background,
                        sprite, threadExecutor, user, userRepository, gameView));
        });
    }


    public void setHasReward(boolean hasReward) {
        this.hasReward = hasReward;
    }

    public boolean hasReward() {
        return hasReward;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + hasReward + "}";
    }

    public class OpenTreasurePresenter{
        private Background background;
        private User user;
        private Sprite sprite;
        private UserRepository userRepository;
        private ThreadExecutor threadExecutor;
        private boolean hasReward;
        private GameView gameView;

        public OpenTreasurePresenter(boolean hasReward, Background background, Sprite sprite, ThreadExecutor threadExecutor, User user, UserRepository userRepository, GameView gameView) {
            this.hasReward = hasReward;
            this.background = background;
            this.user = user;
            this.sprite = sprite;
            this.userRepository = userRepository;
            this.threadExecutor = threadExecutor;
            this.gameView = gameView;
        }

        /**
         * invoke this method to open the treasure of the holding treasure sprite which is on clicked.
         */
        public void openTheTreasure(){
            if (user.getMoney() < PRICE)
                throw new IllegalStateException("The user has no enough money to open the treasure.");

            threadExecutor.execute(()->{
                try{
                    userRepository.addMoney(user, -1 * PRICE);  //cost
                    user.removeSpriteInCurrentPools(TreasureProxy.this);
                    background.removeGameItem(sprite);
                    addFadingTextEffect(background, sprite);
                    if (hasReward)
                        userRepository.addReward(user);
                    threadExecutor.executeOnMainThread(()->{
                        gameView.onMoneyUpdated(sprite, user.getMoney());
                        gameView.onShowingRewardInTreasure(hasReward);
                    });
                }catch (Exception err){
                    err.printStackTrace();
                }
            });
        }

        private void addFadingTextEffect(Background background, Sprite treasureSprite){
            FadingTextEffect textEffect = (FadingTextEffect) SpritePrototypeFactory.getInstance().createSprite(SpriteName.FADING_TEXT_EFFECT);
            textEffect.affect(background, treasureSprite, "- " + PRICE + "$");
            background.addGameItem(textEffect);
        }
    }
}
