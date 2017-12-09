package com.ood.clean.waterball.gracehotel.Model.datamodel;


import java.util.Date;

public class ItemShowUpRecord {
    private Date datetime;
    private SpriteName itemName;

    public ItemShowUpRecord(Date datetime, SpriteName itemName) {
        this.datetime = datetime;
        this.itemName = itemName;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public SpriteName getItemName() {
        return itemName;
    }

    public void setItemName(SpriteName itemName) {
        this.itemName = itemName;
    }
}
