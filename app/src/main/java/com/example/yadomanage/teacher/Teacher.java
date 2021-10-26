package com.example.yadomanage.teacher;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

import com.example.yadomanage.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher extends AppCompatActivity {
    TextView tid, tlastname,tfirstname,temail,tpasswd,tcpasswd ,tlogin,tphone;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        tlogin = (TextView) findViewById(R.id.txtlogin);
        tid = (TextView) findViewById(R.id.txtmsgv);
        tlastname = findViewById(R.id.txthogv);
        tfirstname = findViewById(R.id.txttengv);
        tphone=findViewById(R.id.txtsdt);
        temail = findViewById(R.id.txtemail);
        tpasswd = findViewById(R.id.txtmkgv);
        tcpasswd = findViewById(R.id.txtcmkgv);

        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        btnregister = findViewById(R.id.btnadd);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextUtils.isEmpty( someString)
                if (TextUtils.isEmpty(tlastname.getText().toString()) || TextUtils.isEmpty(tfirstname.getText().toString())
                        || TextUtils.isEmpty(tid.getText().toString()) || TextUtils.isEmpty(temail.getText().toString())
                        || TextUtils.isEmpty(tpasswd.getText().toString()) || TextUtils.isEmpty(tcpasswd.getText().toString())
                        || TextUtils.isEmpty(tphone.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Vui long nhap day du thong tin", Toast.LENGTH_LONG).show();
                } else {
//                    if (tpasswd.getText().toString() != tcpasswd.getText().toString()) {
//                        Toast.makeText(getApplicationContext(), "Mat khau khong trung khop", Toast.LENGTH_LONG).show();
//                    } else {

                        registerTeacher();
//                    }
                }


            }
        });


        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TeacherLogin = new Intent(Teacher.this, TeacherLogin.class);
                startActivity(TeacherLogin);
            }
        });


    }
    public void registerTeacher() {
        String id = tid.getText().toString();
        //int id = Integer.parseInt(data);
        String name =tlastname.getText().toString()+" "+tfirstname.getText().toString();
        String sex =  radioButton.getText().toString();
        String phone = tphone.getText().toString();
        String email = temail.getText().toString();
        String password = tpasswd.getText().toString();


        Call<ApiResponse> call =ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .performTeacherSignUp(id, name, sex,email, phone, password);
        Log.d("===", "VVV");

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        if (response.body().getResult_code() == 1) {
                            Toast.makeText(getApplicationContext(), "Your account is created. You can Login now!", Toast.LENGTH_LONG).show();
                            Intent TeacherLogin = new Intent(Teacher.this, TeacherLogin.class);
                            startActivity(TeacherLogin);

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
                //Toast.makeText(getApplicationContext(), "?????...", Toast.LENGTH_LONG).show();
                Log.d("===","" +id);
                Log.d("===", name);
                Log.d("===", email);
                Log.d("===", phone);
                Log.d("===", password);

            }
        });
    }
    public void checkButton (View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this,"Selected Radio Button: " + radioButton.getText(),Toast.LENGTH_SHORT).show();
    }
}