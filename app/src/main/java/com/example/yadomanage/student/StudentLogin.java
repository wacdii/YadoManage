package com.example.yadomanage.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yadomanage.R;
import com.example.yadomanage.teacher.TeacherHome;
import com.example.yadomanage.teacher.TeacherLogin;

public class StudentLogin extends AppCompatActivity {
    Button btndangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudentHome = new Intent(StudentLogin.this, StudentHome.class);
                startActivity(StudentHome);
                //Log.d("===","VVV");
            }
        });
    }
}