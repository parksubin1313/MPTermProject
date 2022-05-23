package com.example.termproject;


public class FoodItem {
    int storageWay; //냉장 = 0, 냉동 = 1, 기타 = 2
    String foodName;
    String dueDate;
    int quantity;
    String memo;
    int resId; //이미지

    //Constructor
    public FoodItem(int storageWay, String foodName, String dueDate, int quantity, String memo, int resId) {
        this.storageWay = storageWay;
        this.foodName = foodName;
        this.dueDate = dueDate;
        this.quantity = quantity;
        this.memo = memo;
        this.resId = resId;
    }

    public FoodItem() {

    }

    public int getStorageWay() {
        return storageWay;
    }

    public void setStorageWay(int storageWay) {
        this.storageWay = storageWay;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

