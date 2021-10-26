package com.example.yadomanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yadomanage.teacher.TeacherLogin;

public class MainActivity extends AppCompatActivity {
    Button btn_cv, btn_sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_cv = (Button) findViewById(R.id.btn_cv);
        btn_sv = (Button) findViewById(R.id.btn_sv);

        btn_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Teacher = new Intent(MainActivity.this, com.example.yadomanage.teacher.Teacher.class);
                startActivity(Teacher);
            }
        });

        btn_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudentLogin = new Intent(MainActivity.this, com.example.yadomanage.student.StudentLogin.class);
                startActivity(StudentLogin);
            }
        });
    }
}