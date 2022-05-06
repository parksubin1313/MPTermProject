package com.example.termproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    ArrayList<FoodItem> items = new ArrayList<FoodItem>();


    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(FoodItem item){
        items.add(item);

    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        //뷰 객체 재사용
        FoodItemView view = null;
        if(convertView == null){
            view = new FoodItemView(convertView.getContext());
        }else{
            view = (FoodItemView) convertView;
        }
        return null;
    }
}
