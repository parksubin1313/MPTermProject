package com.example.termproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import static com.example.termproject.MyFridgeActivity.fName;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.termproject.domain.foodItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class myFridge_freeze extends Fragment{

    private View view;

    String frName = fName;

    Button btnInvite;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();


    public static myFridge_freeze newInstance(){
        myFridge_freeze mff = new myFridge_freeze();
        return mff;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_my_fridge_freeze, container, false);


//        List<String> data = new ArrayList<>();
//        if(listView != null){
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
//            listView.setAdapter(adapter);
//
//            for(int i=0; i<15; i++){
//                String fName = "freeze : " +(i+1);
//                data.add(fName);
//            }
////        이걸 해줘야 add 가 반영됨
//            adapter.notifyDataSetChanged();
//        }
//        else{
//            Log.d("myFridge_freeze.java", "null 임");
//        }
////        listView.setAdapter(AFadapter);
//


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = (ListView) view.findViewById(R.id.myFridge_freeze_listView);

        mReference.child("RFList").child(frName).child("food").child("냉동").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<foodItem> food = new ArrayList<>();
                foodAdapter adapter;

                for (int i = 1; i < 100; i++) {
                    if (snapshot.hasChild(Integer.toString(i))) {
                        String pName="", dd ="";

                        for (DataSnapshot dataSnapshot : snapshot.child(Integer.toString(i)).getChildren()) {
                            String key = dataSnapshot.getKey();

                            if (key.equals("productName")) {
                                pName = "" + dataSnapshot.getValue().toString();
                                Log.e("name", pName);
                            }

                            if(key.equals("dueDate"))
                            {
                                String date = "" + dataSnapshot.getValue().toString();
                                try {
                                    dd = calDD(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        food.add(new foodItem("냉동", pName, dd));
                    } else {
                        break;
                    }
                }
                adapter = new foodAdapter(getContext(), food);
                listView.setAdapter(adapter);
                //이걸 해줘야 add 가 반영됨
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // 클릭이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                mReference.child("RFList").child(frName).child("food").child("냉동").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(Integer.toString(index + 1))) {
                            for (DataSnapshot dataSnapshot : snapshot.child(Integer.toString(index + 1)).getChildren()) {
                                String key = dataSnapshot.getKey();

                                if (key.equals("productName")) {
                                    String pName = "" + dataSnapshot.getValue().toString();
                                    Log.e("gg", pName);
                                    Toast.makeText(getActivity(), pName + " 클릭", Toast.LENGTH_SHORT).show();
                                    //Intent intent = new Intent(getActivity(), MyFridgeActivity.class);
                                    //intent.putExtra("fName",name);
                                    //startActivity(intent);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), DeletePopup.class);
                intent.putExtra("index", Integer.toString(i+1));
                intent.putExtra("root", frName);
                intent.putExtra("storageWay", "2");
                startActivity(intent);

                return true;
            }
        });

        /*
        btnInvite = (Button) view.findViewById(R.id.button);

        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InviteUser.class);
                startActivity(intent);
            }
        });

         */

    }

    //어댑터
    public class foodAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener{

        private Context context;
        private List list;

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
        }

        class ViewHolder{
            public TextView foodName, dueDate;
        }

        public foodAdapter(Context context, ArrayList list){
            super(context, 0, list);
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            final ViewHolder viewHolder;

            if(convertView == null){
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                convertView = layoutInflater.inflate(R.layout.food_item, parent, false);

            }

            viewHolder = new ViewHolder();
            viewHolder.foodName = (TextView) convertView.findViewById(R.id.foodName);
            viewHolder.dueDate = (TextView) convertView.findViewById(R.id.dueDate);

            final foodItem food = (foodItem) list.get(position);
            viewHolder.foodName.setText(food.getName());
            viewHolder.dueDate.setText(food.getDD());

            return convertView;
        }
    }

    public String calDD(String date) throws ParseException
    {
        String d = date;
        Date dueDay = new SimpleDateFormat("yyyy-MM-dd").parse(d);

        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

        long diffSec = (dueDay.getTime() - curDate.getTime()) / 1000; //초 차이
        //long diffMin = (dueDay.getTime() - curDate.getTime()) / 60000; //분 차이
        //long diffHor = (dueDay.getTime() - curDate.getTime()) / 3600000; //시 차이
        long diffDays = diffSec / (24*60*60); //일자수 차이

        String result;

        if(diffDays > 0)
        {
            result = "D-"+diffDays;
        }
        else if(diffDays==0)
        {
            result = "D-Day";
        }
        else
        {
            diffDays = Math.abs(diffDays);
            result = "D+"+diffDays;
        }

        return result;
    }


}