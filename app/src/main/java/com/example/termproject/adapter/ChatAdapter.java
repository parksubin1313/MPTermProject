package com.example.termproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termproject.R;
import com.example.termproject.data.ChatData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    private ArrayList<ChatData> chatList;
    LayoutInflater layoutInflater;

    public ChatAdapter(ArrayList<ChatData> chatList, LayoutInflater layoutInflater) {
        this.chatList = chatList;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //현재 보여줄 번째의(position)의 데이터로 뷰를 생성
        ChatData data = chatList.get(position);

        View itemView = null;

        //메세지가 내 메세지인지??
        if (data.getUid().equals(this.uid)) {//TODO: 닉네임 비교
            itemView = layoutInflater.inflate(R.layout.cum_chatting_mymsgbox, parent, false);

            TextView nickname = itemView.findViewById(R.id.tv_name);
            TextView msg = itemView.findViewById(R.id.tv_msg);
            TextView time = itemView.findViewById(R.id.tv_time);


            /*닉네임으로 띄우기*/
            //String user1 = "이매";
            //nickname.setText(user1);
            nickname.setText(data.getNickname());
//            String user1 = user.getDisplayName();

            msg.setText(data.getMsg());
            time.setText(data.getTime());
        } else {
            itemView = layoutInflater.inflate(R.layout.cum_chatting_othermsgbox, parent, false);

            TextView nickname = itemView.findViewById(R.id.tv_name);
            TextView msg = itemView.findViewById(R.id.tv_msg);
            TextView time = itemView.findViewById(R.id.tv_time);

            /*닉네임으로 띄우기*/
            //String user2 = "쵯";
            //nickname.setText(user2);
            nickname.setText(data.getNickname());

            msg.setText(data.getMsg());
            time.setText(data.getTime());
        }
        return itemView;
    }
}