package com.ood.clean.waterball.gracehotel;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.domain.NoPainNoGain;
import com.ood.clean.waterball.gracehotel.Model.domain.TimeBlock;
import com.ood.clean.waterball.gracehotel.Model.domain.TimeItemPool;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.BaseSpriteProxy;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class TestItemArranger {
    private static Gson gson = MyApplication.getDeGson();
    private NoPainNoGain itemArranger;
    private HashMap<SpriteName, Integer> constraints = new HashMap<>();
    private TreeSet<TimeItemPool> pools;


    @Before
    public void before(){
        constraints.put(SpriteName.MONEY, 8);
        constraints.put(SpriteName.TREASURE, 4);
        itemArranger = new NoPainNoGain();
        pools = new TreeSet<>(itemArranger.arrange(3));
    }

    //@Test time arranged successfully, each time interval is one hour.
    public void testDateArrangement(){
        for(TimeItemPool timeItemPool : pools)
            System.out.println(timeItemPool.getStartTime() + ",");

    }

    //@Test succeeded: [{"hasReward":false},{"hasReward":false},{"money":15},{"money":11},{"money":15},{"money":19},{"money":13},{"money":14},{"money":13},{"money":16}]
    public void testSpritesCreation(){
        List<BaseSpriteProxy> spriteProxies = itemArranger.createSpriteProxies(1);
        String json = gson.toJson(spriteProxies);
        System.out.println(json);
    }

    //@Test  //succeeded [{"acceptSpriteName":"MONEY","date":"Dec 17, 2017 9:36:11 PM","duration":3600000},{"acceptSpriteName":"MONEY","date":"Dec 17, 2017 9:36:11 PM","duration":3600000},{"acceptSpriteName":"TREASURE","date":"Dec 17, 2017 9:36:11 PM","duration":3600000},{"acceptSpriteName":"MONEY","date":"Dec 17, 2017 10:36:11 PM","duration":3600000}
    public void testTimeBlockCreation(){  //stay for one day
        HashMap<SpriteName, Integer> constraints = new HashMap<>();
        constraints.put(SpriteName.MONEY, 2);
        constraints.put(SpriteName.TREASURE, 1);
        List<TimeBlock> timeBlocks = itemArranger.createTimeBlocks(new Date(), TimeUnit.HOURS.toMillis(1), 5, constraints);
        String json = gson.toJson(timeBlocks);
        System.out.println(json);
    }


    @Test
    public void testTimePools(){
        System.out.println(TimeItemPool.asString(new ArrayList<>(pools)));

        String json = new Gson().toJson(pools);
        System.out.println(json);
        Type type = new TypeToken<ArrayList<TimeItemPool>>(){}.getType();
        List<TimeItemPool> dePools = gson.fromJson(json, type);
        System.out.println("Break point here to watch if the dePools has a correct deserialization on each spriteProxy.");
    }
}
