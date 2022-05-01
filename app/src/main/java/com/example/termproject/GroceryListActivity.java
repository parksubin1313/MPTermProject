package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GroceryListActivity extends AppCompatActivity {
    private ListView groceryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        Button addFoodBtn = findViewById(R.id.addFoodBtn);

        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "음식 추가", Toast.LENGTH_SHORT).show();
                mOnPopupClick(view);
            }
        });

        groceryList = (ListView)findViewById(R.id.addFood_listView);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        groceryList.setAdapter(adapter);

        data.add("사과");
        data.add("우유");
        adapter.notifyDataSetChanged();
    }

    public void mOnPopupClick(View v){
        //음식 추가하는 팝업(엑티비티)호출
        Intent intent = new Intent(this, AddFoodActivity.class);
        //intent.putExtra("data", "Test addFood Popup");
        startActivity(intent);
    }

    //TODO: 디폴트로 editText 키보드 안보이게
}