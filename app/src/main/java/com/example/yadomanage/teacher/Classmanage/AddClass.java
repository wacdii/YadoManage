package com.example.yadomanage.teacher.Classmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.yadomanage.R;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.sharedPreferences.DataLocalManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClass extends AppCompatActivity {
    TextView cid,cname,clink;
    Button btncreclass;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        cid = findViewById(R.id.malop);
        cname = findViewById(R.id.tenlop);
        clink = findViewById(R.id.linklop);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btncreclass= findViewById(R.id.btnupdate);

        btncreclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addclass();
            }
        });
    }

    public void addclass(){
        String id = cid.getText().toString();
        String name = cname.getText().toString();
        String link =clink.getText().toString();

        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .performClassAdd(id,name,link, DataLocalManager.getStringId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        if (response.body().getResult_code() == 1) {
                            Toast.makeText(getApplicationContext(), "Them Lop Thanh Cong", Toast.LENGTH_LONG).show();
                            Intent TeacherLogin = new Intent(AddClass.this, com.example.yadomanage.teacher.TeacherHome.class);
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
                Toast.makeText(getApplicationContext(), "?????", Toast.LENGTH_LONG).show();
            }
        });
    }
}