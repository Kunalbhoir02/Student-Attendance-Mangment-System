package com.example.attendancedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class AddTeacher extends AppCompatActivity {

    EditText etname;
    TextView textView;
    Spinner sSubject;
    Button bAdd;
    boolean[] selectedLanguage;
    ArrayList<Integer> langList = new ArrayList<>();
    String[] langArray = {"PYTHON", "COD", "STATISTIC", "LINUX", "CPP", "HTML", "CALCULUS",
            "FOA", "ADV_JAVA", "CN", "SE", "LA", "DOTNET", "ANDROID",
            "DATA_SCIENCE", "CLOUD_COMPUT", "INFO_RETRIEVAL", "ETHICAL_HACKING", "CYBER_FORENSIC"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        etname = findViewById(R.id.editTextTeachName);
//        sSubject = findViewById(R.id.spinnerSubject);
        bAdd = findViewById(R.id.buttonAddTeacher);
        Intent intent = getIntent();
        String teacherName = intent.getStringExtra("TeachName");
        //Below code is from https://www.geeksforgeeks.org/how-to-implement-multiselect-dropdown-in-android/
        textView = findViewById(R.id.textViewSelectSubject);

        // initialize selected language array
        selectedLanguage = new boolean[langArray.length];

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AddTeacher.this);

                // set title
                builder.setTitle("Select Language");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(langArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            // concat array value
                            stringBuilder.append(langArray[langList.get(j)]);
                            // check condition
                            if (j != langList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLanguage.length; j++) {
                            // remove all selection
                            selectedLanguage[j] = false;
                            // clear language list
                            langList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        bAdd.setOnClickListener(v -> {
            try {
                Teacher t = new Teacher(AddTeacher.this);
                String name = etname.getText().toString().trim();
                String subject = textView.getText().toString().trim();
                if (!name.isEmpty() && !subject.isEmpty() && teacherName != null) {
                    t.addTeacher(name, subject, teacherName);
                    Toast.makeText(AddTeacher.this, "Teacher Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
                }
            }
            catch (SQLiteConstraintException e){
                Toast.makeText(AddTeacher.this, "Teacher Already Exist!!", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(AddTeacher.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}