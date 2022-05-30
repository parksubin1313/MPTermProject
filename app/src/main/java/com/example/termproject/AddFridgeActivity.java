package com.example.termproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddFridgeActivity extends AppCompatActivity {
    //HomeFragment 에서 + 버트 눌렀을 때 오는 곳
    //냉장고 리스트에 새로운 냉장고 추가하기
    //R.id.add_fridge

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fridge);

        EditText fridgeName = findViewById(R.id.fridgeName_editText);
        Button fridgeAddBtn = findViewById(R.id.fridgeList_register_btn);

        fridgeAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), fridgeName.getText().toString() + " is added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
