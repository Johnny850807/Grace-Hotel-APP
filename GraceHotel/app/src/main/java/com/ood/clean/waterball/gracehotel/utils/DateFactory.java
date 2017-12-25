package com.ood.clean.waterball.gracehotel.utils;


import java.util.Date;

public class DateFactory {

    /**
     * @param hourInDay 0~23
     * @return
     */
    private static Date createOneHour(int year, int day, int hourInDay){
        if (hourInDay < 0 || hourInDay > 23)
            throw new IllegalArgumentException("Hour should be in the range 0~23");
        Date date = new Date();
        return date;
    }
}
