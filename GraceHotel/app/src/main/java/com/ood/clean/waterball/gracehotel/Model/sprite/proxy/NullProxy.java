package com.ood.clean.waterball.gracehotel.Model.sprite.proxy;

import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.GameView;


public class NullProxy extends BaseSpriteProxy {

    public NullProxy(SpriteName spriteName) {
        super(spriteName);
    }

    @Override
    public void execute(Background background, Sprite moneySprite, ThreadExecutor threadExecutor, User user, UserRepository userRepository, GameView gameView) {

    }
}
