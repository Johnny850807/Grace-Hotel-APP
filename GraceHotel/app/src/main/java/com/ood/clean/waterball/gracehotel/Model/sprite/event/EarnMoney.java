package com.ood.clean.waterball.gracehotel.Model.sprite.event;


import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.GameView;

public class EarnMoney implements EventHandler{
    private Sprite moneySprite;
    private int money;

    public EarnMoney(int money) {
        this.moneySprite = moneySprite;
        this.money = money;
    }

    @Override
    public void execute(Sprite moneySprite, ThreadExecutor threadExecutor, User user, UserRepository userRepository, GameView gameView) {
        threadExecutor.execute(()->{
            userRepository.addMoney(user, money);
            threadExecutor.executeOnMainThread(()->gameView.onMoneyEarned(moneySprite, money));
        });
    }
}
