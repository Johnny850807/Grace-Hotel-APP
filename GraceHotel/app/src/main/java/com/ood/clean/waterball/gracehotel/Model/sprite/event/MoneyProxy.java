package com.ood.clean.waterball.gracehotel.Model.sprite.event;


import com.ood.clean.waterball.gracehotel.Model.UserRepository;
import com.ood.clean.waterball.gracehotel.Model.datamodel.User;
import com.ood.clean.waterball.gracehotel.Model.sprite.Background;
import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;
import com.ood.clean.waterball.gracehotel.Threading.ThreadExecutor;
import com.ood.clean.waterball.gracehotel.View.GameView;

public class MoneyProxy implements SpriteProxy {
    private int money;

    public MoneyProxy(){}

    public MoneyProxy(int money) {
        this.money = money;
    }

    @Override
    public void execute(Background background,
                        Sprite moneySprite,
                        ThreadExecutor threadExecutor,
                        User user,
                        UserRepository userRepository,
                        GameView gameView) {
        userRepository.addMoney(user, money);
        background.removeGameItem(moneySprite);
        threadExecutor.executeOnMainThread(() -> gameView.onMoneyEarned(moneySprite, money));
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

