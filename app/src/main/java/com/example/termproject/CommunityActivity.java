package com.example.termproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.ui.dashboard.DashboardFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

public class CommunityActivity extends AppCompatActivity {
    private TextView name;
    private TextView loc;
    private TextView detail;
    private ImageView imageView;
    private TextView ETDate;
    private Button chatBtn;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_item);
        Log.d("CommunityActivity ", "들어옴");

        Intent indexIntent = getIntent();
        String index = indexIntent.getStringExtra("index");

        name = findViewById(R.id.ETName);
        loc = findViewById(R.id.ETLoc);
        detail = findViewById(R.id.ETDetail);
        imageView = findViewById(R.id.imageView);
        ETDate = findViewById(R.id.ETDate);
        chatBtn = findViewById(R.id.chatBtn);

        mReference.child("/Community List/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("0")) {
                    for (DataSnapshot dataSnapshot : snapshot.child(index).getChildren()) {
                        String key = dataSnapshot.getKey();

                        if (key.equals("name")) {
                            String value = "" + dataSnapshot.getValue().toString();
                            name.setText(value);
                        }
                        if (key.equals("location")) {
                            String value = "" + dataSnapshot.getValue().toString();
                            loc.setText(value);
                        }
                        if (key.equals("description")) {
                            String value = "" + dataSnapshot.getValue().toString();
                            detail.setText(value);
                        }
                        if (key.equals("dueDate")) {
                            String value = "" + dataSnapshot.getValue().toString();
                            ETDate.setText(value);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), name.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("food", name.getText().toString());
                startActivity(intent);


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

            }
        });
    }

    //name.setText("샐러드");
    //loc.setText("가천대학교");
    //detail.setText("서브웨이 쉬림프 샐러드");

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
        /*
        ETDate = findViewById(R.id.ETDate);
        Calendar cal = Calendar.getInstance();
        ETDate.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 2) + "-" + cal.get(Calendar.DATE));
    }
    */

}