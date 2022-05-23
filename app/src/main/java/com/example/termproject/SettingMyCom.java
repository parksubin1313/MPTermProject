package com.example.termproject;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Setting 에서 내가 올린 상품들 볼 수 있어야 하니까

public class SettingMyCom extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CommunityItem> item_list = new ArrayList<>();
    private CItemAdapter cItemAdapter;
    private CheckBox chk_selected_all;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_my_community);

        //init 처음 설정용
        initControls();
    }

    private void initControls(){

        chk_selected_all = (CheckBox) findViewById(R.id.check_btn);

        recyclerView = (RecyclerView) findViewById(R.id.MyComList);
        item_list.add(new CommunityItem("name1", "date1", "loc1", "tag1", "detail1", false));
        item_list.add(new CommunityItem("name2", "date2", "loc2", "tag2", "detail2", false));
        item_list.add(new CommunityItem("name3", "date3", "loc3", "tag3", "detail3", false));
        item_list.add(new CommunityItem("name4", "date4", "loc4", "tag4", "detail4", false));
        item_list.add(new CommunityItem("name5", "date5", "loc5", "tag5", "detail5", false));
        item_list.add(new CommunityItem("name6", "date6", "loc6", "tag6", "detail6", false));
        item_list.add(new CommunityItem("name7", "date7", "loc7", "tag7", "detail7", false));
        item_list.add(new CommunityItem("name8", "date8", "loc8", "tag8", "detail8", false));
        item_list.add(new CommunityItem("name9", "date9", "loc9", "tag9", "detail9", false));
        item_list.add(new CommunityItem("name10", "date10", "loc10", "tag10", "detail10", false));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cItemAdapter = new CItemAdapter(item_list);
        recyclerView.setAdapter(cItemAdapter);

        chk_selected_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chk_selected_all.isChecked()){
                    for(CommunityItem citem : item_list){
                        citem.setSelected(true);
                    }
                }
                else{
                    for(CommunityItem citem : item_list){
                        citem.setSelected(false);
                    }
                }
                cItemAdapter.notifyDataSetChanged();
            }
        });

    }

}

