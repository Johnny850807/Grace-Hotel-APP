package com.ood.clean.waterball.gracehotel;


import com.google.gson.Gson;
import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.domain.NoPainNoGain;
import com.ood.clean.waterball.gracehotel.Model.domain.TimeBlock;
import com.ood.clean.waterball.gracehotel.Model.domain.TimeItemPool;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class TestItemArranger {
    private static Gson gson = new Gson();
    private NoPainNoGain itemArranger;
    private HashMap<SpriteName, Integer> constraints = new HashMap<>();
    private TreeSet<TimeItemPool> pools;


    @Before
    public void before(){
        constraints.put(SpriteName.MONEY, 8);
        constraints.put(SpriteName.TREASURE, 4);
        itemArranger = new NoPainNoGain();
        pools = new TreeSet<>(itemArranger.arrange(1));
    }

    //@Test time arranged successfully, each time interval is one hour.
    public void testDateArrangement(){
        for(TimeItemPool timeItemPool : pools)
            System.out.println(timeItemPool.getStartTime() + ",");

    }

    //@Test succeeded: [{"hasReward":false},{"hasReward":false},{"money":15},{"money":11},{"money":15},{"money":19},{"money":13},{"money":14},{"money":13},{"money":16}]
    public void testSpritesCreation(){
        List<SpriteProxy> spriteProxies = itemArranger.createSpriteProxies(1);
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
        Map<Date, List<SpriteProxy>> poolMap = TimeItemPool.poolsToMap(new TreeMap<Date, List<SpriteProxy>>(),
                new ArrayList<>(pools));
        for(Date date : poolMap.keySet())
        {
            System.out.print("Date " + date.toString() + ", SpriteProxies: ");
            for(SpriteProxy proxy : poolMap.get(date))
                if (proxy != null)
                    System.out.print(proxy + ",");
            System.out.println();
        }
    }
}
