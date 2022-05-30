package com.example.termproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shopping);

        EditText shoppingName = findViewById(R.id.shopping_name_editText);
        Button shoppingAddBtn = findViewById(R.id.shoppingList_register_btn);

        shoppingAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), shoppingName.getText().toString() + " is added", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
