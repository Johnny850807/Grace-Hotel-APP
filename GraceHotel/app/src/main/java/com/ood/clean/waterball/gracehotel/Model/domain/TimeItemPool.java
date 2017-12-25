package com.ood.clean.waterball.gracehotel.Model.domain;


import android.support.annotation.NonNull;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.BaseSpriteProxy;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * TimeItemPool is an object expected to contain the items means item will be showing up during a specific hour,
 * as well as manages the maximum amount constraints.
 */
public class TimeItemPool implements Comparable<TimeItemPool>, Serializable{
    private Date startTime;
    private long duration;
    private final Map<SpriteName, Integer> MAXIMUM_CONSTRAINTS;
    private HashMap<SpriteName, List<BaseSpriteProxy>> sprites = new HashMap<>();

    public TimeItemPool(Date startTime,
                        long duration,
                        Map<SpriteName,Integer> maximum_constraints) {
        MAXIMUM_CONSTRAINTS = maximum_constraints;
        this.duration = duration;
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
    public synchronized boolean put(BaseSpriteProxy spriteProxy){
        if (canPutIn(spriteProxy.getSpriteName()))
        {
            sprites.get(spriteProxy.getSpriteName()).add(spriteProxy);
            return true;
        }
        else
            throw new IllegalArgumentException("Not supported spriteName.");
    }

    public Date getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }

    public List<SpriteProxy> getSpriteProxys(){
        Collection<List<BaseSpriteProxy>> eachKindSprites = sprites.values();
        List<SpriteProxy> allSprites = new ArrayList<>();
        for (List<BaseSpriteProxy> sprites : eachKindSprites)
            allSprites.addAll(sprites);
        return allSprites;
    }


    public void removeItem(BaseSpriteProxy itemProxy){
        List<BaseSpriteProxy> proxies = sprites.get(itemProxy.getSpriteName());
        if (proxies.contains(itemProxy))
            proxies.remove(itemProxy);
    }

    public boolean hasTreasure(){
        return sprites.get(SpriteName.TREASURE) != null && sprites.get(SpriteName.TREASURE).size() > 0;
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

    public static String asString(List<TimeItemPool> timeItemPools){
        StringBuilder strb = new StringBuilder();
        Map<Date, List<SpriteProxy>> poolMap = TimeItemPool.poolsToMap(new TreeMap<Date, List<SpriteProxy>>(),
                new ArrayList<>(timeItemPools));
        for(Date date : poolMap.keySet())
        {
            strb.append(String.format("Date %s, SpriteProxies: ", date.toString()));
            for(SpriteProxy proxy : poolMap.get(date))
                strb.append(String.format("%s,", proxy));
            strb.append("\n");
        }
        return strb.toString();
    }
}
