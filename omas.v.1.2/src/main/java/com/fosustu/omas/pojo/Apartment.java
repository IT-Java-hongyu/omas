package com.fosustu.omas.pojo;

public class Apartment {
    private String apartId;

    private String apartName;

    private String tag0;

    private String tag1;

    private String tag2;

    private String tag3;

    public String getApartId() {
        return apartId;
    }

    public void setApartId(String apartId) {
        this.apartId = apartId == null ? null : apartId.trim();
    }

    public String getApartName() {
        return apartName;
    }

    public void setApartName(String apartName) {
        this.apartName = apartName == null ? null : apartName.trim();
    }

    public String getTag0() {
        return tag0;
    }

    public void setTag0(String tag0) {
        this.tag0 = tag0;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }
}