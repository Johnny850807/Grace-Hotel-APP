package com.ood.clean.waterball.gracehotel.Model.sprite.event;


import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

public abstract class BaseSpriteProxy implements SpriteProxy {
    private final SpriteName spriteName;

    protected BaseSpriteProxy(SpriteName spriteName) {
        this.spriteName = spriteName;
    }

    @Override
    public SpriteName getSpriteName() {
        return spriteName;
    }
}
