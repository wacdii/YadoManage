package com.example.yadomanage.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yadomanage.R;
import com.example.yadomanage.adapter.Student;
import com.example.yadomanage.chat.Chat;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.teacher.Classmanage.ManageClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDetail extends AppCompatActivity {
    TextView ten,ns,sdt,email,dc,que;
    ImageView anh;
    Toolbar toolbar;
    Student student;
    Button btnupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        student = (Student) bundle.get("obj_student");
       ten = findViewById(R.id.etxtht);
       ns = findViewById(R.id.etxtns);
       sdt = findViewById(R.id.etxtsdt);
       email =findViewById(R.id.etxtemail);
       dc = findViewById(R.id.etxtdiachi);
       que =findViewById(R.id.etxtque);
       anh = findViewById(R.id.anh);
       toolbar = findViewById(R.id.toolbar);
       btnupdate = findViewById(R.id.btn_update);

       btnupdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Toast.makeText(getApplication(),"Da nhap",Toast.LENGTH_SHORT).show();
               updateStudent();

           }
       });

        ten.setText(student.getS_name());
        ns.setText(student.getS_dob());
        sdt.setText(student.getS_phone());
        email.setText(student.getS_email());
        dc.setText(student.getS_address());
        que.setText(student.getS_hometown());

        Glide.with(anh.getContext()).load("http://192.168.167.214/yadomanage/image/"+student.getS_images()).into(anh);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_edit:
//                        Toast.makeText(getApplication(),"Item1 clicked",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(StudentDetail.this, Chat.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Key_1",student.getSid() ); // Truyền một String
                        bundle.putString("Key_2",student.getS_name());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return true;
                    case R.id.action_manage:
                        Toast.makeText(getApplication(),"Item2 clicked",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_delete:
                        //Toast.makeText(getApplication(),"Item3 clicked",Toast.LENGTH_SHORT).show();
                        deleteStudent();
                        return true;
                    default:
                        return false;
                }

            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("  Thông tin sinh viên - "+student.getSid());


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_class_manage,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void deleteStudent()
    {
        String sid = student.getSid();

        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .deleteStudent(sid);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code()==200 && response.body().getStatus().equals("ok")){
                    Intent intent = new Intent(StudentDetail.this, ManageClass.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Key_1",student.getCid() ); // Truyền một String
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplication(),"Khong xoa dc",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateStudent()
    {
        String sid = student.getSid();
        String hoten = ten.getText().toString();
        String ngaysinh = ns.getText().toString();
        String dt = sdt.getText().toString();
        String mail =email.getText().toString();
        String diachi =dc.getText().toString();
        String queq = que.getText().toString();

        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .updateStudent(sid,hoten,ngaysinh,dt,mail,diachi,queq);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code()==200 && response.body().getStatus().equals("ok")){
                    Toast.makeText(getApplication(),"Cap nhat thanh cong!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StudentDetail.this, ManageClass.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Key_1",student.getCid() ); // Truyền một String
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplication(),"Khong xoa dc",Toast.LENGTH_SHORT).show();
            }
        });
    }



}