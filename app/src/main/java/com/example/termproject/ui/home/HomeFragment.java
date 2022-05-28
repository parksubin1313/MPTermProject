package com.example.termproject.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.termproject.adapter.AllFridgeAdapter;
import com.example.termproject.domain.DetailFridge;
import com.example.termproject.domain.Food;
import com.example.termproject.MyFridgeActivity;
import com.example.termproject.R;
import com.example.termproject.databinding.FragmentHomeBinding;
import com.example.termproject.domain.MyFridge;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

public class HomeFragment extends Fragment {

    String TAG = "HomeFragment";
    private ArrayList<Food> foodList;


    private FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AllFridgeAdapter AFadapter;

    private FragmentHomeBinding binding;

    public HomeFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        return root;
        Log.d("HomeFragment", "들어옴");
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_home , container, false);

        foodList = new ArrayList<>();

        ListView listView= (ListView) rootView.findViewById(R.id.fridgeList_listView);

        AFadapter = new AllFridgeAdapter();
        listView.setAdapter(AFadapter);

        db.collection("All_Fridge").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //추가될 AllFridge
                        DetailFridge tempContent = document.toObject(DetailFridge.class);
                        //User -> 내 냉장고 list 불러와서
                        if(MyFridgeCheck(tempContent.getUserList())) {
                            //냉장고가 되어야 함
//                            TODO: 해결해야 함
//                            AFadapter.addItem(tempContent);
                        }
                    }
                    AFadapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //내 냉장고로 이동
                Intent intent = new Intent(getActivity(), MyFridgeActivity.class);
                startActivity(intent);
            }
        });
        //냉장고 등록하기 버튼
        Button add = (Button) rootView.findViewById(R.id.fridgeList_add_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 냉장고 추가하기 activity 로 이동
            }
        });

        return rootView;

    }

    @Override
    public void onResume() {
        AFadapter.notifyDataSetChanged();
        Log.e(TAG, " : " + AFadapter.getCount());
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public boolean MyFridgeCheck(ArrayList<String> userList){
        String myUid = curUser.getUid();
        for(String memberUid : userList){
            if(myUid.equals(memberUid))
                //내 냉장고임
                return true;
        }
        return false;
    }
    
}