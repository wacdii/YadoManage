package com.example.yadomanage.teacher.teacherflagment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.adapter.Class;
import com.example.yadomanage.adapter.ClassAdapter;
import com.example.yadomanage.adapter.ListAdapter;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.retrofit.model.ClassModel;
import com.example.yadomanage.sharedPreferences.DataLocalManager;
import com.example.yadomanage.teacher.Classmanage.AddClass;
import com.example.yadomanage.teacher.Classmanage.ManageClass;
import com.example.yadomanage.teacher.classfragment.FragmentClassStudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentTeacherHome extends Fragment {
    ListView listViewClass;
   // ArrayList<Class> arrayListClass;
    ClassAdapter classAdapter;
    Button btn;
    TextView t;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_home, container, false);


        listViewClass = (ListView) view.findViewById(R.id.listMH);

        getClassData();

        btn = view.findViewById(R.id.btnthemlopquanly);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddClass.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getClassData(){
        Call<List<Class>> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .showClass(DataLocalManager.getStringId());

        call.enqueue(new Callback<List<Class>>() {
            @Override
            public void onResponse(Call<List<Class>> call, Response<List<Class>> response) {
                List<Class> data = response.body();

                classAdapter = new ClassAdapter(getContext(),R.layout.list_class_row,data);
                listViewClass.setAdapter(classAdapter);

                listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), ManageClass.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Key_1",data.get(position).getCid()); // Truyền một String
                        bundle.putString("Key_2",data.get(position).getCname());
                        bundle.putString("Key_3",data.get(position).getClink());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        //Toast.makeText(getContext(), data.get(position), Toast.LENGTH_LONG).show();
                        //t.setText(data.get(position).getCid());
                    }
                });
                //Toast.makeText(getContext(), "okokok", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Class>> call, Throwable t) {
                Toast.makeText(getContext(), "Khong fech duoc", Toast.LENGTH_LONG).show();
            }


        });




    }
}