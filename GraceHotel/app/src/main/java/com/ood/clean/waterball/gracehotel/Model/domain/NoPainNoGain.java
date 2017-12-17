package com.ood.clean.waterball.gracehotel.Model.domain;


import com.ood.clean.waterball.gracehotel.Model.datamodel.SpriteName;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.MoneyProxy;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.SpriteProxy;
import com.ood.clean.waterball.gracehotel.Model.sprite.event.TreasureProxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * NoPainNoGain means there will be very few treasures contain actually a reward fragment.
 * The algorithm can be separated into 2 processes:
 *      1. determine each item amount to put based on the durationDays
 *      2. create items according to the amount
 *      3. create time blocks according to the maximum constraints on each item,
 *      each time block means to contain a specific sprite in a specific duration time.
 *      4. shuffle the time blocks and the items
 *      2. for each item, put to each time block sequentially until no items left.
 *      The time block amount should be greater than the item amount.
 *
 *  In both processes the rule below:
 *      1. money item - only 60/day, at most 10/hr
 *      2. treasure item - only 4/day, at most 1/3hr
 *
 *  Each item has a rule below:
 *      1. money actually worth 5 ~ 20$ on each.
 *      2. the probability of the reward fragment occurring is 25% each treasure,
 *          means four treasures might have one contain reward fragment.
 */
public class NoPainNoGain implements ItemArranger{
    private static final HashMap<SpriteName, Integer> MAXIMUM_CONSTRAINTS;  //maximum amount per 'day'
    private static final int MIN_MONEY_VALUE = 10; //each money value in 10~20
    private static final int MAX_MONEY_VALUE = 20;
    private static final float TREASURE_REWARD_PROBABILITY = 0.25f;

    static  //constraints assign
    {
        MAXIMUM_CONSTRAINTS = new HashMap<>();
        MAXIMUM_CONSTRAINTS.put(SpriteName.MONEY, 120);
        MAXIMUM_CONSTRAINTS.put(SpriteName.TREASURE, 4);
    }

    @Override
    public List<TimeItemPool> arrange(int durationDays) {
        List<TimeItemPool> allRecords = new ArrayList<>();

        int timeBlockAmount = 24 * durationDays;

        List<SpriteProxy> spriteProxies = createSpriteProxies(durationDays);
        LinkedList<TimeBlock> timeBlocks = new LinkedList<>(GraceHotel.createTimeBlocks(new Date(), TimeUnit.HOURS.toMillis(1),
                timeBlockAmount, MAXIMUM_CONSTRAINTS));

        if (timeBlocks.size() > spriteProxies.size())
            throw new IllegalStateException("The time block amount should be greater than the item amount.");

        Collections.shuffle(spriteProxies);
        Collections.shuffle(timeBlocks);

        for(SpriteProxy spriteProxy : spriteProxies)
            timeBlocks.pollFirst().setSpriteProxy(spriteProxy);

        return allRecords;
    }

    private static List<SpriteProxy> createSpriteProxies(int durationDays){
        List<SpriteProxy> spriteProxies = new ArrayList<>();

        for(SpriteName spriteName : MAXIMUM_CONSTRAINTS.keySet())
        {
            int amount = MAXIMUM_CONSTRAINTS.get(spriteName) * durationDays;
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

    private static List<MoneyProxy> createMoneyProxies(int amount){
        Random random = new Random();
        List<MoneyProxy> proxies = new ArrayList<>();
        for (int i = 0 ; i < amount ; i ++)
            proxies.add(new MoneyProxy(random.nextInt(MAX_MONEY_VALUE - MIN_MONEY_VALUE + 1) + MIN_MONEY_VALUE));
        return proxies;
    }

    private static List<TreasureProxy> createTreasureProxies(int amount){
        int rewardAmount = (int) (amount * TREASURE_REWARD_PROBABILITY);

        List<TreasureProxy> proxies = new ArrayList<>();
        for (int i = 0 ; i < amount ; i ++)
        {
            boolean hasReward = rewardAmount-- > 0;
            proxies.add(new TreasureProxy(hasReward));
        }
        return proxies;
    }
}
