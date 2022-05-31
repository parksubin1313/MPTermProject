package com.example.termproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CommunityActivity extends AppCompatActivity {
    private EditText name;
    private EditText loc;
    private EditText tag;
    private EditText detail;
    private ImageView imageView;
    private TextView ETDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_upload);
        Log.d("CommunityActivity ", "들어옴");

        name = findViewById(R.id.ETName);
        loc = findViewById(R.id.ETLoc);
        tag = findViewById(R.id.ETTag);
        detail = findViewById(R.id.ETDetail);
        imageView = findViewById(R.id.imageView);
        ETDate = findViewById(R.id.ETDate);

//        Button btn = findViewById(R.id.uploadBtn);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), name.getText().toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), AddFridgeActivity.class);
//                startActivity(intent);
//            }
//        });

        /*유통기한-디폴트 값으로 오늘 날짜 설정*/
        ETDate = findViewById(R.id.ETDate);
        Calendar cal = Calendar.getInstance();
        ETDate.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));


    }

    /*유통기한 날짜 선택하면 뜨는 DatePicker*/
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            //Date Picker 에서 선택한 날짜를 TextView에 설정.
            ETDate = (TextView) findViewById(R.id.ETDate);
            ETDate.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
        }
    };

    /*유통기한 클릭시 실행 함수*/
    public void mOnClick_DatePick(View v){
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }
}
