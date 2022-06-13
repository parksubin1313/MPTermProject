package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChoiceFridgeActivity extends AppCompatActivity {
    //HomeFragment 에서 + 버트 눌렀을 때 오는 곳
    //냉장고 리스트에 새로운 냉장고 추가하기
    //R.id.add_fridge

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    DatabaseReference reference;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    public static String fName;
    public static String pName;
    String uid = user != null ? user.getUid() : null;

    String countString;
    int count=1, countNum;
    public static int cnt=1;

    EditText fridgeName;
    Button fridgeAddBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_fridge);

        fridgeName = findViewById(R.id.fridgeName_editText);
        fridgeAddBtn = findViewById(R.id.fridgeList_register_btn);


        zero_save();

        fridgeAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(isStringEmpty(fridgeName.getText().toString()))) {
                    //save();
                    fName=fridgeName.getText().toString();
                    Toast.makeText(getApplicationContext(), fName + " is choiced", Toast.LENGTH_SHORT).show();
                    fridgeName.setText(null);
                    Intent intent = new Intent(ChoiceFridgeActivity.this, ChoiceFoodActivity.class);
                    intent.putExtra("fName",fName);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), fridgeName.getText().toString() + "냉장고의 이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    fridgeName.setText(null);
                }
            }
        });
    }

    public int countDB(){

        mReference.child("USER").child(uid).child("RFList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //String count;

                for(int i=1; i<100; i++){

                    countString = Integer.toString(i);

                    if(!snapshot.hasChild(countString)){
                        break;
                    }
                    else{
                    }


                }

                countNum = Integer.parseInt(countString);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return countNum;
    }

    // Data storage and modification method
    public void postFirebaseDataBase(boolean add) {

        //reference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            FirebasePost post = new FirebasePost(fName);
            postValues = post.toMap();
        }

        cnt = countDB();

        childUpdates.put("/USER/" + uid + "/RFList/" + cnt + "/", postValues);
        mReference.updateChildren(childUpdates);
    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public void zero_save() {

        fName = "";
        postFirebaseDataBase(true);
    }

    // Save to Firebase
    public void save() {

        fName = fridgeName.getText().toString();
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        public String fName;

        public FirebasePost(String fName) {
            this.fName = fName;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("name", fName);
            return result;
        }
    }

    //문자열 null인지 확인
    static boolean isStringEmpty(String str)
    {
        return str == null || str.trim().isEmpty();
    }
}
