package com.example.termproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.termproject.R;
import com.example.termproject.domain.AllFridge;

import java.util.ArrayList;

public class AllFridgeAdapter extends BaseAdapter {

    //냉장고 이름
    private TextView fName;
    ArrayList<AllFridge> allFridges = new ArrayList<AllFridge>();

    public AllFridgeAdapter(){}

    public TextView getfName() {
        return fName;
    }

    public void setfName(TextView fName) {
        this.fName = fName;
    }

    public ArrayList<AllFridge> getFoods() {
        return allFridges;
    }

    public void setFoods(ArrayList<AllFridge> fridges) {
        this.allFridges = fridges;
    }

    @Override
    public int getCount() {
        return allFridges.size();
    }

    @Override
    public Object getItem(int position) {
        return allFridges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(AllFridge allFridgeInfo){
        allFridges.add(allFridgeInfo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //냉장고 리스트 보여지기
            //TODO: 이것도 아직
//            convertView = inflater.inflate(R.layout.all_fridge, parent, false);
            Log.d("AllFridgeAdapter", "아직 안 함");
        }

        fName = (TextView) convertView.findViewById(R.id.foodName_textView);
        //이름 받아오면 그걸 등록하면 됨
        AllFridge foodContent = allFridges.get(position);

        //TODO: 이것도 아직
//        fName.setText(foodContent.getFridge_name());

        return convertView;
    }

}
