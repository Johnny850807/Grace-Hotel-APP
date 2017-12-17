package com.ood.clean.waterball.gracehotel.Model.datamodel;


import com.ood.clean.waterball.gracehotel.Model.sprite.Sprite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * One-Hour-Pool is an object expected to contain the items means item will be showing up during a specific hour,
 * as well as manages the maximum amount constraints.
 */
public class OneHourItemPool {
    private Date startTime;
    private final Map<SpriteName, Integer> MAXIMUM_CONSTRAINTS;
    private HashMap<SpriteName, List<Sprite>> sprites = new HashMap<>();

    public OneHourItemPool(Date startTime, Map<SpriteName, Integer> maximum_constraints) {
        MAXIMUM_CONSTRAINTS = maximum_constraints;
        this.startTime = startTime;
    }

    /**
     * @param sprite
     * @return is the pool full
     */
    public synchronized boolean canPutIn(Sprite sprite){
        int maximum = MAXIMUM_CONSTRAINTS.get(sprite.getSpriteName());
        return sprites.get(sprite.getSpriteName()).size() >= maximum;
    }

    /**
     * @param sprite
     * @return is the pool full
     */
    public synchronized boolean put(Sprite sprite){
        if (canPutIn(sprite))
        {
            if (!sprites.containsKey(sprite.getSpriteName()))
                sprites.put(sprite.getSpriteName(), new ArrayList<>());
            sprites.get(sprite.getSpriteName()).add(sprite);
            return true;
        }
        else
            return false;
    }

    public Date getStartTime() {
        return startTime;
    }

    public List<Sprite> getSprites(){
        Collection<List<Sprite>> eachKindSprites = sprites.values();
        List<Sprite> allSprites = new ArrayList<>();
        eachKindSprites.forEach(allSprites::addAll);
        return allSprites;
    }
}
