package com.example.termproject;

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

public class AddShoppingActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    String pName;
    String uid = user != null ? user.getUid() : null;

    String countString;
    int count = 1, countNum;
    public static int cnt = 1;

    EditText shoppingName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shopping);

        shoppingName = findViewById(R.id.shopping_name_editText);
        Button shoppingAddBtn = findViewById(R.id.shoppingList_register_btn);

        zero_save();

        shoppingAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(isStringEmpty(shoppingName.getText().toString()))) {
                    save();
//                    Toast.makeText(getApplicationContext(), shoppingName.getText().toString() + " is added", Toast.LENGTH_SHORT).show();
                    shoppingName.setText(null);
                } else {
//                    Toast.makeText(getApplicationContext(), shoppingName.getText().toString() + "식품명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    shoppingName.setText(null);
                }
            }
        });
    }


    //  파베 올리기
    public int countDB() {

        mReference.child("USER").child(uid).child("shoppingList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //String count;

                for (int i = 1; i < 100; i++) {

                    countString = Integer.toString(i);

                    if (!snapshot.hasChild(countString)) {
                        break;
                    } else {
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
            FirebasePost post = new FirebasePost(pName);
            postValues = post.toMap();
        }

        cnt = countDB();

        childUpdates.put("/USER/" + uid + "/shoppingList/" + cnt + "/", postValues);
        mReference.updateChildren(childUpdates);
    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public void zero_save() {

        pName = "";
        postFirebaseDataBase(true);
    }

    // Save to Firebase
    public void save() {

        pName = shoppingName.getText().toString();
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        public String pName;

        public FirebasePost(String pName) {
            this.pName = pName;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("productName", pName);
            return result;
        }

    }

    //문자열 null인지 확인
    static boolean isStringEmpty(String str)
    {
        return str == null || str.trim().isEmpty();
    }
}
