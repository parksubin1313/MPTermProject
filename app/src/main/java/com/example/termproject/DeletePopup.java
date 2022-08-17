package com.example.termproject;

import static com.example.termproject.MyFridgeActivity.fName;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.termproject.ui.dashboard.DashboardFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeletePopup extends Activity {

    public String index, root;
    public String storageWay="0";

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseUser user = firebaseAuth.getCurrentUser();
    String uid = user != null ? user.getUid() : null;

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_popup);

        Button yes = findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popIntent = getIntent();
                storageWay = popIntent.getStringExtra("storageWay");
                root = popIntent.getStringExtra("root");
                index = popIntent.getStringExtra("index");
                Log.e("delete", root);
                deleteData(root,index,storageWay);
                finish();
            }
        });

        Button no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //삭제 제일 마지막 것만 됨!!!!!
    private void deleteData(String root, String index, String storageWay) {

        if(root.equals("shoppingList"))
        {
            mReference.child("USER").child(uid).child("shoppingList").child(index).removeValue();
        }
        else if(root.equals("RFList"))
        {
            mReference.child("USER").child(uid).child("RFList").child(index).removeValue();
        }
        else if(storageWay.equals("1") )
        {
            mReference.child("RFList").child(root).child("food").child("냉장").child(index).removeValue();
        }
        else if(storageWay.equals("2"))
        {
            mReference.child("RFList").child(root).child("food").child("냉동").child(index).removeValue();

        }

    }
}