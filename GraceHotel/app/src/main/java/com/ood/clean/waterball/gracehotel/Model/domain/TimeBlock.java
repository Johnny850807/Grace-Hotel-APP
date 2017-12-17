package com.ood.clean.waterball.gracehotel.Model.domain;


import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;

import java.util.Date;


public class TimeBlock {
    private final SpriteName acceptSpriteName;  // the sprite that this time block will only accept.
    private final Date date;
    private final long duration;
    private SpriteProxy spriteProxy;

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

    public SpriteProxy getSpriteProxy() {
        return spriteProxy;
    }

    public void setSpriteProxy(SpriteProxy spriteProxy) {
        this.spriteProxy = spriteProxy;
    }
}
