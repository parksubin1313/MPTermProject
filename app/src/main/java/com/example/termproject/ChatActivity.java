package com.example.termproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termproject.adapter.ChatAdapter;
import com.example.termproject.data.ChatData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView listView;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference = mDatabase.getReference();

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private String uid = user != null ? user.getUid() : null;

    private TextView food;
    private ArrayList<ChatData> chatList = new ArrayList<>();
    private ChatAdapter adapter;
    private String nickname;

    private EditText EditText_chat;
    private Button Button_send;

    //Firebase Database 관리 객체참조변수
    private FirebaseDatabase firebaseDatabase;

    //'chat'노드의 참조객체 참조변수
    private DatabaseReference myRef, myRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cum_chatting);

        mReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("hehehe", "uid 있음 - 1");

                if (snapshot.hasChild(uid)) {
                    Log.e("hehehe", "uid 있음");
                    for (DataSnapshot dataSnapshot : snapshot.child(uid).getChildren()) {
                        String key = dataSnapshot.getKey();
                        if (key.equals("nickName")) {
                            nickname = "" + dataSnapshot.getValue().toString();
                            Log.e("hehehe", nickname);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        listView = findViewById(R.id.chatting_listView);
        adapter = new ChatAdapter(chatList, getLayoutInflater());
        listView.setAdapter(adapter);

        food = (TextView) findViewById(R.id.foodname);
        Button_send = findViewById(R.id.Button_send);
        EditText_chat = findViewById(R.id.EditText_chat);

        Intent intent = getIntent();
        String foodname = intent.getStringExtra("food");
        food.setText(foodname);

        //Firebase DB관리 객체와 'caht'노드 참조객체 얻어오기
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Chat").child(foodname);
        myRef2 = firebaseDatabase.getReference();

        //firebaseDB에서 채팅 메세지들 실시간 읽어오기..
        //'chat'노드에 저장되어 있는 데이터들을 읽어오기
        //chatRef에 데이터가 변경되는 것으 듣는 리스너 추가
        myRef.addChildEventListener(new ChildEventListener() {
            //새로 추가된 것만 줌 ValueListener는 하나의 값만 바뀌어도 처음부터 다시 값을 줌
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //새로 추가된 데이터(값 : MessageItem객체) 가져오기
                ChatData chatData= dataSnapshot.getValue(ChatData.class);
                //새로운 메세지를 리스뷰에 추가하기 위해 ArrayList에 추가
                chatList.add(chatData);
                //리스트뷰를 갱신
                adapter.notifyDataSetChanged();
                listView.setSelection(chatList.size()-1); //리스트뷰의 마지막 위치로 스크롤 위치 이동
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //샌드 눌렀을떄
        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = EditText_chat.getText().toString(); //msg
                Log.e("hehehe", "버튼눌림");
                EditText_chat.setText(null);
                //널이 아닐때만 값전송하게
                if (msg != null) {
                    Log.e("hehehe", "널아님");
                    Log.e("hehehe", "uid is "+uid);

                    ChatData chat = new ChatData();

                    mReference.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.e("hehehe", "uid 있음 - 1");

                            if (snapshot.hasChild("0")) {
                                Log.e("hehehe", "uid 있음");
                                for (DataSnapshot dataSnapshot : snapshot.child(uid).getChildren()) {
                                    String key = dataSnapshot.getKey();
                                    if (key.equals("nickName")) {
                                        nickname = "" + dataSnapshot.getValue().toString();
                                        chat.setNickname(nickname);
                                        Log.e("hehehe", nickname);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    /*닉네임으로 띄우기*/
                    chat.setNickname(getNick());
                    chat.setMsg(msg);
                    chat.setUid(uid);

                    //메세지 작성 시간 문자열로..
                    Calendar calendar = Calendar.getInstance(); //현재 시간을 가지고 있는 객체
                    String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE); //00:00
                    chat.setTime(time);

                    myRef.push().setValue(chat);
                }
            }
        });
    }

    public String getNick()
    {
        mReference.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.e("hehehe", "uid 있음 - 1");

                if (snapshot.hasChild("0")) {
                    Log.e("hehehe", "uid 있음");
                    for (DataSnapshot dataSnapshot : snapshot.child(uid).getChildren()) {
                        String key = dataSnapshot.getKey();
                        if (key.equals("nickName")) {
                            nickname = "" + dataSnapshot.getValue().toString();
                            Log.e("hehehe", nickname);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return nickname;
    }
}
