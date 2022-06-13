package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class signup extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword, editTextNickname;
    Button buttonSignup;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d("onCreate", "만들었을 때");
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null) {

            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            //initializing views

            Log.d("registerUser", "등록하자 제발");
            //사용자가 입력하는 email, password를 가져온다.

            //initializing views
            editTextEmail = (EditText) findViewById(R.id.SignUpEmailEditText);
            editTextPassword = (EditText) findViewById(R.id.SignUpPasswordEditText);
            editTextNickname = (EditText) findViewById(R.id.SignUpNicknameEditText);

            buttonSignup = (Button) findViewById(R.id.btnSignup);

            //button click event
            buttonSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("clickListener", "버튼 눌렀을 때");

                    registerUser();
                }
            });
        }
        else{
            Log.d("CurrentUser", "is NULL");
        }
    }

    //Firebse creating a new user
    private void registerUser() {
        Log.d("RegisterUser", "들어옴");


        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String nickName = editTextNickname.getText().toString().trim();

        Log.e("String email", email );
        Log.e("String pw", password);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    //email과 password가 비었는지 아닌지를 체크 한다.
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(nickName)) {
                        Toast.makeText(getApplicationContext(), "NickName를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                        return;

        //email과 password가 비었는지 아닌지를 체크 한다.
        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }

        //email과 password가 제대로 입력되어 있다면 계속 진행된다.
        progressDialog.setMessage("등록중입니다. 기다려 주세요...");
        progressDialog.show();

//        creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password) //회원가입
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Get information of logged in user
                            user = firebaseAuth.getCurrentUser();
//                            Toast.makeText(getApplicationContext(), "회원가입 성공",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            String uid = user != null ? user.getUid() : null;
                            /*
                            String nickname = editTextNickname.getText().toString();

                            if(uid != null){
                                //userData userInfo = new userData();
                                //userInfo.setUid(uid);
                                //userInfo.setNickname(nickname);
                                //mReference.child("/USER/").child(nickname).push().setValue(uid);


                            }

                             */
                        }
                        else {
                            //에러발생시
                            Toast.makeText(signup.this, "회원가입 에러!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }

                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    String uid = user.getUid();

                    Toast.makeText(getApplicationContext(), uid, Toast.LENGTH_SHORT).show();

                    user = firebaseAuth.getCurrentUser();

                    HashMap<Object, String> hashMap = new HashMap<>();

                    hashMap.put("uid", uid);
                    hashMap.put("email", email);
                    hashMap.put("pw", password);
                    hashMap.put("nickName", nickName);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Users");
                    reference.child(uid).setValue(hashMap);

                    Intent intent = new Intent(signup.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Log.e("SignUp", "돌아가기");
                }
            }
        });



    }

    @Override
    public void onClick(View view) {
        registerUser();
    }

}