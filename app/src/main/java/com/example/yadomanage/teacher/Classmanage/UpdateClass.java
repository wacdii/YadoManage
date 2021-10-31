package com.example.yadomanage.teacher.Classmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yadomanage.R;
import com.example.yadomanage.retrofit.ApiClient;
import com.example.yadomanage.retrofit.ApiInterface;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.student.StudentDetail;
import com.example.yadomanage.teacher.TeacherHome;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateClass extends AppCompatActivity {
    String cid,cname,clink;
    Button btnupdate;
    EditText ms;
    EditText t;
    EditText l;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            cid = bundle.getString("Key_1", "");
            cname = bundle.getString("Key_2", "");
            clink = bundle.getString("Key_3", "");
        }
         ms = findViewById(R.id.malop);
         t = findViewById(R.id.tenlop);
         l = findViewById(R.id.linklop);
        btnupdate = findViewById(R.id.btnupdate);
        toolbar = findViewById(R.id.toolbar);

        ms.setText(cid);
        t.setText(cname);
        l.setText(clink);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateClassInfor();
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void updateClassInfor(){
        String ml = ms.getText().toString();
        String tl = t.getText().toString();
        String ll = l.getText().toString();

        Call<ApiResponse> call = ApiClient
                .getApiClient()
                .create(ApiInterface.class)
                .updateClass(ml,tl,ll);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code()==200 && response.body().getStatus().equals("ok")){
                    Toast.makeText(getApplication(),"Cap nhat thanh cong!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplication(),ml+tl+ll,Toast.LENGTH_SHORT).show();
            }
        });

    }
}