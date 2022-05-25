package com.example.termproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termproject.R;
import com.example.termproject.CommunityItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

//Community 에 내 상품 upload 할 때 그 추가 화면 (내 냉장고에서 가져오기 아님)

public class ComUpload extends AppCompatActivity {
    //모든 입력받은 값 + upload 버튼
    private EditText ETName;
    private TextView ETDate;
    private EditText ETLoc;
    private EditText ETTag;
    private EditText ETDetail;

    //firebase
    DatabaseReference reference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();
    private DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_upload);

        String uid;
        ETName = (EditText) findViewById(R.id.ETName);
        ETDate = (TextView) findViewById(R.id.ETDate);
        ETLoc = (EditText) findViewById(R.id.ETLoc);
        ETTag = (EditText) findViewById(R.id.ETTag);
        ETDetail = (EditText) findViewById(R.id.ETDetail);

        //데이터 가져오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Get information of logged in user
        uid = user != null ? user.getUid() : null;


    }

    /*유통기한*/
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            //Date Picker 에서 선택한 날짜를 TextView에 설정.
            ETDate.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
        }
    };

    public void mOnClick_DatePick(View v){
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }

    public void Upload(View v){
        if(ETName.getText().toString().length()==0 ||
                ETDate.getText().toString().length()==0 ||
                ETLoc.getText().toString().length()==0 ||
                ETTag.getText().toString().length()==0 ||
                ETDetail.getText().toString().length()==0) {
            Toast.makeText(getApplicationContext(), "모두 입력해주십시오.", Toast.LENGTH_SHORT).show();
        }
        else{
            CommunityItem CItem = new CommunityItem(ETName.getText().toString(), ETDate.getText().toString(), ETLoc.getText().toString(), ETTag.getText().toString(), ETDetail.getText().toString(), false);

            //TODO: database에 community 자리 만들고 그 리스트 안에 올리기

            //TODO: 개인의 community 안에도 올리기
        }
    }
}
