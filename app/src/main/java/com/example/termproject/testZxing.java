package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;

public class testZxing extends AppCompatActivity {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference apiReference = mDatabase.getReference();
    String bcd;
    String PRDname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_zxing);

        IntentIntegrator integrator = new IntentIntegrator(testZxing.this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("바코드 및 QR코드 등록을 위해\n상자안에 위치시켜 주세요\n\n");  // 밑에 문구 수정 가능
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data).getContents().toString();

        if(result != null) {
            if(result == null) {
                Toast.makeText(this, "다시 한 번 바코드를 상자 안에 위치시켜주세요.", Toast.LENGTH_LONG).show();
            } else {

                //DB에서 코드에 맞는 상품명 찾기
                for(int i=0; i<500; i++){

                    int n = i;

                    apiReference.child("/API/").child("-N2wsZkwHlBnOc2MuWuF").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            String num = Integer.toString(n);

                            if (snapshot.hasChild(num)){
                                for(DataSnapshot dataSnapshot : snapshot.child(num).getChildren()){

                                    String key = dataSnapshot.getKey();

                                    if(key.equals("BAR_CD"))
                                    {
                                        bcd =""+dataSnapshot.getValue();

                                    }else{}

                                    if(bcd.equals(result)){

                                        if(key.equals("PRDLST_NM"))
                                        {
                                            PRDname = ""+dataSnapshot.getValue();
                                            Toast.makeText(getApplicationContext(), PRDname, Toast.LENGTH_LONG).show();
                                            Intent myIntent = new Intent(getApplicationContext(), AddFoodActivity.class);
                                            myIntent.putExtra("PRDname", PRDname);
                                            startActivity(myIntent);
                                            finish();
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    if(n==499)
                    {
                        Intent myIntent = new Intent(getApplicationContext(), AddFoodActivity.class);
                        myIntent.putExtra("PRDname", "");
                        startActivity(myIntent);
                        finish();
                        break;
                    }

                }
            }

        }

    }

}
