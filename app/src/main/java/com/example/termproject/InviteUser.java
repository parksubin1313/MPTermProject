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

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    String frName;
    String uid = user != null ? user.getUid() : null;
    String inviteUser;

    String countString;
    int countNum;
    public static int cnt=1;

    EditText textInviteUid;
    Button invite;
    TextView textUid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_user);

        textInviteUid = findViewById(R.id.user);
        invite = findViewById(R.id.button);
        textUid = findViewById(R.id.textUid);

        textUid.setText(uid);

        if(textInviteUid.getText().toString()!=null)
        {
            zero_save();
        }

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                save();
                Toast.makeText(getApplicationContext(), frName+"에 초대완료", Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }

    public int countDB(){

        mReference.child("USER").child(inviteUser).child("RFList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            FirebasePost post = new FirebasePost(frName);
            postValues = post.toMap();
        }

        cnt = countDB();

        childUpdates.put("/USER/" + inviteUser + "/RFList/" + cnt + "/", postValues);
        mReference.updateChildren(childUpdates);
    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public void zero_save() {

        inviteUser = textInviteUid.getText().toString();
        frName = "";
        postFirebaseDataBase(true);
    }

    // Save to Firebase
    public void save() {

        inviteUser = textInviteUid.getText().toString();
        frName = fName;
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        public String frName;

        public FirebasePost(String frName) {
            this.frName = frName;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("name", frName);
            return result;
        }
    }

    //문자열 null인지 확인
    static boolean isStringEmpty(String str)
    {
        return str == null || str.trim().isEmpty();
    }
}