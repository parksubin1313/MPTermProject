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
import com.example.termproject.CommunityActivity;
import com.example.termproject.MyFridgeActivity;
import com.example.termproject.R;
import com.example.termproject.databinding.FragmentNotificationsBinding;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ListView listView;
    SearchView searchView;
    ViewGroup rootView;
    ArrayAdapter<String> adapter;
    TextView ETDate_upload;

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
            Button saveButton = findViewById(R.id.uploadBtn);
            EditText name = findViewById(R.id.ETName);
            ETDate_upload = findViewById(R.id.ETDate_upload);
            EditText loc = findViewById(R.id.ETLoc);
            EditText detail = findViewById(R.id.ETDetail);

            ETDate_upload = (TextView) findViewById(R.id.ETDate_upload);
            Calendar cal = Calendar.getInstance();
            ETDate_upload.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));

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
//        NotificationsViewModel notificationsViewModel =
//                new ViewModelProvider(this).get(NotificationsViewModel.class);
//
//        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();



//        /*유통기한 날짜 선택하면 뜨는 DatePicker*/
//        DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
//                //Date Picker 에서 선택한 날짜를 TextView에 설정.
//                ETDate = (TextView) findViewById(R.id.ETDate_upload);
//                ETDate.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
//            }
//        };
//
//        /*유통기한 클릭시 실행 함수*/
//        public void mOnClick_DatePick(View v){
//            Calendar cal = Calendar.getInstance();
//            new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
//        }

        Log.d("HomeFragment", "들어옴");
        rootView= (ViewGroup) inflater.inflate(R.layout.fragment_notifications , container, false);
        searchView = (SearchView) rootView.findViewById(R.id.community_searchView);
        listView= (ListView) rootView.findViewById(R.id.communityList_listView);

//        AFadapter = new AllFridgeAdapter();
        List<String> data = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
//        listView.setAdapter(AFadapter);
        listView.setAdapter(adapter);

        for(int i=0; i<15; i++){
            String fName = "Community "+(i+1);
            data.add(fName);
        }
//        이걸 해줘야 add 가 반영됨
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Toast.makeText(getActivity(), index+1 + "th is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CommunityActivity.class);
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




}