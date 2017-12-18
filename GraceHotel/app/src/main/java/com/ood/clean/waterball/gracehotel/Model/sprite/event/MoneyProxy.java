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

public class MoneyProxy extends BaseSpriteProxy {
    private int money;

    public MoneyProxy(int money) {
        super(SpriteName.MONEY);
        this.money = money;
    }

    public MoneyProxy() {
        super(SpriteName.MONEY);
    }

    @Override
    public void execute(Background background,
                        Sprite moneySprite,
                        ThreadExecutor threadExecutor,
                        User user,
                        UserRepository userRepository,
                        GameView gameView) {
        user.removeSpriteInCurrentPools(this);
        userRepository.addMoney(user, money);
        addFadingTextEffectAroundTheMoney(background, moneySprite);
        background.removeGameItem(moneySprite);
        threadExecutor.executeOnMainThread(() -> gameView.onMoneyUpdated(moneySprite, user.getMoney()));
    }

    private void addFadingTextEffectAroundTheMoney(Background background, Sprite moneySprite){
        FadingTextEffect textEffect = (FadingTextEffect) SpritePrototypeFactory.getInstance().createSprite(SpriteName.FADING_TEXT_EFFECT);
        textEffect.setX(moneySprite.getX() + moneySprite.getWidth());
        textEffect.setY(moneySprite.getY());
        textEffect.setBackground(background);
        textEffect.setText("+ " + money + "$");
        background.addGameItem(textEffect);
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

