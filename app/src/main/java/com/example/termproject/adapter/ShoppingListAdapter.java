package com.example.termproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.termproject.R;
import com.example.termproject.domain.APIFood;
import com.example.termproject.domain.Food;

import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


    // 왜 있는지는 모르지만 나중에 쓸까봐 혹시 남겨둠
//    private TextView fName;
//    private TextView fDate;
//    ArrayList<APIFood> foods = new ArrayList<APIFood>();
//
//    public ShoppingListAdapter(){}
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    public void addItem(APIFood food){
//        foods.add(food);
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return foods.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final int pos = position;
//        final Context context = parent.getContext();
//
//        if(convertView == null){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.single_allfridge_item, parent, false);
//        }
//
//        fName = (TextView) convertView.findViewById(R.id.foodName_textView);
//        fDate = (TextView) convertView.findViewById(R.id.foodDate_textView);
//
//        APIFood foodContent = foods.get(position);
//
//        fName.setText(foodContent.getPRDLST_NM());
//        fDate.setText("D- " + Integer.toString(foodContent.getBAR_CD()));
//
//        return convertView;
//    }
}
