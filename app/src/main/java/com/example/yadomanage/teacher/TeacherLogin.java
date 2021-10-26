package com.example.yadomanage.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLogin extends AppCompatActivity {
    Button btndangnhap;
    TextView txtemail, txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        txtemail = findViewById(R.id.txtreemail);
        txtpassword = findViewById(R.id.txtpaswordlg);

        btndangnhap = (Button) findViewById(R.id.btndangnhap);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginTeacher();
//                Intent TeacherHome = new Intent(TeacherLogin.this,TeacherHome.class);
//                startActivity(TeacherHome);
            }
        });
    }

    public void loginTeacher(){
        String email = txtemail.getText().toString();
        String passwd = txtpassword.getText().toString();

        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .performTeacherLogIn(email,passwd);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d("===", "VVV");
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        if (response.body().getResult_code() == 1) {
                            String name = response.body().getName();
                            String id = response.body().getId();

                            DataLocalManager.setStringEmail(email);
                            DataLocalManager.setStringName(name);
                            DataLocalManager.setStringId(id);

                            Intent Dashboard = new Intent(TeacherLogin.this, TeacherHome.class);
                            startActivity(Dashboard);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Failed...", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Sai mk...", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Khong phai 200...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "?????...", Toast.LENGTH_LONG).show();
            }
        });

    }
}