package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroceryListActivity extends AppCompatActivity {
    private ListView groceryList;

    String foodName;
    String grocery;
    String uid;
    /*DB-Firebase*/
    DatabaseReference reference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<FoodItem> foodItemArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);


        /*Firebase User*/
        //TODO: 필요없으면 지우기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Get information of logged in user
        uid = user != null ? user.getUid() : null;

        /*Firebase*/
        mReference.child(uid).child("/RFList/").child("/grocery/").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("식품 추가", snapshot.getValue().toString());
                FoodItem foodItem = snapshot.getValue(FoodItem.class);
                ((FoodAdapter) mAdapter).addChat(foodItem);

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


        Button addFoodBtn = findViewById(R.id.addFoodBtn);

        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "음식 추가", Toast.LENGTH_SHORT).show();
                mOnPopupClick(view);
            }
        });



/*
        //MEMO: 어댑터 연습.

        groceryList = (ListView)findViewById(R.id.addFood_listView);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        groceryList.setAdapter(adapter);

        data.add("사과");
        data.add("우유");
        adapter.notifyDataSetChanged();*/
    }

    public void mOnPopupClick(View v){
        //음식 추가하는 팝업(엑티비티)호출
        Intent intent = new Intent(this, AddFoodActivity.class);
        //intent.putExtra("data", "Test addFood Popup");
        startActivity(intent);
    }

    /*Firebase에 Data 저장하기*/
    public void postFirebaseDataBase(boolean add){
        reference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            FirebasePost post = new FirebasePost(foodName);
            postValues = post.toMap();
        }
        childUpdates.put("/addFood_test/", postValues);
        reference.updateChildren(childUpdates);
    }

    // Save to Firebase
    public void save() {
        foodName = "food_name";
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        public String foodName;

        public FirebasePost(String foodName) {
            this.foodName = foodName;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("foodName", foodName);
            return result;
        }

    }


    //TODO: 디폴트로 editText 키보드 안보이게
}