package com.example.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = null;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;
    public static String access;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    //Email Login
    //define view objects
    EditText editTextEmail;
    EditText editTextPassword;
    TextView textviewMessage;
    ImageView buttonLogin;
    Button bntSingin;
    Button bntFindpw;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        AutomaticSignIn();


        ImageView signup = findViewById(R.id.btnSignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, checkNakigi.class);
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
                finish();
            }
        });

        //----------로그인------------
        //initializig firebase auth object
        mAuth = FirebaseAuth.getInstance();


        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (ImageView) findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(this);

        //button click event
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        //이것도 이제 필요없음
//        parseXML();
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("로그인중입니다. 잠시 기다려 주세요...");
        progressDialog.show();

        //logging in the user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            access=email;
                            Toast.makeText(getApplicationContext(), "로그인 성공",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, BottomNavigation.class);
//                            Intent intent = new Intent(MainActivity.this, ListRF.class);
//                            Intent intent = new Intent(MainActivity.this, AddFoodActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void AutomaticSignIn() {
        if(firebaseUser != null) {
            Log.d("자동로그인", "성공");
            Intent intent = new Intent(MainActivity.this, BottomNavigation.class);
            startActivity(intent);
        }
        else{
            Log.d("자동로그인", "실패");
        }
    }


//    이미 DB 에 올렸으니까 필요없음!!!!!!!!!!!!!!!!!
//    //open api 가져올 때 사용할 parseXML, precessParsing, printFood
//    //api 정보는 foods arraylist 에 있음 (Type : Food)
//    private void parseXML(){
//        XmlPullParserFactory parserFactory;
//        try {
//            parserFactory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = parserFactory.newPullParser();
//            InputStream is = getAssets().open("data.xml");
//            //InputStream is = getAssets().open("dataP.xml");
//            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//            parser.setInput(is, null);
//            precessParsing(parser);
//
//        } catch (XmlPullParserException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void precessParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
//
//        ArrayList<Food> foods = new ArrayList<>();
//        int eventType = parser.getEventType();
//        Food currentFood = null;
//
//        while (eventType != XmlPullParser.END_DOCUMENT) {
//            String eltName = null;
//
//            switch (eventType) {
//                case XmlPullParser.START_TAG:
//                    eltName = parser.getName();
//
//                    if ("row".equals(eltName)) {
//                        currentFood = new Food();
//                        foods.add(currentFood);
//                    } else if (currentFood != null) {
//                        if("PRDLST_NM".equals(eltName)){
//                            currentFood.PRDLST_NM = parser.nextText();
//                            //Log.d(TAG, "prdlst: " + currentFood.PRDLST_NM);
//                        }
//                        if ("BAR_CD".equals(eltName)) {
//                            currentFood.BAR_CD = parser.nextText();
//                            //Log.d(TAG, "barcode: " + currentFood.BAR_CD);
//                        }
//                    }
//                    break;
//            }
//            eventType = parser.next();
//        }
//        printFood(foods);
//    }
//    public void printFood(ArrayList<Food> foods){
//        StringBuilder builder = new StringBuilder();
//
//        for(Food fd : foods){
//            builder.append(fd.PRDLST_NM).append("\n").append(fd.BAR_CD).append("\n\n");
//        }
//        //txt로 출력할 때
//        //txt.setText(builder.toString());
//    }
}