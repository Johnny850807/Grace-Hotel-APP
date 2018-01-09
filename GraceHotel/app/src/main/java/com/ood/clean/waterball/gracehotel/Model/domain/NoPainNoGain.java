package com.ood.clean.waterball.gracehotel.Model.domain;


import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.proxy.BaseSpriteProxy;
import com.ood.clean.waterball.gracehotel.Model.sprite.proxy.MoneyProxy;
import com.ood.clean.waterball.gracehotel.Model.sprite.proxy.TreasureProxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * NoPainNoGain means there will be very few treasures contain actually a reward fragment.
 * The algorithm can be separated into processes:
 *      1. determine each item amount based on the durationDays
 *      2. create items according to the amount
 *      3. create time blocks according to the maximum block constraints on each item,
 *      each time block means to contain a specific sprite in a specific duration time.
 *      4. shuffle the time blocks and the items
 *      5. for each item, put to each time block sequentially until no items left.

 *
 *  Each item has a rule below:
 *      1. money actually worth 5 ~ 20$ on each.
 *      2. the probability of the reward fragment occurring is 25% each treasure,
 *          means four treasures might have one contain reward fragment.
 */
public class NoPainNoGain implements ItemArranger{
    private static HashMap<SpriteName, Integer> DEFAULT_MAXIMUM_CONSTRAINTS_DAY;
    private static HashMap<SpriteName, Integer> DEFAULT_MAXIMUM_CONSTRAINTS_BLOCK;
    private final HashMap<SpriteName, Integer> MAXIMUM_CONSTRAINTS_DAY;  //maximum amount per 'day'
    private final HashMap<SpriteName, Integer> MAXIMUM_CONSTRAINTS_BLOCK;  //maximum amount per 'block'

    /**
     * each money is worth 10~20$
     */
    private static final int MIN_MONEY_VALUE = 4; //each money is worth 10~20$
    private static final int MAX_MONEY_VALUE = 13;

    /**
     * the probability of the reward fragment occurring is 25% each treasure,
     means four treasures might have one contain reward fragment.
     */
    private static final float TREASURE_REWARD_PROBABILITY = 0.25f;


     /* In both processes has a default rule below:
         1. money item - only 60/day, at most 8/hr
         2. treasure item - only 4/day, at most 1/hr
     */
    static
    {
        DEFAULT_MAXIMUM_CONSTRAINTS_DAY = new HashMap<>();
        DEFAULT_MAXIMUM_CONSTRAINTS_DAY.put(SpriteName.MONEY, 170);
        DEFAULT_MAXIMUM_CONSTRAINTS_DAY.put(SpriteName.TREASURE, 3);

        DEFAULT_MAXIMUM_CONSTRAINTS_BLOCK = new HashMap<>();
        DEFAULT_MAXIMUM_CONSTRAINTS_BLOCK.put(SpriteName.MONEY, 8);
        DEFAULT_MAXIMUM_CONSTRAINTS_BLOCK.put(SpriteName.TREASURE, 2);
    }

    public NoPainNoGain() {
        MAXIMUM_CONSTRAINTS_DAY = DEFAULT_MAXIMUM_CONSTRAINTS_DAY;
        MAXIMUM_CONSTRAINTS_BLOCK = DEFAULT_MAXIMUM_CONSTRAINTS_BLOCK;
    }

    public NoPainNoGain(HashMap<SpriteName, Integer> maximumConstraintsDay,
                        HashMap<SpriteName, Integer> maximum_constraints_block) {
        this.MAXIMUM_CONSTRAINTS_DAY = maximumConstraintsDay;
        MAXIMUM_CONSTRAINTS_BLOCK = maximum_constraints_block;
    }
    @Override
    public List<TimeItemPool> arrange(int durationDays) {
        int timeBlockAmount = 24 * durationDays;
        List<BaseSpriteProxy> spriteProxies = createSpriteProxies(durationDays);

        List<TimeBlock> timeBlocks = createTimeBlocks(new Date(), TimeUnit.HOURS.toMillis(1),
                timeBlockAmount, MAXIMUM_CONSTRAINTS_BLOCK);

        Collections.shuffle(spriteProxies);
        Collections.shuffle(timeBlocks);

        putProxiesIntoBlocksSequentially(spriteProxies, timeBlocks);

        return createOneHourItemPools(timeBlocks, MAXIMUM_CONSTRAINTS_BLOCK);
    }

