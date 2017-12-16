package com.ood.clean.waterball.gracehotel.Model.datamodel;


import java.util.Date;

public class ItemShowUpRecord {
    private Date startDateTime;
    private Date endDateTime;
    private SpriteName itemName;
    private Object data;

    public ItemShowUpRecord(Date startDateTime, Date endDateTime, SpriteName itemName, Object data) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.itemName = itemName;
        this.data = data;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public SpriteName getItemName() {
        return itemName;
    }

    public void setItemName(SpriteName itemName) {
        this.itemName = itemName;
    }

}
