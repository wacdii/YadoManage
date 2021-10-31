package com.example.yadomanage.teacher.classfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.adapter.Student;
import com.example.yadomanage.adapter.StudentAdapter;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.student.StudentDetail;
import com.example.yadomanage.student.StudentDetails;
import com.example.yadomanage.teacher.Classmanage.ManageClass;
import com.example.yadomanage.vinterface.IClickItemStudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentClassStudent extends Fragment {

    RecyclerView recyclerViewStudent;
    StudentAdapter mStudentAdapter;

    private View view;
    TextView malop;
    ManageClass manageClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_class_student, container, false);

        recyclerViewStudent = view.findViewById(R.id.reStudent);
        mStudentAdapter = new StudentAdapter(getListUser(), new IClickItemStudent() {
            @Override
            public void onClickItemStudent(Student student) {
                goToStudentDetail(student);
            }
        });
        //mStudentAdapter= new StudentAdapter(getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerViewStudent.setLayoutManager(gridLayoutManager);
        getStudentData();



        manageClass = (ManageClass) getActivity();

        //malop = view.findViewById(R.id.truyenmalop);
        //malop.setText();

        InitUi();

        return view;
    }
    private void goToStudentDetail(Student student)
    {
        Intent intent = new Intent(getActivity(), StudentDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_student",student);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void getStudentData(){

        manageClass = (ManageClass) getActivity();
        Call<List<Student>> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .getStudentData(manageClass.getId());

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> data = response.body();
                mStudentAdapter.setData(data);
                recyclerViewStudent.setAdapter(mStudentAdapter);

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(getContext(), "Khong fech duoc", Toast.LENGTH_LONG).show();
            }
        });
    }
    private List<Student> getListUser()
    {
        List<Student> list =new ArrayList<>();
//        list.add(new Student("B1812835","Le Tuyet Ngan",R.drawable.ech));
//        list.add(new Student("B1809728","Ngo Quoc Vi",R.drawable.ech));
//        list.add(new Student("B1809728","Ngo Quoc Vi",R.drawable.ech));
//        list.add(new Student("B1809728","Ngo Quoc Vi",R.drawable.ech));
//        list.add(new Student("B1809728","Ngo Quoc Vi",R.drawable.ech));
//        list.add(new Student("B1809728","Ngo Quoc Vi",R.drawable.ech));
//        list.add(new Student("B1809728","Ngo Quoc Vi",R.drawable.ech));

        return list;
    }

    private  void InitUi(){

    }























}