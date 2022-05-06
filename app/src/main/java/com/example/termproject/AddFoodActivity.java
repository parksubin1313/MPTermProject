package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.termproject.R;

import java.util.Calendar;

public class AddFoodActivity extends AppCompatActivity {
    private RadioGroup frigeWay;
    private RadioButton frigeWay_cool, frigeWay_freeze;
    private EditText foodName, memo;
    //TODO: private 유통기한
    private TextView foodNum, expiryDate;
    private int count = 1;

    /*ListView*/
    //FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀 바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_food);

        //데이터 가져오기
        //Intent intent = getIntent();
        //String data = intent.getStringExtra("data");

        //라디오 그룹 설정
        frigeWay = (RadioGroup) findViewById(R.id.frigeWay);
        //라디오 버튼 설정
        frigeWay_cool = (RadioButton) findViewById(R.id.frigeWay_cool);
        frigeWay_freeze = (RadioButton) findViewById(R.id.frigeWay_freeze);

        /*식품명*/
        //TODO: 식품명

        /*메모 작성*/
        //TODO: 메모 작성

        /*유통기한-디폴트 값으로 오늘 날짜 설정*/
        expiryDate = findViewById(R.id.expiryDate);
        Calendar cal = Calendar.getInstance();
        expiryDate.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));

        /*음식 개수*/
        foodNum = (TextView) findViewById(R.id.foodNum);
        foodNum.setText(count + "");
        //count = Integer.parseInt(foodNum.getText().toString());

    }


    /*라디오 버튼 클릭 리스너*/
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.frigeWay_cool){
                Toast.makeText(getApplicationContext(), "냉장 라디오버튼", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.frigeWay_freeze){
                Toast.makeText(getApplicationContext(), "냉동 라디오버튼", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /*유통기한*/
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            //Date Picker 에서 선택한 날짜를 TextView에 설정.
            expiryDate = (TextView) findViewById(R.id.expiryDate);
            expiryDate.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
        }
    };

    public void mOnClick_DatePick(View v){
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }

    //수량 감소(-) 버튼 클릭
    public void mOnMinus(View v){
        if(count > 0){
            count--;
        }
        foodNum.setText(count + "");
        //TODO: 이거 실행 안됨.
    }

    //수량 증가(+) 버튼 클릭
    public void mOnPlus(View v){
        count++;
        foodNum.setText(count + "");
        //TODO: 이거 실행 안됨.
    }

    //확인 버튼 클릭
    public void mOnSubmit(View v){

        Toast.makeText(getApplicationContext(), "확인 버튼 클릭", Toast.LENGTH_SHORT).show();
        //데이터 전달하기
        //Intent intent = new Intent();
        //TODO: 리스트에 반영되게 저장

        //adapter = new FoodAdapter();
        //adapter.addItem(new FoodItem(0, "양파", "2022-12-01", 3, "", R.mipmap.ic_launcher_round));

        //엑티비티 팝업 닫기
        finish();
    }

    //취소 버튼 클릭
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
}