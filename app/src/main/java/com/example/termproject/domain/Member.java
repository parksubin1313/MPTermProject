package com.example.termproject.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {
    private String Email;
//    private String Pw;
    private String Nickname;
    private ArrayList<MyFridge> myFridgeArrayList;
    private ArrayList<ShoppingList> shoppingListArrayList;

//    public Member(String email, String pw, String nickname) {
//        Email = email;
//        Pw = pw;
//        Nickname = nickname;
//    }
    public Member(String email, String nickname) {
        Email = email;
        Nickname = nickname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

//    public String getPw() {
//        return Pw;
//    }
//
//    public void setPw(String pw) {
//        Pw = pw;
//    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public ArrayList<MyFridge> getAllFridgeArrayList() {
        return myFridgeArrayList;
    }

    public void setAllFridgeArrayList(ArrayList<MyFridge> myFridgeArrayList) {
        this.myFridgeArrayList = myFridgeArrayList;
    }

    public ArrayList<ShoppingList> getShoppingListArrayList() {
        return shoppingListArrayList;
    }

    public void setShoppingListArrayList(ArrayList<ShoppingList> shoppingListArrayList) {
        this.shoppingListArrayList = shoppingListArrayList;
    }

}
