package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAttendance extends AppCompatActivity {

    TextView tSubject;
    TableLayout table;
    Spinner sMonth;
    Button bFilter;
    TableRow[] row;
    ArrayAdapter<CharSequence> adapter;
    ArrayList<StudentData> sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_attendance);
        table = findViewById(R.id.presentTable);
        tSubject = findViewById(R.id.textSubject);
        sMonth = findViewById(R.id.spinnerMonth);
        bFilter = findViewById(R.id.buttonFilter);
        Intent intent = getIntent();
        String subName = intent.getStringExtra("subjectName");
        String id = intent.getStringExtra("StudentID");
        Log.d("Data", subName+" "+id);
        tSubject.setText(subName);
        Log.d("Data", subName+" "+id);
        String month = "Months";
        Log.d("Data", subName+" "+id);
        Student s = new Student(this);
        sd = s.getSubjectAttend(id,subName, month);
        Log.d("sd",String.valueOf(sd.size()));
        row = new TableRow[sd.size()];
        addRow(sd.size(),row, table);
        bFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeRow(sd.size());
                String month = sMonth.getSelectedItem().toString();
                sd.clear();
                sd = s.getSubjectAttend(id,subName, month);
                Log.d("sd", String.valueOf(sd.size()));
                addRow(sd.size(), row, table);
            }
        });
    }

    public void addRow(int size, TableRow[] row, TableLayout table){
        for (int i = 0; i < size; i++) {
            // Create a new table row
            row[i] = new TableRow(this);

            // Create and add TextViews for subject and attendance percentage to the row
            TextView textViewID = new TextView(this);
            textViewID.setText(String.valueOf(sd.get(i).ID));
            textViewID.setPadding(18, 18, 18, 18);
            textViewID.setTextSize(16);
            textViewID.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewID.setBackgroundResource(R.drawable.attend_cell_shape);
            row[i].addView(textViewID);

            TextView textViewSName = new TextView(this);
            textViewSName.setText(sd.get(i).date);
            textViewSName.setPadding(18, 18, 18, 18);
            textViewSName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewSName.setBackgroundResource(R.drawable.attend_cell_shape);
            textViewSName.setTextSize(16);
            row[i].addView(textViewSName);

            TextView textViewPresent = new TextView(this);
            textViewPresent.setText(sd.get(i).attend);
            textViewPresent.setPadding(18, 18, 18, 18);
            textViewPresent.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewPresent.setBackgroundResource(R.drawable.attend_cell_shape);
            textViewPresent.setTextSize(16);
            row[i].addView(textViewPresent);

            // Add the row to the table
            table.addView(row[i]);
        }
    }
    public void removeRow(int size){
        for (int i = 0; i < size; i++){
            table.removeView(row[i]);
        }
    }
}