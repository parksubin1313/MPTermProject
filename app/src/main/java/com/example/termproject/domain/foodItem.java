package com.example.termproject.domain;

import androidx.annotation.NonNull;

public class foodItem {

    String storageWay;
    String name;
    String dd;

    //생성
    public foodItem(String storageWay, String name, String dd)
    {
        this.storageWay = storageWay;
        this.name = name;
        this.dd = dd;
    }

    //변수에 접근
    public String getStorageWay()
    {
        return storageWay;
    }

    public void setStorageWay(String storageWay)
    {
        this.storageWay = storageWay;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDD()
    {
        return dd;
    }

    public void setDD(String dd)
    {
        this.dd = dd;
    }

    @NonNull
    @Override
    public String toString() {
        return "D - " + dd;
    }
}

