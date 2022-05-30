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

public class myFridge_freeze extends Fragment implements AdapterView.OnItemClickListener {

    private View view;

    public static myFridge_freeze newInstance(){
        myFridge_freeze mff = new myFridge_freeze();
        return mff;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_my_fridge_freeze, container, false);

//        List<String> data = new ArrayList<>();
//        if(listView != null){
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
//            listView.setAdapter(adapter);
//
//            for(int i=0; i<15; i++){
//                String fName = "freeze : " +(i+1);
//                data.add(fName);
//            }
////        이걸 해줘야 add 가 반영됨
//            adapter.notifyDataSetChanged();
//        }
//        else{
//            Log.d("myFridge_freeze.java", "null 임");
//        }
////        listView.setAdapter(AFadapter);
//


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] pName = {"Lamb", "Marashangguo", "Maratang", "Pasta", "Outback"};
        ListView listView = (ListView) view.findViewById(R.id.myFridge_freeze_listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, pName);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String[] pName = {"Lamb", "Marashangguo", "Maratang", "Pasta", "Outback"};

        Toast.makeText(getActivity(), pName[position], Toast.LENGTH_SHORT).show();
    }
}