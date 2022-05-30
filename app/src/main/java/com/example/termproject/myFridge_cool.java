package com.example.termproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class myFridge_cool extends Fragment implements AdapterView.OnItemClickListener{

    private View view;

    public static myFridge_cool newInstance(){
        myFridge_cool mfc = new myFridge_cool();
        return mfc;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_fridge_cool, container, false);

//        List<String> data = new ArrayList<>();
//        if(listView != null){
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
//            listView.setAdapter(adapter);
//
//            for(int i=0; i<15; i++){
//                String fName = "cool : "+(i+1);
//                data.add(fName);
//            }
//
////        이걸 해줘야 add 가 반영됨
//            adapter.notifyDataSetChanged();
//        }
//        else{
//            Log.d("myFridge_cool.java", "null 임");
//        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] pName = {"Strawberry", "Mango", "Egg", "Milk", "Kimchi"};
        ListView listView = (ListView) view.findViewById(R.id.myFridge_cool_listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pName);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String[] pName = {"Strawberry", "Mango", "Egg", "Milk", "Kimchi"};

        Toast.makeText(getActivity(), pName[position], Toast.LENGTH_SHORT).show();
    }
}