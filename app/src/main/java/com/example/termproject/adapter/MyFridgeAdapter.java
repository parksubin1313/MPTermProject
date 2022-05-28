package com.example.termproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.termproject.R;
import com.example.termproject.domain.AllFridge;
import com.example.termproject.domain.MyFridge;

import java.util.ArrayList;

public class MyFridgeAdapter extends BaseAdapter {

    //냉장고 이름
    private TextView fName;
    ArrayList<MyFridge> myFridges = new ArrayList<MyFridge>();

    public MyFridgeAdapter(){}

    public TextView getfName() {
        return fName;
    }

    public void setfName(TextView fName) {
        this.fName = fName;
    }

    public ArrayList<MyFridge> getFoods() {
        return myFridges;
    }

    public void setFoods(ArrayList<MyFridge> fridges) {
        this.myFridges = fridges;
    }

    @Override
    public int getCount() {
        return myFridges.size();
    }

    @Override
    public Object getItem(int position) {
        return myFridges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(MyFridge allFridgeInfo){
        myFridges.add(allFridgeInfo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //냉장고 리스트 보여지기
            convertView = inflater.inflate(R.layout.my_fridge, parent, false);
        }

        fName = (TextView) convertView.findViewById(R.id.foodName_textView);
        //이름 받아오면 그걸 등록하면 됨
        //TODO: 이것도 아직
//        AllFridge foodContent = allFridges.get(position);

        //TODO: 이것도 아직
//        fName.setText(foodContent.getFridge_name());

        return convertView;
    }

}
