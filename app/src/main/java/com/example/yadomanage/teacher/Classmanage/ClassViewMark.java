package com.example.yadomanage.teacher.Classmanage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.adapter.MarkAdapter;
import com.example.yadomanage.adapter.Marks;
import com.example.yadomanage.adapter.Semester;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.teacher.TeacherHome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassViewMark extends AppCompatActivity {
    Toolbar toolbar;
    String cid;
    Button button;
    Spinner dropdown;
    String sm_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view_mark);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            cid = bundle.getString("Key_1", "");
        }
        button = findViewById(R.id.btnadd);
        toolbar = findViewById(R.id.toolbar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getMarkList();
//                getSemester();
            }
        });


        dropdown = findViewById(R.id.spinner);
        ArrayAdapter<Semester> adaptere = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getSemester());
        dropdown.setAdapter(adaptere);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Chọn học kỳ")){
                    sm_name= "HK1-2021-2022";
                }
                else{
                    String item =parent.getItemAtPosition(position).toString();
                    sm_name = dropdown.getSelectedItem().toString();
//                    Toast.makeText(parent.getContext(),"Selected "+ item,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassViewMark.this, ManageClass.class);
                Bundle bundle = new Bundle();
                bundle.putString("Key_1",cid); // Truyền một String
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        toolbar.setTitle("  Điểm lớp "+cid);
    }

    private void getMarkList(){

        Call<List<Marks>> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .showMarks(cid,sm_name);
//        Toast.makeText(this,"Selected "+ sm_name + cid,Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<List<Marks>>() {
            @Override
            public void onResponse(Call<List<Marks>> call, Response<List<Marks>> response) {
                List<Marks> data = response.body();

                RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);
                MarkAdapter adapter = new MarkAdapter(data);
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
//                int log = response.code();
//                Toast.makeText(getApplicationContext(),log+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Marks>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Học kỳ này chưa có điểm",Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Semester> getSemesterList(){

        List movieList = new ArrayList<>();
        movieList.add("HK1-2021-2022");
        movieList.add(new Semester("HK2-2021-2022").getSm_name());

        return movieList;

    }

    private List getSemester(){
        List movieList = new ArrayList<>();
//        movieList.add(new Marks("B1809728", "Ngo Quoc Vi","hi", "hi","hi"));
        movieList.add("Chọn học kỳ");

        Call<List<Semester>> call =ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .showSemester(cid);

        call.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                int log = response.code();
                List<Semester> semesters = response.body();
                for (int i =0; i<semesters.size();i++){
                    movieList.add(semesters.get(i).getSm_name());
                }

                //Toast.makeText(getApplicationContext(),+log+""+semesters.get(0).getSm_name(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"????",Toast.LENGTH_LONG).show();
            }
        });
        return movieList;
    }


//        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);
//        //MarkAdapter adapter = new MarkAdapter(getMovieList());
//        MarkAdapter adapter = new MarkAdapter(getMovieList());
//        recyclerView.setAdapter(adapter);
//
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);

//    private List getMovieList() {
//        List movieList = new ArrayList<>();
//        movieList.add(new Marks("B1809728", "Ngo Quoc Vi","hi", "hi","hi"));
//
//        return movieList;
//    }


}