    private static void putProxiesIntoBlocksSequentially(List<BaseSpriteProxy> spriteProxies, List<TimeBlock> timeBlocks){
        LinkedList<TimeBlock> timeBlockList = new LinkedList<>(timeBlocks);
        for(BaseSpriteProxy spriteProxy : spriteProxies)
        {
            if (timeBlockList.isEmpty())
                break;
            for (TimeBlock timeBlock : timeBlockList)
                if (timeBlock.getAcceptSpriteName() == spriteProxy.getSpriteName())
                {
                    timeBlock.setSpriteProxy(spriteProxy);
                    timeBlockList.remove(timeBlock);
                    break;
                }
        }
    }

    /**
     * Create the shuffled time blocks based on some constraints.
     * @param startDate the date the first created time block references.
     * @param duration the duration of the time block
     * @param timeCount the iterating times through the duration from the startDate. Usually if the base duration is an hour, then you
     *                  should assign 24 * days as a timeCount.
     * @param maximumConstraintsBlock the Hash Map should contain the info about each sprite name with its maximum constraint
     * @return shuffled time blocks
     */
    public List<TimeBlock> createTimeBlocks(Date startDate,
                                                   long duration,
                                                   int timeCount,
                                                   HashMap<SpriteName, Integer> maximumConstraintsBlock){
        List<TimeBlock> timeBlocks = new ArrayList<>();
        Collection<SpriteName> spriteNames = maximumConstraintsBlock.keySet();

        for (int i = 0 ; i < timeCount ; i ++ )
        {
            Date date = (Date) startDate.clone();
            date.setTime(date.getTime() + duration*i);
            for (SpriteName spriteName : spriteNames)
                timeBlocks.addAll(bindingTimeBlocks(date, duration, spriteName, maximumConstraintsBlock.get(spriteName)));
        }

        return timeBlocks;
    }

    /**
     * Create a specific amount of time blocks with specific acceptSpriteName, startDate and duration.
     */
    public List<TimeBlock> bindingTimeBlocks(Date startDate,
                                                    long duration,
                                                    SpriteName acceptSpriteName,
                                                    int amount){
        List<TimeBlock> timeBlocks = new ArrayList<>();
        for (int i = 0 ; i < amount ; i ++)
            timeBlocks.add(new TimeBlock(acceptSpriteName, startDate, duration));
        return timeBlocks;
    }

    public List<BaseSpriteProxy> createSpriteProxies(int durationDays){
        List<BaseSpriteProxy> spriteProxies = new ArrayList<>();

        for(SpriteName spriteName : MAXIMUM_CONSTRAINTS_DAY.keySet())
        {
            int amount = MAXIMUM_CONSTRAINTS_DAY.get(spriteName) * durationDays;
            switch (spriteName)
            {
                case MONEY:
                    spriteProxies.addAll(createMoneyProxies(amount));
                    break;
                case TREASURE:
                    spriteProxies.addAll(createTreasureProxies(amount));
                    break;
            }
        }
        return spriteProxies;
    }

    public List<MoneyProxy> createMoneyProxies(int amount){
        Random random = new Random();
        List<MoneyProxy> proxies = new ArrayList<>();
        for (int i = 0 ; i < amount ; i ++)
            proxies.add(new MoneyProxy(random.nextInt(MAX_MONEY_VALUE - MIN_MONEY_VALUE + 1) + MIN_MONEY_VALUE));
        return proxies;
    }

    public static List<TreasureProxy> createTreasureProxies(int amount){
        Random random = new Random();
        int rewardAmount = (int) (amount * TREASURE_REWARD_PROBABILITY);

        List<TreasureProxy> proxies = new ArrayList<>();
        for (int i = 0 ; i < amount ; i ++)
        {
            boolean hasReward = rewardAmount > 0 && random.nextInt(100) > TREASURE_REWARD_PROBABILITY*100;
            if (hasReward)
                rewardAmount --;
            proxies.add(new TreasureProxy(hasReward));
        }
        return proxies;
    }

    /**
     * @param timeBlocks the time blocks contain all sprite's showing times
     * @param maximumConstraintsBlock the Hash Map should contain the info about each sprite name with its maximum constraint
     * @return pools
     */
    public static List<TimeItemPool> createOneHourItemPools(List<TimeBlock> timeBlocks,
                                                            HashMap<SpriteName, Integer> maximumConstraintsBlock){
        HashMap<Date, TimeItemPool> hourPoolMap = new HashMap<>();
        for (TimeBlock timeBlock : timeBlocks)
        {
            Date date = timeBlock.getDate();
            if (!hourPoolMap.containsKey(date))
                hourPoolMap.put(date, new TimeItemPool(date, timeBlock.getDuration(), maximumConstraintsBlock));
            if (timeBlock.hasSpriteProxy())
                hourPoolMap.get(date).put(timeBlock.getSpriteProxy());
        }
        return new ArrayList<>(hourPoolMap.values());
    }
}
