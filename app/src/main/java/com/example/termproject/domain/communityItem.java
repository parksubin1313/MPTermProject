package com.example.termproject.domain;

import androidx.annotation.NonNull;

public class communityItem {

    String location;
    String name;
    String description;
    String date;

    //생성
    public communityItem(String location, String name, String description, String date)
    {
        this.location = location;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    //변수에 접근
    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDescription() { return description;}

    public void setDescription(String description) { this.description =description;}

}
