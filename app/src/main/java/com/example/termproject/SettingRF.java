package com.example.termproject;

import static com.example.termproject.MainActivity.access;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingRF extends AppCompatActivity {

    String uid;
    EditText text;
    String RFname;

    DatabaseReference reference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();
    private DatabaseReference myRef;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<RFData> RFList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting_rf);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Get information of logged in user
        uid = user != null ? user.getUid() : null;

        text = findViewById(R.id.editTextEmail);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = text.getText().toString(); //msg
                //널이 아닐때만 값전송하게
                if (name != null) {
                    RFData rf = new RFData();
                    //chat.setNickname("user");
                    rf.setName(name);
                    mReference.child("/USER/").child(uid).child("/RFList/").push().setValue(rf);
                    finish();
                }
            }
        });

        /*
        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RFList = new ArrayList<>();
        mAdapter = new RFAdapter(RFList, SettingRF.this);
        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //chatData chat = new chatData();
        //chat.setNickname(nick);
        //chat.setMsg("hi");
        //myRef.setValue(chat);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Log.d("RF",snapshot.getValue().toString());
                RFData rf = snapshot.getValue(RFData.class);
                ((RFAdapter) mAdapter).addChat(rf);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */
    }



    // Data storage and modification method
    public void postFirebaseDataBase(boolean add) {

        reference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            SettingRF.FirebasePost post = new SettingRF.FirebasePost(RFname);
            postValues = post.toMap();
        }

        childUpdates.put("wlqkr23" + "/RFList/", postValues);
        reference.updateChildren(childUpdates);
    }

    // Save to Firebase
    public void save() {
        RFname = String.valueOf(text.getText());
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        public String RFname;

        public FirebasePost(String RFname) {
            this.RFname = RFname;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put(RFname, RFname);
            return result;
        }

    }
}