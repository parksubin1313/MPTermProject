package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InviteUser extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();
    private DatabaseReference myRef;
    String uid;
    String rfName = "rfrf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_user);

        EditText user = findViewById(R.id.user);
        String name = user.getText().toString();

        Button invite = findViewById(R.id.button);
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mReference.child("USER").child(name).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChild("nickname")) {
                            for (DataSnapshot dataSnapshot : snapshot.child("nickname").getChildren()) {
                                String key = dataSnapshot.getKey();
                                uid = "" + dataSnapshot.getValue();
                                Log.d("subin", uid);

                                RFData rf = new RFData();
                                //chat.setNickname("user");
                                rf.setName(rfName);
                                mReference.child(uid).child("/RFList/").push().setValue(rf);
                            }
                        } else {
                            Log.d("subin", name);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


}