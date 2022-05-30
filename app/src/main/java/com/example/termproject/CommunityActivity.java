package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_upload);
        Log.d("CommunityActivity ", "들어옴");

        EditText name = findViewById(R.id.ETName);
        EditText loc = findViewById(R.id.ETLoc);
        EditText tag = findViewById(R.id.ETTag);
        EditText detail = findViewById(R.id.ETDetail);
        ImageView imageView = findViewById(R.id.imageView);
//        Button btn = findViewById(R.id.uploadBtn);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), name.getText().toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), AddFridgeActivity.class);
//                startActivity(intent);
//            }
//        });


    }
}
