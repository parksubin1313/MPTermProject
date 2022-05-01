package com.example.termproject;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀 바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_food);

        //데이터 가져오기
        //Intent intent = getIntent();
        //String data = intent.getStringExtra("data");

    }
    //확인 버튼 클릭
    public void mOnSubmit(View v){

        Toast.makeText(getApplicationContext(), "확인 버튼 클릭", Toast.LENGTH_SHORT).show();
        //데이터 전달하기
        //Intent intent = new Intent();
        //TODO: 리스트에 반영되게 저장

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