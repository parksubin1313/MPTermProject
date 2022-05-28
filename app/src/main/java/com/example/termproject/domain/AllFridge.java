package com.example.termproject.domain;

import java.util.ArrayList;

//대분류 User -> 개인 -> myfridge 안에 있는 냉장고 이름들
//내가 등록한 냉장고 이름만 있음
public class AllFridge {
    private ArrayList<String> fridge_name;

    public AllFridge(){}

    public ArrayList<String> getFridge_name() {
        return fridge_name;
    }

    public void setFridge_name(ArrayList<String> fridge_name) {
        this.fridge_name = fridge_name;
    }
}
