//package com.example.termproject;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//public class testZxing extends AppCompatActivity {
//
//    DatabaseReference reference;
//    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
//    private DatabaseReference apiReference = mDatabase.getReference("/API/");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_zxing);
//
//        IntentIntegrator integrator = new IntentIntegrator(testZxing.this);
//        integrator.setOrientationLocked(false);
//        integrator.setPrompt("바코드 및 QR코드 등록을 위해\n상자안에 위치시켜 주세요\n\n");  // 밑에 문구 수정 가능
//        integrator.initiateScan();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                //result.getContents 를 이용 데이터 재가공
//
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//
////                TODO: 해당 바코드가 api 에서 있는지 찾는 함수
////                apiReference.addListenerForSingleValueEvent(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(DataSnapshot snapshot) {
////                        if (snapshot.hasChild("BAR_CD")==result.getContents().toString()) {
////                            // run some code
////                        }
////                    }
////                });
//
////                if(apiReference.orderByChild("BAR_CD").equalTo("8805522061018") != null){
////                    Toast.makeText(this, "상품명", Toast.LENGTH_SHORT).show();
////                }
////
////                Query thisAPIQuery = apiReference.orderByChild("BAR_CD").equalTo(result.getContents().toString());
////                thisAPIQuery.addChildEventListener(new ChildEventListener() {
////                    @Override
////                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////                    }
////
////                    @Override
////                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////                    }
////
////                    @Override
////                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
////
////                    }
////
////                    @Override
////                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////
////                    }
////                });
////
////                thisAPIQuery.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                        for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
////
////                        }
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError databaseError) {
////                        Log.w("onCancelled", "loadPost:onCancelled", databaseError.toException());
////                    }
////                });
////
////            }
////        } else {
////            super.onActivityResult(requestCode, resultCode, data);
////        }
//    }}}}