package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.termproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity {

    final int FRIDGEWAYCOOL = 0;
    final int FRIDGEWAYFREEZE = 1;

    private RadioGroup frigeWay;
    private RadioButton frigeWay_cool, frigeWay_freeze;
    private EditText foodName, memo;
    //TODO: private 유통기한
    private TextView foodNum, expiryDate;
    private int count = 1;
    private int f_Way = -1;
    private int resId = -1;



    String uid;
    /*Firebase*/
    DatabaseReference reference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();
    private DatabaseReference myRef;

    /*ListView*/
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<FoodItem> foodItemArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀 바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_food);

        //데이터 가져오기
        //Intent intent = getIntent();
        //String data = intent.getStringExtra("data");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Get information of logged in user
        uid = user != null ? user.getUid() : null;


        //라디오 그룹 설정
        frigeWay = (RadioGroup) findViewById(R.id.frigeWay);
        frigeWay.setOnCheckedChangeListener(radioGroupClickListener);
        //라디오 버튼 설정
        frigeWay_cool = (RadioButton) findViewById(R.id.frigeWay_cool);
        frigeWay_freeze = (RadioButton) findViewById(R.id.frigeWay_freeze);

        /*식품명*/
        foodName = (EditText) findViewById(R.id.foodName);

        /*메모 작성*/
        memo = (EditText) findViewById(R.id.memo);

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
    RadioGroup.OnCheckedChangeListener radioGroupClickListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.frigeWay_cool){
                f_Way = FRIDGEWAYCOOL;
                Toast.makeText(getApplicationContext(), "냉장 라디오버튼", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.frigeWay_freeze){
                f_Way = FRIDGEWAYFREEZE;
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
        if(foodName.getText().toString().length() == 0 || f_Way == -1){
            Toast.makeText(getApplicationContext(), "식품명 또는 보관방법이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }else {
            FoodItem foodItem = new FoodItem();
            foodItem.setFoodName(foodName.getText().toString());
            foodItem.setDueDate(expiryDate.getText().toString());
            foodItem.setQuantity(count);
            foodItem.setStorageWay(f_Way);
            if (memo.getText().toString().length() != 0) {
                foodItem.setMemo(memo.getText().toString());
            }

            //TODO: 이미지 추가 기능
            if (resId != -1) {
                foodItem.setResId(resId);
            }


//            mReference.child("/RFList/").child(get).child(uid).child("//").push().setValue(foodItem);

            Toast.makeText(getApplicationContext(), "확인 버튼 클릭", Toast.LENGTH_SHORT).show();

            //엑티비티 팝업 닫기
            finish();
        }
    }

    //취소 버튼 클릭
    public void mOnClose(View v){

        Toast.makeText(getApplicationContext(), "취소 버튼 클릭", Toast.LENGTH_SHORT).show();
        finish();
    }

    //바코드 버튼 클릭
    public void mOnBarcode(View v){

        Toast.makeText(getApplicationContext(), "바코드 버튼 클릭", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), testZxing.class);

        startActivity(intent);
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