package com.example.termproject.domain;

import java.io.Serializable;
import java.util.Date;

public class Food implements Serializable {
    public String fName;
    public Date fDate;
    public int storageWay;

    public Food(String fName, Date fDate, int storageWay) {
        this.fName = fName;
        this.fDate = fDate;
        this.storageWay = storageWay;
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

    public int getStorageWay() {
        return storageWay;
    }

    public void setStorageWay(int storageWay) {
        this.storageWay = storageWay;
    }
}
