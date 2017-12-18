package com.ood.clean.waterball.gracehotel.Model.sprite.event;


import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.GameView;

public class TreasureProxy extends BaseSpriteProxy  {
    private boolean hasReward;

    public TreasureProxy(SpriteName spriteName, boolean hasReward) {
        super(spriteName);
        this.hasReward = hasReward;
    }

    public TreasureProxy(SpriteName spriteName) {
        super(spriteName);
    }


    @Override
    public void execute(Background background, Sprite moneySprite, ThreadExecutor threadExecutor, User user, UserRepository userRepository, GameView gameView) {

    }

    public void setHasReward(boolean hasReward) {
        this.hasReward = hasReward;
    }

    public boolean isHasReward() {
        return hasReward;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + hasReward + "}";
    }
}
