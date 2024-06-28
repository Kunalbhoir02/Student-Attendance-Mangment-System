package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SelectClass extends AppCompatActivity {
    Spinner sClass;
    Spinner sDiv;
    Spinner sSub;
    Button bMark;
    String[] selectOptions;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);
        sClass = findViewById(R.id.spinnerSelectClass);
        sDiv = findViewById(R.id.spinnerSelectDiv);
        sSub = findViewById(R.id.spinnerSubject);
        bMark = findViewById(R.id.buttonMarkOnThis);
        Intent intent = getIntent();
        Teacher t = new Teacher(this);
        selectOptions = t.gettSubject(intent.getStringExtra("TeachName"));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selectOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSub.setAdapter(adapter);

        bMark.setOnClickListener(v -> {
            String name = intent.getStringExtra("TeachName");
            String aclass = sClass.getSelectedItem().toString();
            String div = sDiv.getSelectedItem().toString();
            String sub = sSub.getSelectedItem().toString();
            openMarkAttendance(name, aclass, div, sub);
        });
    }
    public void openMarkAttendance(String name,String aclass, String div, String sub){
        Intent intent = new Intent(SelectClass.this,MarkAttendance.class);
        intent.putExtra("TeachName", name);
        intent.putExtra("Class", aclass);
        intent.putExtra("Division", div);
        intent.putExtra("Subject", sub);
        startActivity(intent);
    }
}