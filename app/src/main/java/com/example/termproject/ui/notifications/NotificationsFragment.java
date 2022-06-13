package com.example.termproject.ui.notifications;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.shapes.Shape;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.termproject.AddFoodActivity;
import com.example.termproject.AddFridgeActivity;
import com.example.termproject.CommunityActivity;
import com.example.termproject.MyFridgeActivity;
import com.example.termproject.R;
import com.example.termproject.databinding.FragmentNotificationsBinding;
import com.example.termproject.domain.communityItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ListView listView;
    SearchView searchView;
    ViewGroup rootView;
    ArrayAdapter<String> adapter;
    TextView ETDate_upload;
    Button saveButton;
    EditText name;
    EditText loc;
    EditText detail;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobile-programming-91257-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference mReference = mDatabase.getReference();

    String uid = user != null ? user.getUid() : null;
    String fname, date, location, description;

    String countString;
    int countNum;
    public static int cnt=1;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater inflater1 = getActivity().getMenuInflater();
        inflater1.inflate(R.menu.menu_add_community, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getActivity(), "community upload clicked", Toast.LENGTH_SHORT).show();
        //커스텀 dialog 로 바꿈
        CustomDialog dlg = new CustomDialog(getActivity());
        dlg.show();
        return super.onOptionsItemSelected(item);
    }

    class CustomDialog extends Dialog {

        private Context mContext;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.community_upload);

            // 다이얼로그의 배경을 투명으로 만든다.
            Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // 커스텀 다이얼로그의 각 위젯들을 정의한다.
            saveButton = findViewById(R.id.uploadBtn);
            name = findViewById(R.id.ETName);
            ETDate_upload = findViewById(R.id.ETDate_upload);
            loc = findViewById(R.id.ETLoc);
            detail = findViewById(R.id.ETDetail);

            ETDate_upload = (TextView) findViewById(R.id.ETDate_upload);
            Calendar cal = Calendar.getInstance();
            ETDate_upload.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));

            zero_save();

            DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    //Date Picker 에서 선택한 날짜를 TextView에 설정.

                    ETDate_upload.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
                }
            };

            /*유통기한 클릭시 실행 함수*/
            ETDate_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    new DatePickerDialog(getActivity(), mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
                }
            });


            // 버튼 리스너 설정
            saveButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // '확인' 버튼 클릭시
                    // ...코드..
                    //TODO: DB 에 올리기
                    save();
                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                    // Custom Dialog 종료
                    dismiss();
                }
            });

        }

        public CustomDialog(Context mContext) {
            super(mContext);
            this.mContext = mContext;
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d("HomeFragment", "들어옴");
        rootView= (ViewGroup) inflater.inflate(R.layout.fragment_notifications , container, false);
        searchView = (SearchView) rootView.findViewById(R.id.community_searchView);
        listView= (ListView) rootView.findViewById(R.id.communityList_listView);


        mReference.child("/Community List/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> data = new ArrayList<>();
                adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
                listView.setAdapter(adapter);

                for(int i=1; i<100; i++)
                {
                    if(snapshot.hasChild(Integer.toString(i))){

                        for(DataSnapshot dataSnapshot : snapshot.child(Integer.toString(i)).getChildren()){
                            String key = dataSnapshot.getKey();

                            if(key.equals("name")){
                                String name=""+dataSnapshot.getValue().toString();
                                data.add(name);
                            }
                        }
                        adapter.notifyDataSetChanged();
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

/*
//        AFadapter = new AllFridgeAdapter();
        List<String> data = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
//        listView.setAdapter(AFadapter);
        listView.setAdapter(adapter);

        String food[] = {"샐러드", "Pork", "Strawberry", "Mango", "Watermelon", "Cherry", "Cake", "Water", "Milk", "Egg", "Coke", "Cheese", "Apple", "Kimchi", "Sausage"};

        for(int i=0; i<15; i++){
            data.add(food[i].toString());
        }
//        이걸 해줘야 add 가 반영됨
        adapter.notifyDataSetChanged();

 */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent intent = new Intent(getActivity(), CommunityActivity.class);
                intent.putExtra("index", Integer.toString(index+1));
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                NotificationsFragment.this.adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotificationsFragment.this.adapter.getFilter().filter(s);
                return false;
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

    public int countDB(){

        mReference.child("/Community List/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //String count;

                for(int i=1; i<100; i++){

                    countString = Integer.toString(i);

                    if(!snapshot.hasChild(countString)){
                        break;
                    }
                    else{
                    }


                }

                countNum = Integer.parseInt(countString);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return countNum;
    }

    // Data storage and modification method
    public void postFirebaseDataBase(boolean add) {

        //reference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if (add) {
            FirebasePost post = new FirebasePost(uid,fname, date, location, description);
            postValues = post.toMap();
        }

        cnt = countDB();

        childUpdates.put("/Community List/"+ cnt + "/", postValues);
        mReference.updateChildren(childUpdates);
    }

    // 파이어베이스에 0이 자꾸 이상하게 담겨서....
    public void zero_save() {

        fname="";
        date="";
        location="";
        description="";
        postFirebaseDataBase(true);
    }

    // Save to Firebase
    public void save() {

        fname = name.getText().toString();
        date=ETDate_upload.getText().toString();
        location=loc.getText().toString();
        description=detail.getText().toString();
        postFirebaseDataBase(true);
    }

    public class FirebasePost {

        public String uid, fname, date, location, description;

        public FirebasePost(String uid,String fname, String date, String location, String description) {
            this.uid = uid;
            this.fname = fname;
            this.date = date;
            this.location = location;
            this.description = description;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("writerUID", uid);
            result.put("name", fname);
            result.put("dueDate", date);
            result.put("location", location);
            result.put("description", description);
            return result;
        }
    }

    //문자열 null인지 확인
    static boolean isStringEmpty(String str)
    {
        return str == null || str.trim().isEmpty();
    }

}