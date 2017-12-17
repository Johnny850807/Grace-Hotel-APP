package com.ood.clean.waterball.gracehotel.Model.domain;

import android.support.annotation.DrawableRes;

import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * The facade pattern instance which deals with all the domain models.
 */
public class GraceHotel {
    private static @DrawableRes int overlookMapDrawableId;
    private static ItemArranger itemArranger;

    /**
     * @param timeBlocks the time blocks contain all sprite's showing times
     * @param maximumConstraints the Hash Map should contain the info about each sprite name with its maximum constraint
     * @return pools
     */
    public static List<TimeItemPool> createOneHourItemPools(List<TimeBlock> timeBlocks,
                                                             HashMap<SpriteName, Integer> maximumConstraints){
        HashMap<Date, TimeItemPool> hourPoolMap = new HashMap<>();
        for (TimeBlock timeBlock : timeBlocks)
        {
            Date date = timeBlock.getDate();
            if (!hourPoolMap.containsKey(date))
                hourPoolMap.put(date, new TimeItemPool(date, maximumConstraints));
            hourPoolMap.get(date).put(timeBlock.getAcceptSpriteName(), timeBlock.getSpriteProxy());
        }
        return new ArrayList<>(hourPoolMap.values());
    }

    /**
     * Create the shuffled time blocks based on some constraints.
     * @param startDate the date the first created time block references.
     * @param duration the duration of the time block
     * @param timeCount the iterating times through the duration from the startDate. Usually if the base duration is an hour, then you
     *                  should assign 24 * days as a timeCount.
     * @param maximumConstraints the Hash Map should contain the info about each sprite name with its maximum constraint
     * @return shuffled time blocks
     */
    public static List<TimeBlock> createTimeBlocks(Date startDate,
                                                   long duration,
                                                   int timeCount,
                                                   HashMap<SpriteName, Integer> maximumConstraints){
        List<TimeBlock> timeBlocks = new ArrayList<>();
        Collection<SpriteName> spriteNames = maximumConstraints.keySet();

        for (int i = 0 ; i < timeCount ; i ++ )
        {
            Date date = (Date) startDate.clone();
            date.setTime(date.getTime() + duration*i);
            for (SpriteName spriteName : spriteNames)
                timeBlocks.addAll(bindingTimeBlocks(date, duration, spriteName, maximumConstraints.get(spriteName)));
        }

        return timeBlocks;
    }

    /**
     * Create a specific amount of time blocks with specific acceptSpriteName, startDate and duration.
     */
    public static List<TimeBlock> bindingTimeBlocks(Date startDate,
                                                     long duration,
                                                     SpriteName acceptSpriteName,
                                                     int amount){
        List<TimeBlock> timeBlocks = new ArrayList<>();
        for (int i = 0 ; i < amount ; i ++)
            timeBlocks.add(new TimeBlock(acceptSpriteName, startDate, duration));
        return timeBlocks;
    }
}
