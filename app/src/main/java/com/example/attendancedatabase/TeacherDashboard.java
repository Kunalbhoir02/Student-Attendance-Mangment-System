package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeacherDashboard extends AppCompatActivity {

    TextView welcome;
    String teacherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        welcome = findViewById(R.id.textWelcomeTeacher);
        Intent intent = getIntent();
        teacherName = intent.getStringExtra("TeachName");
        welcome.setText(String.format("Welcome \n\t%s", teacherName));
    }

    public void openAddStudent(View view){
        Intent intent = new Intent(this, AddStudent.class);
        intent.putExtra("TeachName", teacherName);
        startActivity(intent);
    }
    public void openAddTeacher(View view){
        Intent intent = new Intent(this, AddTeacher.class);
        intent.putExtra("TeachName", teacherName);
        startActivity(intent);
    }
    public void openSelectClass(View view){
        Intent intent = new Intent(this, SelectClass.class);
        intent.putExtra("TeachName", teacherName);
        startActivity(intent);
    }
}