package com.example.termproject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.domain.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
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
    private TextView foodDate_dateView;

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


        /*유통기한-디폴트 값으로 오늘 날짜 설정*/
        foodDate_dateView = findViewById(R.id.foodDate_dateView);
        Calendar cal = Calendar.getInstance();
        foodDate_dateView.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));


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

    /*유통기한 날짜 선택하면 뜨는 DatePicker*/
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            //Date Picker 에서 선택한 날짜를 TextView에 설정.
            foodDate_dateView = (TextView) findViewById(R.id.foodDate_dateView);
            foodDate_dateView.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
        }
    };

    /*유통기한 클릭시 실행 함수*/
    public void mOnClick_DatePick(View v){
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }

    /*바코드인식 버튼 클릭시 실행 함수*/
    public void mOnBarcode(View v){
        //TODO: 바코드인식 버튼 클릭시 실행 함수 구현

        Toast.makeText(getApplicationContext(), "바코드 버튼 클릭", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(getApplicationContext(), testZxing.class);
//
//        startActivity(intent);
    }

    /*확인 버튼 클릭시 실행 함수*/
    public void mOnSubmit(View v){
        //TODO: 확인버튼 클릭시 실행 함수 구현
        Toast.makeText(getApplicationContext(), "확인 버튼 클릭", Toast.LENGTH_SHORT).show();
        //엑티비티 팝업 닫기
        finish();
    }

    /*취소 버튼 클릭시 실행 함수*/
    public void mOnClose(View v){
        Toast.makeText(getApplicationContext(), "취소 버튼 클릭", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥 레이어 클릭 시 안닫히게
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
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
