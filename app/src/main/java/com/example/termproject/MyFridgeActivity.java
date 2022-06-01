package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_food, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getApplicationContext(), "food add clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), AddFoodActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public static String fName;

    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fridge);
        Log.d("MyFridgeActivity", "들어옴");

        Intent fNameIntent = getIntent();
        fName = fNameIntent.getStringExtra("fName");
        Log.e("gg2", fName);

        //뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.myFridge_viewPager);
        fragmentPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        //fragmentPagerAdapter.setName("hi");

        TabLayout tabLayout = findViewById(R.id.myFridge_tabLayout);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
