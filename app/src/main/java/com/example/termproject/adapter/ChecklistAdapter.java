package com.example.termproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.termproject.R;
import com.example.termproject.data.Shoppinglist_item;

import java.util.ArrayList;

public class ChecklistAdapter extends BaseAdapter {
    private ArrayList<Shoppinglist_item> list;

//    public ChecklistAdapter(ArrayList<String> list) {
//        this.list = list;
//    }

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

    public boolean isChecked(int i){
        return list.get(i).checked;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.shoppinglist_check, viewGroup, false);
        }

        TextView itemName = view.findViewById(R.id.item_shopping);
        CheckBox checkBox = view.findViewById(R.id.checkbox_shopping);
        checkBox.setChecked(list.get(i).checked);
        itemName.setText(list.get(i).Item);

        checkBox.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean newState = !list.get(i).isChecked();
                list.get(i).checked = newState;
            }
        }));
        checkBox.setChecked(isChecked(i));
        return view;
    }
}
