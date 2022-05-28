package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.domain.MyFridge;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class FridgeAddActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    MyFridge fridgeInfo;
    EditText fridgeName;
    Button CreateButton;
    ArrayList<String> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_add);

        fridgeName = findViewById(R.id.fridgeName_editText);
        CreateButton = findViewById(R.id.fridgeList_register_btn);

        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateFridge();
            }
        });

    }

    private void CreateFridge() {
        String FN = fridgeName.getText().toString();
        if(CheckCondition(FN)) {
            userList.add(user.getUid());
            //TODO: 이것도 아직
            fridgeInfo = new MyFridge();
            db.collection("Fridge").document(user.getUid()).collection("My_Fridge").document(FN)
                    .set(fridgeInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    StartToast("그룹이 생성되었습니다.");
                    //이부분을 꼭 해줘야 하나? 일단 주석 처리 함
//                    StartActivity(HomeActivity.class);
                }
            });
        }
    }

    //냉장고 이름에 대한 최소 조건
    private boolean CheckCondition(String Name) {
        if(Name.length() <= 1) {
            StartToast("냉장고 이름은 최소 1자 이상이어야 합니다.");
            return false;
        }
        if(Name.length() <= 10) {
            StartToast("냉장고 이름은 10자를 넘을 수 없습니다.");
            return false;
        }
        return true;
    }

    private void StartToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void StartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}