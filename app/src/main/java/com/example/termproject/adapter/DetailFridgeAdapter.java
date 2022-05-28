package com.example.termproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.termproject.R;
import com.example.termproject.domain.Food;

import java.util.ArrayList;

public class DetailFridgeAdapter extends BaseAdapter {

    //TextView 두 개를 받아서 ArrayList 두 개에 각 각 넣어주는 것임
    //각 ArrayList 는
    private TextView fName;
    private TextView fDate;
    private ArrayList<Food> foods = new ArrayList<Food>();
    private ArrayList<String> users = new ArrayList<String>();

    public DetailFridgeAdapter(){}

    public TextView getfName() {
        return fName;
    }

    public void setfName(TextView fName) {
        this.fName = fName;
    }

    public TextView getfDate() {
        return fDate;
    }

    public void setfDate(TextView fDate) {
        this.fDate = fDate;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    @Override
    public int getCount() {
        return 0;
    }

    public void addItem(Food food){
        foods.add(food);
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_detailfridge_item, parent, false);
        }

        fName = (TextView) convertView.findViewById(R.id.foodName_textView);
        fDate = (TextView) convertView.findViewById(R.id.foodDate_textView);

        Food foodContent = foods.get(position);

        fName.setText(foodContent.getfName());
        //fDate 는 TextView 인데 getfDate 는 Date 형식임. 이거 되는건가?
        fDate.setText("D- " + foodContent.getfDate());

        return convertView;
    }
}
