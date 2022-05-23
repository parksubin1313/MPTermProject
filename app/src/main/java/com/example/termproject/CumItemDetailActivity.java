package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class CumItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cum_item_detail);
//        Button chatBtn = findViewById(R.id.chatBtn);


//        chatBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CumItemDetailActivity.this, ChatActivity.class);
//                startActivity(intent);
//            }
//        });

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


}
