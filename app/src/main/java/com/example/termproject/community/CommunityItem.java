package com.example.termproject.community;

//community 에 올라갈 때, 한 상품페이지가 가질 요소

public class CommunityItem {
    //community 화면 (음식리스트 보이는 그 화면)
    String foodName;
    String dueDate;
    String location;
    String tag;
    String detail;
    boolean isSelected;

    public CommunityItem(String foodName, String dueDate, String location, String tag, String detail, boolean isSelected) {
        this.foodName = foodName;
        this.dueDate = dueDate;
        this.location = location;
        this.tag = tag;
        this.detail = detail;
        this.isSelected = isSelected;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }
}
