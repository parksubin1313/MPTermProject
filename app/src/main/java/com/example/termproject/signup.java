package com.example.termproject;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.termproject.domain.Member;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//
//public class signup extends AppCompatActivity {
//
//
//    EditText Email, Password, Nickname;
//    Button btnSignup;
//    static final String TAG = "SignUp";
//    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    FirebaseUser user;
//    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        btnSignup = findViewById(R.id.btnSignup);
//        Email = findViewById(R.id.SignUpEmailEditText);
//        Password = findViewById(R.id.SignUpPasswordEditText);
//        Nickname = findViewById(R.id.SignUpNicknameEditText);
//
//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String EmailValue, PasswordValue, NicknameValue, NameValue, BirthDateValue;
//                EmailValue = Email.getText().toString();
//                PasswordValue = Password.getText().toString();
//                NicknameValue = Nickname.getText().toString();
//
//                if (conditionChecker(EmailValue, PasswordValue, NicknameValue)) {
//                    Member member = new Member(EmailValue, NicknameValue);
//                    SignUp(member, PasswordValue);
//                } else {
//                    Log.e("signUpError", "Fail to SignUp : check the condition");
//                }
//            }
//        });
//    }
//
//    private boolean conditionChecker(String EmailValue, String PasswordValue, String NicknameValue){// 회원가입 조건들. 추가 예정
//        if (EmailValue.length() != 0 && PasswordValue.length() != 0 && NicknameValue.length() != 0)
//            return true;
//        else
//            return false;
//    }
//
//
//    private void SignUp(Member member, String PasswordValue) {
//        firebaseAuth.createUserWithEmailAndPassword(member.getEmail(), PasswordValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Log.e("SignUp", "계정생성 성공");
//                    // 생성한 계정의 firebaseAuth 바로 가져오기. uid를 얻기 위함.
//                    user = firebaseAuth.getCurrentUser();
//                    // 계정 생성에 성공할 경우만 DB에 정보를 저장.
//                    firebaseFirestore.collection("user").document(user.getUid()).set(member).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Log.e("SignUp", "회원가입 완료");
//                                StartActivity(MainActivity.class);
//                            } else {
//                                Log.e("SignUp", "회원가입 실패");
//                            }
//                        }
//                    });
//                } else {
//                    Log.e("SignUp", "계정생성 실패");
//                }
//            }
//        });
//    }
//
//
//    private void StartActivity(Class c) {
//        Intent intent = new Intent(this, c);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }
//}


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
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

public class signup extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword, editTextNickname;
    Button buttonSignup;

//    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
//    private DatabaseReference mReference = mDatabase.getReference();


    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user;
    FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();

    ProgressDialog progressDialog;
    //define firebase object
//    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        //initializig firebase auth object
//        firebaseAuth = FirebaseAuth.getInstance();

        /*
        if (firebaseAuth.getCurrentUser() != null) {
            //이미 로그인 되었다면 이 액티비티를 종료함
            finish();
            //그리고 profile 액티비티를 연다.
            startActivity(new Intent(getApplicationContext(), nagiki.class)); //추가해 줄 ProfileActivity
        }
        */


        //initializing views
        editTextEmail = (EditText) findViewById(R.id.SignUpEmailEditText);
        editTextPassword = (EditText) findViewById(R.id.SignUpPasswordEditText);
        editTextNickname = (EditText) findViewById(R.id.SignUpNicknameEditText);

        buttonSignup = (Button) findViewById(R.id.btnSignup);
//        progressDialog = new ProgressDialog(this);

        //button click event
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    //Firebse creating a new user
    private void registerUser() {
        //사용자가 입력하는 email, password를 가져온다.
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // !! 챙 : 닉네임 받아오는것도 추가
        String nickname = editTextNickname.getText().toString().trim();

        //email과 password가 비었는지 아닌지를 체크 한다.
        // !! 챙 : 닉네임 추가 + isEmpty라면 return; 각각 추가해줬음
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nickname)) {
            Toast.makeText(this, "Nickname를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO : email, nickname 중복검사를 여기서 실행?
        // TODO : firebase에 데이터 등록하는걸 추가 먼저 하고, 그 추가하는 과정에서 중복 검사 코드 넣어야 할 듯

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
                            Toast.makeText(getApplicationContext(), "회원가입 성공",Toast.LENGTH_SHORT).show();
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
                });


    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignup) {
            //TODO
            registerUser();
        }
    }

    /*
    //button click event
    @Override
    public void onClick(View view) {
        if(view == buttonSignup) {
            registerUser();
        }
    }

     */
}