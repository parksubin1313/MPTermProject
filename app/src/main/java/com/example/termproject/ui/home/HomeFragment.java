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
import com.example.termproject.DeletePopup;
import com.example.termproject.MyFridgeActivity;
import com.example.termproject.R;
import com.example.termproject.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    String TAG = "HomeFragment";

    private FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    private ListView listView;

    private FragmentHomeBinding binding;

    String uid = curUser != null ? curUser.getUid() : null;

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

        Log.d("HomeFragment", "들어옴");
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_home , container, false);

        listView= (ListView) rootView.findViewById(R.id.fridgeList_listView);

        //냉장고 리스트 보이게
        mReference.child("USER").child(uid).child("RFList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> data = new ArrayList<>();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
                listView.setAdapter(adapter);

                for(int i=1; i<100; i++)
                {
                    if(snapshot.hasChild(Integer.toString(i))){
                        for(DataSnapshot dataSnapshot : snapshot.child(Integer.toString(i)).getChildren()){
                            String key = dataSnapshot.getKey();

                            if(key.equals("name")){
                                String name=""+dataSnapshot.getValue().toString();
                                data.add(name);
                                Log.e("name", name);
                            }
//                          이걸 해줘야 add 가 반영됨
                            adapter.notifyDataSetChanged();
                        }
                    }
                    else{
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //선택하면 해당 냉장고로 이동
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                mReference.child("USER").child(uid).child("RFList").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(Integer.toString(index+1))){
                            for(DataSnapshot dataSnapshot : snapshot.child(Integer.toString(index+1)).getChildren())
                            {
                                String key = dataSnapshot.getKey();

                                if(key.equals("name")){
                                    String name = "" + dataSnapshot.getValue().toString();
                                    Log.e("gg", name);
                                    Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MyFridgeActivity.class);
                                    intent.putExtra("fName",name);
                                    startActivity(intent);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        //길게 클릭하면 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), DeletePopup.class);
                intent.putExtra("index", Integer.toString(i+1));
                intent.putExtra("root", "RFList");
                startActivity(intent);

                return true;
            }
        });

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