package com.ood.clean.waterball.gracehotel.Model.domain;


import com.ood.clean.waterball.gracehotel.Model.datamodel.ItemShowUpRecord;

import java.util.ArrayList;
import java.util.List;

//TODO
/**
 * NoPainNoGain means there will be very few treasures contain actually a reward fragment.
 * The algorithm can be separated into 2 processes:
 *      1. determine each item amount to put based on the durationDays
 *      2. randomly arrange each items with a proper start date and an end date.
 *
 *  In both processes the rule below:
 *      1. money item - only 120/day, at most 10/hr
 *      2. treasure item - only 4/day, at most 1/3hr
 *
 *  Each item has a rule below:
 *      1. money actually worth 5 ~ 20$ on each.
 *      2. the probability of the reward fragment occurring is 1/4 treasure,
 *          means four treasures might have one contain reward fragment.
 */
public class NoPainNoGain implements ItemArranger{
    private static final int MONEY_IN_DAY = 120;
    private static final int TREASURE_IN_DAY = 4;
    private int moneyAmount;
    private int treasureAmount;
    @Override
    public List<ItemShowUpRecord> arrange(int durationDays) {
        this.moneyAmount = durationDays * MONEY_IN_DAY;
        this.treasureAmount = durationDays * TREASURE_IN_DAY;

        List<ItemShowUpRecord> allRecords = new ArrayList<>();
        allRecords.addAll(createMoneyItemShowUpRecords());
        allRecords.addAll(createTreasureItemShowUpRecords());
        return allRecords;
    }

    private List<ItemShowUpRecord> createMoneyItemShowUpRecords() {
        List<ItemShowUpRecord> moneyRecords = new ArrayList<>();

        return moneyRecords;
    }

    private List<ItemShowUpRecord> createTreasureItemShowUpRecords() {
        List<ItemShowUpRecord> treasureRecords = new ArrayList<>();

        return treasureRecords;
    }
}
