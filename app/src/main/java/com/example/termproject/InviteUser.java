package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.termproject.MyFridgeActivity.fName;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class InviteUser extends AppCompatActivity {

    private FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();
    String uid = curUser != null ? curUser.getUid() : null;

    String countString;
    int count=1, countNum;
    public static int cnt=1;

    //String frName = fName;
    String frName = fName;

    String userUid;

    EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_user);

        user = findViewById(R.id.user);
        String userUid = user.getText().toString();

        zero_save();

        Button invite = findViewById(R.id.button);
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

        user = findViewById(R.id.user);
        String userUid = user.getText().toString();
        frName = fName;
        
        //reference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            FirebasePost post = new FirebasePost(frName);
            postValues = post.toMap();
        }

        cnt = countDB();

        childUpdates.put("/USER/" + userUid + "/RFList/" + cnt + "/", postValues);
        mReference.updateChildren(childUpdates);
    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public void zero_save() {

        frName = "";
        postFirebaseDataBase(true);
    }

    // Save to Firebase
    public void save() {

        //fName = fridgeName.getText().toString();
        user = findViewById(R.id.user);
        String userUid = user.getText().toString();
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
}