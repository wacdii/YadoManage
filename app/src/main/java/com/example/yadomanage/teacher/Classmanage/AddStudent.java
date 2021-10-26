package com.example.yadomanage.teacher.Classmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.student.StudentDetail;
import com.example.yadomanage.teacher.Teacher;
import com.example.yadomanage.teacher.TeacherLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStudent extends AppCompatActivity {
    TextView ms,ten,ns,sdt,email,dc,que;
    String passwd,cid;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            cid = bundle.getString("Key_1", "");
        }

        ms = findViewById(R.id.etxtmssv);
        ten = findViewById(R.id.etxtht);
        ns = findViewById(R.id.etxtns);
        sdt = findViewById(R.id.etxtsdt);
        email =findViewById(R.id.etxtemail);
        dc = findViewById(R.id.etxtdiachi);
        que =findViewById(R.id.etxtque);
        btnadd = findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

    }
    private void addStudent(){
        String mssv = ms.getText().toString();
        String hoten = ten.getText().toString();
        String ngaysinh = ns.getText().toString();
        String dt = sdt.getText().toString();
        String mail =email.getText().toString();
        String diachi =dc.getText().toString();
        String queq = que.getText().toString();
        String pass ="123456";

        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .addStudent(mssv,hoten,ngaysinh,dt,mail,diachi,queq,pass,cid);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        if (response.body().getResult_code() == 1) {
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AddStudent.this, ManageClass.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Key_1",cid); // Truyền một String
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Email already exits...", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "?????...", Toast.LENGTH_LONG).show();

            }
        });
    }
}