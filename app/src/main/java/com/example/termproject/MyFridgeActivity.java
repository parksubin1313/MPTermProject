package com.example.termproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.termproject.adapter.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyFridgeActivity extends AppCompatActivity {

//    private ListView myFridge_cool_list;
//    private ListView myFridge_freeze_list;
//    private ViewPager viewPager;
//    private TabLayout tabLayout;

    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("MyFridgeActivity : ", "들어옴");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fridge);

        //뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.myFridge_viewPager);
        fragmentPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = findViewById(R.id.myFridge_tabLayout);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout = (TabLayout) findViewById(R.id.myFridge_tabLayout);
//
//        tabLayout.addTab(tabLayout.newTab());
//        tabLayout.addTab(tabLayout.newTab());
//
//        viewPager = (ViewPager) findViewById(R.id.myFridge_viewPager);
//
//        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Log.d("onTabSelected", tab.toString());
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


//        myFridge_cool_list = (ListView)findViewById(R.id.myFridge_cool_listView);
//        myFridge_freeze_list = (ListView) findViewById(R.id.myFridge_freeze_listView);
//
//        List<String> data_cool = new ArrayList<>();
//        List<String> data_freeze = new ArrayList<>();
//
//        ArrayAdapter<String> adapter_cool = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data_cool);
//        myFridge_cool_list.setAdapter(adapter_cool);
//
//        ArrayAdapter<String> adapter_freeze = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data_freeze);
//        myFridge_freeze_list.setAdapter(adapter_freeze);
//
//        for(int i=0; i<10; i++){
//            String pName_cool = (i+1) + "_cool";
//            String pName_freeze = (i+1) + "_freeze";
//            data_cool.add(pName_cool);
//            data_freeze.add(pName_freeze);
//        }
//
//        adapter_cool.notifyDataSetChanged();
//        adapter_freeze.notifyDataSetChanged();
//
//        if(myFridge_cool_list != null){
////            ArrayAdapter<String> adapter_cool = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data_cool);
////            myFridge_cool_list.setAdapter(adapter_cool);
////
////            adapter_cool.notifyDataSetChanged();
//        }
//        else{
//            Log.d("Cool list : ", "null 임");
//        }
//
//        if(myFridge_freeze_list != null){
////            ArrayAdapter<String> adapter_freeze = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data_freeze);
////            myFridge_freeze_list.setAdapter(adapter_freeze);
////
////            adapter_freeze.notifyDataSetChanged();
//        }
//        else{
//            Log.d("Freeze list : ", "null 임");
//        }
//
//



    }
}
