package com.ood.clean.waterball.gracehotel.Model;

/**
 * Created by user on 2017/12/31.
 */

public class Information {
    private String title;
    private String introduction;
    private int imageSrc;
    public Information(String title, String introduction, int imageSrc){
        this.title = title;
        this.introduction = introduction;
        this.imageSrc = imageSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }
}
