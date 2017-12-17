package com.ood.clean.waterball.gracehotel.Model.datamodel;


import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;

import java.util.Date;

public class TimeBlock {
    private final SpriteName acceptSpriteName;
    private final Date date;
    private final int duration;
    private Sprite sprite;

    public TimeBlock(SpriteName acceptSpriteName, Date date, int duration) {
        this.acceptSpriteName = acceptSpriteName;
        this.date = date;
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public boolean accept(Sprite sprite){
        return this.acceptSpriteName == sprite.getSpriteName();
    }

    public boolean accept(SpriteName spriteName){
        return this.acceptSpriteName == spriteName;
    }

    public Sprite getSprite() {
        return sprite;
    }

    /**
     * @param sprite
     * @return the sprite is accepted.
     */
    public boolean setSprite(Sprite sprite) {
        if (accept(sprite))
        {
            this.sprite = sprite;
            return true;
        }
        return false;
    }
}
