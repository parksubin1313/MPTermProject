package com.example.termproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RFAdapter extends RecyclerView.Adapter<RFAdapter.MyViewHolder> {

    private List<RFData> RFList;
    private String nick1="user";

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nick;
        public TextView name;
        public View rootView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //nick = itemView.findViewById(R.id.TextView_msg);
            name = itemView.findViewById(R.id.title);
            rootView = itemView;

/*            itemView.setClickable(true);
            itemView.setEnabled(true);*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strText = name.getText().toString();
                    Toast.makeText(name.getContext(), strText, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public RFAdapter(List<RFData> myDataSet, Context context){

        RFList=myDataSet;
        //this.nick1=myNickName;
    }

    @NonNull
    @Override
    public RFAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_rfitem,parent,false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RFData rf = RFList.get(position);

        //holder.nick.setText(chat.getNickname());
        holder.name.setText(rf.getName());

    }



    @Override
    public int getItemCount() {
        return RFList==null ? 0 : RFList.size();
    }

    public RFData getChat(int position){

        return RFList != null ? RFList.get(position) : null;
    }

    public void addChat(RFData rf){
        RFList.add(rf);
        notifyItemInserted(RFList.size()-1);
    }

}


