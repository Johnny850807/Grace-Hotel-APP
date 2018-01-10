package com.ood.clean.waterball.gracehotel.Model.sprite.proxy;


import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

import java.io.Serializable;

public abstract class BaseSpriteProxy implements SpriteProxy, Serializable {
    protected SpriteName spriteName;

    protected BaseSpriteProxy(SpriteName spriteName) {
        this.spriteName = spriteName;
    }

    @Override
    public SpriteName getSpriteName() {
        return spriteName;
    }
}
