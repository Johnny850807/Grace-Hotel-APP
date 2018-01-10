package com.ood.clean.waterball.gracehotel.Model.domain;


import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.proxy.BaseSpriteProxy;

import java.util.Date;


public class TimeBlock {
    private final SpriteName acceptSpriteName;  // the sprite that this time block will only accept.
    private final Date date;
    private final long duration;
    private BaseSpriteProxy spriteProxy;

    public TimeBlock(SpriteName acceptSpriteName, Date date, long duration) {
        this.acceptSpriteName = acceptSpriteName;
        this.date = date;
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public long getDuration() {
        return duration;
    }

    public boolean accept(SpriteName spriteName){
        return this.acceptSpriteName == spriteName;
    }

    public SpriteName getAcceptSpriteName() {
        return acceptSpriteName;
    }

    public BaseSpriteProxy getSpriteProxy() {
        return spriteProxy;
    }

    /**
     * @return has the block had a sprite injected.
     */
    public boolean hasSpriteProxy(){
        return getSpriteProxy() != null;
    }

    public void setSpriteProxy(BaseSpriteProxy spriteProxy) {
        if (spriteProxy.getSpriteName() != getAcceptSpriteName())
            throw new IllegalArgumentException("The spriteProxy is not accepted in the timeblock.");
        this.spriteProxy = spriteProxy;
    }
}
