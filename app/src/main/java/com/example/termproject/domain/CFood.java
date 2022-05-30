package com.example.termproject.domain;

import java.io.Serializable;
import java.util.Date;

public class CFood implements Serializable {
    public String fName;
    public Date fDate;
    public boolean sold;
    public String place;
    public String Detail;
    //판매자 UID
    public String UID;

    public CFood(String fName, Date fDate, boolean sold, String place, String detail, String UID) {
        this.fName = fName;
        this.fDate = fDate;
        this.sold = sold;
        this.place = place;
        Detail = detail;
        this.UID = UID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
