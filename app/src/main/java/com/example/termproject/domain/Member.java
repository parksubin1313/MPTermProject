package com.example.termproject.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable {
    private String Email;
    private String Nickname;
    private ArrayList<MyFridge> myFridgeArrayList;
    private ArrayList<ShoppingList> shoppingListArrayList;

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
