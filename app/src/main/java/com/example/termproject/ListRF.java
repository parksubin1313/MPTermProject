package com.example.termproject;


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

import com.example.termproject.comunity.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRF extends AppCompatActivity {


    String id, name;
    String uid;

    DatabaseReference reference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<RFData> RFList;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_list_rf);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Get information of logged in user
        uid = user != null ? user.getUid() : null;

        //mReference.child(uid).child("/RFList/").push().setValue("hello world");

        /*
        //If RF is existed.
        mReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChild("/RFList/"))
                {

                }
                else{
                    //Intent intent = new Intent(nakigi.this, nakigi.class);
                    //startActivity(intent);
                    //finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });

         */

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save();
                //Intent intent = new Intent(MainActivity.this, checkNakigi.class);
                Intent intent = new Intent(ListRF.this, SettingRF.class);
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RFList = new ArrayList<>();
        mAdapter = new RFAdapter(RFList, ListRF.this);
        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //chatData chat = new chatData();
        //chat.setNickname(nick);
        //chat.setMsg("hi");
        //myRef.setValue(chat);


        /*
        mReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("/RFList/")) {
                    for (DataSnapshot dataSnapshot : snapshot.child("/RFList/").getChildren()) {
                        String key = dataSnapshot.getKey();
                        String name = "" + dataSnapshot.getValue().toString();
                        Log.d("RF",name);
                        RFData rf = dataSnapshot.getValue(RFData.class);
                        ((RFAdapter) mAdapter).addChat(rf);
                    }
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });

         */





        mReference.child(uid).child("/RFList/").addChildEventListener(new ChildEventListener() {

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

        /*장보기 리스트 버튼*/
        Button listBtn = findViewById(R.id.listBtn);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GroceryListActivity.class);

                startActivity(intent);
            }
        });

        /*채팅 버튼. TODO:채팅 실행 확인을 위해 임의로 채팅버튼을 추가한 거라 채팅 구현 시 지우기*/
        Button chattingBtn = findViewById(R.id.chattingBtn);
        chattingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

    }

    // Data storage and modification method
    public void postFirebaseDataBase(boolean add) {

        reference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            FirebasePost post = new FirebasePost(id, name);
            postValues = post.toMap();
        }

        childUpdates.put("/Test/" , postValues);
        reference.updateChildren(childUpdates);
    }

    // Save to Firebase
    public void save() {
        id = "user";
        name = "test";
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        public String id;
        public String name;

        public FirebasePost(String memory, String date) {
            this.id = memory;
            this.name = date;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("id", id);
            result.put("name", name);
            return result;
        }

    }


}