package com.example.termproject.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.termproject.Food;
import com.example.termproject.GroceryListActivity;
import com.example.termproject.ListRF;
import com.example.termproject.R;
import com.example.termproject.SettingRF;
import com.example.termproject.databinding.FragmentHomeBinding;
import com.example.termproject.testZxing;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DatabaseReference myRef;

    DatabaseReference reference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();
    private DatabaseReference apiReference = mDatabase.getReference("/API/");

    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    myRef =

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        View v = inflater.inflate(R.layout.fragment_home, container, false);

//        된다된다!!!!! 추가 버튼되면 이 바코드는 없애도 됨
        Button barcodeBtn = (Button) v.findViewById(R.id.barcodeBtn);
        barcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "바코드 버튼 클릭", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), testZxing.class);

                startActivity(intent);
            }
        });
//        //바코드 버튼 클릭
//        public void mOnBarcode(View v){
//            Toast.makeText(getContext(), "바코드 버튼 클릭", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getContext(), testZxing.class);
//
//            startActivity(intent);
//
//        }

        Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save();
                //Intent intent = new Intent(MainActivity.this, checkNakigi.class);
                Intent intent = new Intent(getContext(), SettingRF.class);
                startActivity(intent);
            }
        });

        return v;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}