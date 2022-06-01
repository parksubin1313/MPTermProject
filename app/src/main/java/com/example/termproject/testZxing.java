package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.termproject.MyFridgeActivity.fName;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class testZxing extends AppCompatActivity {

    DatabaseReference reference;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference apiReference = mDatabase.getReference();
    String bcd;
    String PRDname;
    String rfName = fName;
    String code;

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
        //String result = "8807617070554"; //고구마전분맛

        if(result != null) {
            if(result == null) {
                //Toast.makeText(this, "다시 한 번 바코드를 상자 안에 위치시켜주세요.", Toast.LENGTH_LONG).show();
            } else {
                //result.getContents 를 이용 데이터 재가공

                //Toast.makeText(this, "Scanned: " + result, Toast.LENGTH_LONG).show();

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
                                            //Log.e("key", PRDname);
                                            Toast.makeText(getApplicationContext(), PRDname, Toast.LENGTH_LONG).show();
                                            Intent myIntent = new Intent(getApplicationContext(), AddFoodActivity.class);
                                            myIntent.putExtra("PRDname", PRDname);
                                            startActivity(myIntent);
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
                        //Toast.makeText(getApplicationContext(), "등록되지 않은 상품입니다.\n 상품명을 직접 입력해주세요.", Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(getApplicationContext(), AddFoodActivity.class);
                        myIntent.putExtra("PRDname", "");
                        startActivity(myIntent);
                        break;
                    }

                }
            }

        }

    }


}
