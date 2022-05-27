package com.example.termproject.ui.dashboard;

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

import com.example.termproject.AddFoodActivity;
import com.example.termproject.R;
import com.example.termproject.databinding.FragmentDashboardBinding;

/**
 * @description 장바구니 fragment
 */
public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Button add_shoppingList = (Button) v.findViewById(R.id.add_shoppingList);

        add_shoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "구매할 음식 추가", Toast.LENGTH_SHORT).show();
                mOnPopupClick(view);
            }
        });
        return v;
    }

    public void mOnPopupClick(View v){
        //음식 추가하는 팝업(엑티비티)호출
        Intent intent = new Intent(getContext(), AddFoodActivity.class);
        //intent.putExtra("data", "Test addFood Popup");
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

/**
 * 되면 지워
 */
//public class DashboardFragment extends Fragment {
//
//    private FragmentDashboardBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
////        DashboardViewModel dashboardViewModel =
////                new ViewModelProvider(this).get(DashboardViewModel.class);
////
////        binding = FragmentDashboardBinding.inflate(inflater, container, false);
////        View root = binding.getRoot();
////
////        final TextView textView = binding.textDashboard;
////        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
////        return root;
//
//        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
//
//        Button addFoodBtn = (Button) v.findViewById(R.id.addFoodBtn);
//
//        addFoodBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "음식 추가", Toast.LENGTH_SHORT).show();
//                mOnPopupClick(view);
//            }
//        });
//        return v;
//    }
//
//    public void mOnPopupClick(View v){
//        //음식 추가하는 팝업(엑티비티)호출
//        Intent intent = new Intent(getContext(), AddFoodActivity.class);
//        //intent.putExtra("data", "Test addFood Popup");
//        startActivity(intent);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}