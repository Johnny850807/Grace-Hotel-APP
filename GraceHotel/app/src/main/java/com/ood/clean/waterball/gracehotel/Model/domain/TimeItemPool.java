package com.ood.clean.waterball.gracehotel.Model.domain;


import android.support.annotation.NonNull;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TimeItemPool is an object expected to contain the items means item will be showing up during a specific hour,
 * as well as manages the maximum amount constraints.
 */
public class TimeItemPool implements Comparable<TimeItemPool>{
    private Date startTime;
    private final Map<SpriteName, Integer> MAXIMUM_CONSTRAINTS;
    private HashMap<SpriteName, List<SpriteProxy>> sprites = new HashMap<>();

    public TimeItemPool(Date startTime, Map<SpriteName, Integer> maximum_constraints) {
        MAXIMUM_CONSTRAINTS = maximum_constraints;
        this.startTime = startTime;
    }

    /**
     * @return is the pool full
     */
    public synchronized boolean canPutIn(SpriteName spriteName){
        if (!sprites.containsKey(spriteName))
            sprites.put(spriteName, new ArrayList<>());
        return sprites.get(spriteName).size() < MAXIMUM_CONSTRAINTS.get(spriteName);
    }

    /**
     * @return is the pool full
     */
    public synchronized boolean put(SpriteName spriteName, SpriteProxy spriteProxy){
        if (canPutIn(spriteName))
        {
            sprites.get(spriteName).add(spriteProxy);
            return true;
        }
        else
            throw new IllegalArgumentException("Not supported spriteName.");
    }

    public Date getStartTime() {
        return startTime;
    }

    public List<SpriteProxy> getSpriteProxys(){
        Collection<List<SpriteProxy>> eachKindSprites = sprites.values();
        List<SpriteProxy> allSprites = new ArrayList<>();
        eachKindSprites.forEach(allSprites::addAll);
        return allSprites;
    }

    @Override
    public int compareTo(@NonNull TimeItemPool timeItemPool) {
        return getStartTime().compareTo(timeItemPool.getStartTime());
    }

    public static Map<Date, List<SpriteProxy>> poolsToMap(Map<Date, List<SpriteProxy>> map, List<TimeItemPool> timeItemPools){
        for(TimeItemPool pool : timeItemPools)
        {
            if(!map.containsKey(pool.getStartTime()))
                map.put(pool.getStartTime(), new ArrayList<>());
            map.get(pool.getStartTime()).addAll(pool.getSpriteProxys());
        }
        return map;
    }
}
