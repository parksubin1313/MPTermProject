package com.example.termproject.ui.dashboard;

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

import com.example.termproject.AddFoodActivity;
import com.example.termproject.AddShoppingActivity;
import com.example.termproject.DeletePopup;
import com.example.termproject.R;
import com.example.termproject.databinding.FragmentDashboardBinding;
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

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ListView listView;

    private FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();
    String uid = curUser != null ? curUser.getUid() : null;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.menu_add_shopping, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getActivity(), "checked!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), AddShoppingActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d("HomeFragment", "들어옴");
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_dashboard , container, false);

        listView= (ListView) rootView.findViewById(R.id.shoppingList_listView);

        mReference.child("USER").child(uid).child("shoppingList").addValueEventListener(new ValueEventListener() {
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

                            if(key.equals("productName")){
                                String name=""+dataSnapshot.getValue().toString();
                                data.add(name);
                                Log.e("name", name);
                            }
//                          이걸 해줘야 add 가 반영됨
                            adapter.notifyDataSetChanged();
                        }
                    }
                    else{
                        break;
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                mReference.child("USER").child(uid).child("shoppingList").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(Integer.toString(index+1))){
                            for(DataSnapshot dataSnapshot : snapshot.child(Integer.toString(index+1)).getChildren())
                            {
                                String key = dataSnapshot.getKey();

                                if(key.equals("productName")){
                                    String pName = "" + dataSnapshot.getValue().toString();
                                    Toast.makeText(getActivity(), pName + " checked!", Toast.LENGTH_SHORT).show();
                                    Log.e("gg", pName);
                                    //Intent intent = new Intent(getActivity(), MyFridgeActivity.class);
                                    //intent.putExtra("fName",name);
                                    //startActivity(intent);
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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), DeletePopup.class);
                intent.putExtra("index", Integer.toString(i+1));
                intent.putExtra("root", "shoppingList");
                startActivity(intent);

                return true;
            }
        });

        setHasOptionsMenu(true);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}