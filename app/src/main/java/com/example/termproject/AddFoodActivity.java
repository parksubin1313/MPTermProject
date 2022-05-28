package com.example.termproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.domain.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;

    private Button submitBtn;
    private Button cancelBtn;
    private Button barcodeBtn;
    private EditText foodNameEdit;
//    TODO: 이거 코드 받아서 해결하기!!!!!!!
    private Date foodDate;
    private RadioGroup storageWayRadioGroup;
    private RadioButton storageWayRadio;
    private int storageWayInt;

    private Food food;

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseUser user = firebaseAuth.getCurrentUser();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        init();
    }

    private void init(){
        this.context = this;
        submitBtn = findViewById(R.id.submitBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        foodNameEdit = findViewById(R.id.foodName_editView);
//        TODO: 이거 코드 받아서 해결하기!!!!!!!
//        foodDate = findViewById(R.id.foodName_editView);

        initView();
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.fridgeWay_cool_radioBtn){
                Toast.makeText(getApplicationContext(), "냉장선택", Toast.LENGTH_SHORT).show();
                storageWayInt = 1;
            }
            else if(i == R.id.fridgeWay_freeze_radioBtn){
                Toast.makeText(getApplicationContext(), "냉동선택", Toast.LENGTH_SHORT).show();
                storageWayInt = 2;
            }
        }
    };

    private void initView(){
        storageWayRadioGroup = findViewById(R.id.fridgeWayRadioGroup);
        storageWayRadioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.submitBtn:
                food = new Food(foodNameEdit.toString(), foodDate, storageWayInt);

                Intent i = new Intent();

        }
    }
}
