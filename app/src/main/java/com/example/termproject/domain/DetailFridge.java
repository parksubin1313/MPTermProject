package com.example.termproject.domain;

import java.util.ArrayList;

//대분류 냉장고 밑에 속한 individual 한 모든 냉장고
//즉, 하나하나의 식품과 자신을 공유하고 있는 유저 정보를 가지고 있음
public class DetailFridge {
    public ArrayList<String> userList;
    public ArrayList<Food> fFoodlist;

    public DetailFridge() {
    }

    public ArrayList<String> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<String> userList) {
        this.userList = userList;
    }

    public ArrayList<Food> getfFoodlist() {
        return fFoodlist;
    }

    public void setfFoodlist(ArrayList<Food> fFoodlist) {
        this.fFoodlist = fFoodlist;
    }
}
