package com.example.termproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import java.util.Objects;

public class CommunityActivity extends AppCompatActivity {
    private TextView name;
    private TextView loc;
    private TextView detail;
    private ImageView imageView;
    private TextView ETDate;
    private Button uploadBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_item);
        Log.d("CommunityActivity ", "들어옴");

        name = findViewById(R.id.ETName);
        loc = findViewById(R.id.ETLoc);
        detail = findViewById(R.id.ETDetail);
        imageView = findViewById(R.id.imageView);
        ETDate = findViewById(R.id.ETDate);
        uploadBtn = findViewById(R.id.uploadBtn);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), name.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("food", name.getText().toString());

        name.setText("샐러드");
        loc.setText("가천대학교");
        detail.setText("서브웨이 쉬림프 샐러드");

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
        ETDate.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 2) + "-" + cal.get(Calendar.DATE));
    }
}
