package com.example.termproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.termproject.MyFridgeActivity.fName;

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

public class InviteUser extends AppCompatActivity {
    //HomeFragment 에서 + 버트 눌렀을 때 오는 곳
    //냉장고 리스트에 새로운 냉장고 추가하기
    //R.id.add_fridge

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    DatabaseReference reference;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    String uid = user != null ? user.getUid() : null;

    String inveiteUid;

    String countString;
    int count=1, countNum;
    public static int cnt=1;

    //String frName = fName;
    String frName = fName;

    String userUid;

    EditText textInviteUid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_user);


        textInviteUid = findViewById(R.id.user);
        String userUid = textInviteUid.getText().toString();

        TextView textUid = findViewById(R.id.textUid);
        textUid.setText(uid);

        //zero_save();

        Button invite = findViewById(R.id.button);
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //zero_save();
                save();
//                Toast.makeText(getApplicationContext(), frName+"에 초대완료", Toast.LENGTH_LONG).show();
                finish();
/*
                if(userUid!=null)
                {
                    zero_save();
                    save();
                    Toast.makeText(getApplicationContext(), frName+"에 초대완료", Toast.LENGTH_LONG).show();
                    finish();
                }

 */

            }
        });
    }

    public int countDB(){

        textInviteUid = findViewById(R.id.user);
        String userUid = textInviteUid.getText().toString();
        mReference.child("USER").child(userUid).child("RFList").addValueEventListener(new ValueEventListener() {
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

        textInviteUid = findViewById(R.id.user);
        String userUid = textInviteUid.getText().toString();

        childUpdates.put("/USER/" + userUid + "/RFList/" + 3 + "/", postValues);
        mReference.updateChildren(childUpdates);
    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public boolean zero_save() {

        frName = "";
        postFirebaseDataBase(true);
        return true;
    }

    // Save to Firebase
    public void save() {

        textInviteUid = findViewById(R.id.user);
        String userUid = textInviteUid.getText().toString();
        frName = fName;
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
