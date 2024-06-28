package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView loginAs;
    EditText etUserName;
    EditText etPassword;
    Button login;
    Spinner selectClass;
    String[] selectOptions;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_t);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USER");
        if (username != null && username.equals("Student")) {
            setContentView(R.layout.activity_login_s);
            selectOptions = new String[]{"Select Class", "FYCS", "SYCS", "TYCS"};
            adapter = new ArrayAdapter<>(Login.this, android.R.layout.simple_spinner_item, selectOptions);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selectClass = findViewById(R.id.spinnerSelect);
            selectClass.setAdapter(adapter);
        }

        loginAs = findViewById(R.id.textViewLoginAs);
        loginAs.setText(String.format("Login as %s", username));
        etUserName = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogin);

        if (username != null && username.equals("Student")) {
            login.setOnClickListener(v -> {
                Student s = new Student(Login.this);
                if(s.login(etUserName.getText().toString().trim(),etPassword.getText().toString(),selectClass.getSelectedItem().toString())) {
                    Student student = new Student(Login.this);
                    String name = student.getName(selectClass.getSelectedItem().toString(), etUserName.getText().toString().trim());
                    Toast.makeText(Login.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                    openStudentDashboard(name, etUserName.getText().toString(), selectClass.getSelectedItem().toString());
                }
                else {
                    Toast.makeText(Login.this, "Who Are YOU??", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            login.setOnClickListener(v -> {
                Teacher t = new Teacher(Login.this);
                if(t.login(etUserName.getText().toString().trim(),etPassword.getText().toString())){
                    Toast.makeText(Login.this, "Welcome " + etUserName.getText().toString(), Toast.LENGTH_SHORT).show();
                    openTeacherDashboard(etUserName.getText().toString());
                }
                else {
                    Toast.makeText(Login.this, "Who Are YOU??", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void openTeacherDashboard(String name){
        Intent intent = new Intent(this, TeacherDashboard.class);
        intent.putExtra("TeachName", name);
        startActivity(intent);
        finish();
    }
    public void openStudentDashboard(String name, String Id, String aClass){
        Intent intent = new Intent(this, StudentDashboard.class);
        intent.putExtra("StudentID", Id);
        intent.putExtra("StudentName", name);
        intent.putExtra("StudentClass", aClass);
        startActivity(intent);
        finish();
    }
}