package com.example.termproject;

import static com.example.termproject.MyFridgeActivity.fName;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.domain.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText foodNameEdit;
    private TextView foodDate_dateView;
    public static int count = 0;
    private Date foodDate;
    private RadioGroup storageWayRadioGroup;
    private int storageWayInt;
    private Food food;

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseUser user = firebaseAuth.getCurrentUser();

    private String rfName = fName;
    private String pName, date;
    private String coolCountString, freezeCountString;
    int cnt=1, coolCountNum, freezeCountNum;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        init();
        count++;
        setFoodName();
        cool_zero_save();
        freeze_zero_save();
    }

    private void init(){
        foodNameEdit = findViewById(R.id.foodName_editView);
        foodDate_dateView = findViewById(R.id.foodDate_dateView);

        /*유통기한-디폴트 값으로 오늘 날짜 설정*/
        Calendar cal = Calendar.getInstance();
        foodDate_dateView.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));

        initView();
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.fridgeWay_cool_radioBtn){
                storageWayInt = 1;
            }
            else if(i == R.id.fridgeWay_freeze_radioBtn){
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
            date = foodDate_dateView.getText().toString();
        }
    };

    /*유통기한 클릭시 실행 함수*/
    public void mOnClick_DatePick(View v){
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }

    /*바코드인식 버튼 클릭시 실행 함수*/
    public void mOnBarcode(View v){
        Intent intent = new Intent(AddFoodActivity.this, testZxing.class);
        startActivity(intent);
        finish();
    }

    /*확인 버튼 클릭시 실행 함수*/
    public void mOnSubmit(View v){
        save();
        //엑티비티 팝업 닫기
        finish();
    }

    /*취소 버튼 클릭시 실행 함수*/
    public void mOnClose(View v){
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

    public void setFoodName() {
        count++;

        String name;
        Intent foodNameIntent = getIntent();
        String foodName = foodNameIntent.getStringExtra("PRDname");
        foodNameEdit.setText(foodName);
        name = foodNameEdit.getText().toString();

        if(count<2)
        {
            if(name.equals("null"))
            {
//                Toast.makeText(getApplicationContext(), "등록되지 않은 상품입니다.\n 상품명을 직접 입력해주세요.", Toast.LENGTH_LONG).show();
            }
        }
    }

    ///////파이어베이스에 올리기
    public int coolCountDB() {

        mReference.child("RFList").child(fName).child("food").child("냉장").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (int i = 1; i < 100; i++) {
                    coolCountString = Integer.toString(i);
                    if (!snapshot.hasChild(coolCountString)) {
                        break;
                    }
                }
                coolCountNum = Integer.parseInt(coolCountString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return coolCountNum;
    }

    public int freezeCountDB() {

        mReference.child("RFList").child(fName).child("food").child("냉동").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (int i = 1; i < 100; i++) {
                    freezeCountString = Integer.toString(i);
                    if (!snapshot.hasChild(freezeCountString)) {
                        break;
                    }
                }
                freezeCountNum = Integer.parseInt(freezeCountString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return freezeCountNum;
    }

    // Data storage and modification method
    public void postFirebaseDataBase(boolean add) {
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            FirebasePost post = new FirebasePost(pName, date);
            postValues = post.toMap();
        }

        if(storageWayInt == 1)
        {
            cnt = coolCountDB();
            childUpdates.put("/RFList/" + fName + "/food/" + "/냉장/" + cnt + "/", postValues);
            mReference.updateChildren(childUpdates);
        }
        else if(storageWayInt == 2)
        {
            cnt=freezeCountDB();
            childUpdates.put("/RFList/" + fName + "/food/" + "/냉동/" + cnt + "/", postValues);
            mReference.updateChildren(childUpdates);
        }

    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public void cool_zero_save() {
        rfName = "";
        storageWayInt = 1;
        postFirebaseDataBase(true);
    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public void freeze_zero_save() {
        rfName = "";
        storageWayInt = 2;
        postFirebaseDataBase(true);
    }

    // Save to Firebase
    public void save() {
        EditText foodNameEdit = findViewById(R.id.foodName_editView);
        pName = foodNameEdit.getText().toString();
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        String pName, date;

        public FirebasePost(String pName, String date) {
            this.pName = pName;
            this.date = date;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("productName", pName);
            result.put("dueDate", date);
            return result;
        }
    }
}