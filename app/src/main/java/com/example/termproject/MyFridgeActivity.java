package com.example.termproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyFridgeActivity extends AppCompatActivity {

    private ListView myFridge_cool_list;
    private ListView myFridge_freeze_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fridge);

        myFridge_cool_list = (ListView)findViewById(R.id.myFridge_cool_listView);
        myFridge_freeze_list = (ListView) findViewById(R.id.myFridge_freeze_listView);

        List<String> data_cool = new ArrayList<>();
        List<String> data_freeze = new ArrayList<>();

        ArrayAdapter<String> adapter_cool = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_cool);
        myFridge_cool_list.setAdapter(adapter_cool);

        ArrayAdapter<String> adapter_freeze = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_freeze);
        myFridge_freeze_list.setAdapter(adapter_freeze);

        for(int i=0; i<10; i++){
            data_cool.add(i+"_cool");
            data_freeze.add(i+"_freeze");
        }
        adapter_cool.notifyDataSetChanged();
        adapter_freeze.notifyDataSetChanged();
    }
}
