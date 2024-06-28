package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    Button addButton;
    EditText etsID;
    EditText etsname;
    EditText etRoll;
    Spinner sClass;
    Spinner sDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        addButton = findViewById(R.id.bAdd);
        etsID = findViewById(R.id.editTextID);
        etsname = findViewById(R.id.editTextName);
        etRoll = findViewById(R.id.editTextRoll);
        sClass = findViewById(R.id.spinnerClass);
        sDiv = findViewById(R.id.spinnerDiv);

        Intent intent = getIntent();
        String teachName = intent.getStringExtra("TeachName");
        addButton.setOnClickListener(v -> {
            try {
                String Id = etsID.getText().toString().trim();
                String name = etsname.getText().toString().trim();
                String classes = sClass.getSelectedItem().toString().trim();
                String div = sDiv.getSelectedItem().toString().trim();
                int rollNo = Integer.parseInt(etRoll.getText().toString().trim());
                if (!Id.isEmpty() && !name.isEmpty() && !classes.isEmpty() && !div.isEmpty()) {
                    Teacher t = new Teacher(AddStudent.this);
                    t.addStudent(Id, name, classes, div, rollNo, teachName);
                    Toast.makeText(AddStudent.this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddStudent.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException e){
                Toast.makeText(AddStudent.this, "Enter Number in Roll NO.", Toast.LENGTH_SHORT).show();
            }
            catch (SQLiteConstraintException e){
                Toast.makeText(AddStudent.this, "Student Already Exist!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}