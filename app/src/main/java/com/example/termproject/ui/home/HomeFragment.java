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

import com.example.termproject.GroceryListActivity;
import com.example.termproject.ListRF;
import com.example.termproject.R;
import com.example.termproject.SettingRF;
import com.example.termproject.databinding.FragmentHomeBinding;
import com.example.termproject.testZxing;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

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

    //바코드 버튼 클릭
    public void mOnBarcode(View v){

        Toast.makeText(getContext(), "바코드 버튼 클릭", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), testZxing.class);

        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}