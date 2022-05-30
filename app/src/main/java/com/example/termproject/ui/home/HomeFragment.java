package com.example.termproject.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.termproject.AddFridgeActivity;
import com.example.termproject.MyFridgeActivity;
import com.example.termproject.R;
import com.example.termproject.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    String TAG = "HomeFragment";

    private FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView listView;

    private FragmentHomeBinding binding;

    public HomeFragment(){}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.menu_add_fridge, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getActivity(), "fridge add clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), AddFridgeActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

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
//        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.my_fridge , container, false);
//        setContentView(R.layout.my_fridge);

//        TabLayout tabLayout = rootView.findViewById(R.id.myFridge_tabLayout);
//        ViewPager viewPager = rootView.findViewById(R.id.myFridge_viewPager);
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        vpAdapter = new VPAdapter(getParentFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        vpAdapter.addFragment(new myFridge_cool(), "냉장");
//        vpAdapter.addFragment(new myFridge_freeze(), "냉동");




        listView= (ListView) rootView.findViewById(R.id.fridgeList_listView);

//        AFadapter = new AllFridgeAdapter();
        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
//        listView.setAdapter(AFadapter);
        listView.setAdapter(adapter);

        for(int i=0; i<15; i++){
            String fName = "RF"+(i+1);
            data.add(fName);
        }
//        이걸 해줘야 add 가 반영됨
        adapter.notifyDataSetChanged();


//          TODO: DB 는 따로 혜균이 오면 손보자
//        db.collection("All_Fridge").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        //추가될 AllFridge
//                        DetailFridge tempContent = document.toObject(DetailFridge.class);
//                        //User -> 내 냉장고 list 불러와서
//                        if(MyFridgeCheck(tempContent.getUserList())) {
//                            //냉장고가 되어야 함
////                            TODO: 해결해야 함
////                            AFadapter.addItem(tempContent);
//                        }
//                    }
////                    AFadapter.notifyDataSetChanged();
//                }
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                //내 냉장고로 이동
                Intent intent = new Intent(getActivity(), MyFridgeActivity.class);
                startActivity(intent);
            }
        });
//        //냉장고 등록하기 버튼
//        Button add = (Button) rootView.findViewById(R.id.fridgeList_add_btn);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: 냉장고 추가하기 activity 로 이동
//                Intent intent = new Intent(getActivity(), AddFridgeActivity.class);
//                startActivity(intent);
//            }
//        });

        setHasOptionsMenu(true);

        return rootView;

    }

    @Override
    public void onResume() {
//        TODO: 이거 왜 오류남? 없어도 되냐?
//        AFadapter.notifyDataSetChanged();
//        Log.e(TAG, " : " + AFadapter.getCount());
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