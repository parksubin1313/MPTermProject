package com.example.termproject.data;

public class Shoppinglist_item {
    public boolean checked;
    public String Item;

    Shoppinglist_item (boolean b, String t){
        checked = b;
        Item = t;
    }

    public boolean isChecked(){
        return checked;
    }
}
