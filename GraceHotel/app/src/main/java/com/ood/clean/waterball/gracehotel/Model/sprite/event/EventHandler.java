package com.ood.clean.waterball.gracehotel.Model.sprite.event;


import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.GameView;

/**
 * The handler used for responding to the event such as when the player click the sprite.
 * This is an implementation of the strategy pattern, it's expected to respond to the current view,
 * so it needs the view instance and some model objects as parameters to the execute method.
 */
public interface EventHandler {
    /**
     * Handle the event, make sure this method will be invoked on the main thread.
     */
    public void execute(Background background, Sprite moneySprite, ThreadExecutor threadExecutor, User user, UserRepository userRepository, GameView gameView);
}
