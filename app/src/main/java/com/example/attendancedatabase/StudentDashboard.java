package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentDashboard extends AppCompatActivity {
    TextView sName;
    TextView sId;
    TextView sRoll;
    TextView sClass;
    TableLayout table;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        sName = findViewById(R.id.textViewName);
        sId = findViewById(R.id.textViewID);
        sRoll = findViewById(R.id.textViewRoll);
        sClass = findViewById(R.id.textViewClass);
        table = findViewById(R.id.tableLayoutAttendance);

        Student s = new Student(this);
        Intent intent = getIntent();
        String aClass = intent.getStringExtra("StudentClass");
        sClass.setText(String.format("Class : %s", aClass));
        id = intent.getStringExtra("StudentID");
        sName.setText(String.format("Name : %s", intent.getStringExtra("StudentName")));
        sId.setText(String.format("ID : %s", id));
        sRoll.setText(String.format("Roll No. : %d", s.getRoll(aClass, id)));

        s.updateOverall(id,aClass);

        String[] sub = s.getSubjects(aClass);
        String ne = "OVERALL"; // Define new element to add
        List<String> l = new ArrayList<String>(
                Arrays.asList(sub)); // Convert Array to ArrayList
        l.add(ne); // Add new element in ArrayList l
        sub = l.toArray(sub);
        String[] attendancePercentages;
        if(aClass.equals("TYCS")){
            attendancePercentages= new String[]{s.getAttendance(sub[0], aClass, id),
                    s.getAttendance(sub[1], aClass, id),
                    s.getAttendance(sub[2], aClass, id),
                    s.getAttendance(sub[3], aClass, id),
                    s.getAttendance(sub[4], aClass, id),
                    s.getAttendance(sub[5], aClass, id)};
        }
        else {
            attendancePercentages= new String[]{s.getAttendance(sub[0], aClass, id),
                    s.getAttendance(sub[1], aClass, id),
                    s.getAttendance(sub[2], aClass, id),
                    s.getAttendance(sub[3], aClass, id),
                    s.getAttendance(sub[4], aClass, id),
                    s.getAttendance(sub[5], aClass, id),
                    s.getAttendance(sub[6], aClass, id),
                    s.getAttendance(sub[7], aClass, id)};
        }

        for (int i = 0; i < sub.length; i++) {
            // Create a new table row
            TableRow row = new TableRow(this);

            // Create and add TextViews for subject and attendance percentage to the row
            TextView textViewSubject = new TextView(this);
            textViewSubject.setText(sub[i]);
            textViewSubject.setPadding(18, 18, 18, 18);
            textViewSubject.setTextSize(16);
            textViewSubject.setTextColor(Color.WHITE);
            textViewSubject.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewSubject.setBackgroundResource(R.drawable.sub_cell_shape);
            row.addView(textViewSubject);

            TextView textViewAttendance = new TextView(this);
            textViewAttendance.setText(attendancePercentages[i]);
            textViewAttendance.setPadding(18, 18, 18, 18);
            textViewAttendance.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewAttendance.setBackgroundResource(R.drawable.attend_cell_shape);
            textViewAttendance.setTextSize(16);
            row.addView(textViewAttendance);

            if(Float.parseFloat(attendancePercentages[i])<75f){
                textViewAttendance.setTextColor(Color.rgb(255,0,0));
            }
            else {
                textViewAttendance.setTextColor(Color.rgb(0,255,0));
            }
            if (sub[i].equals("OVERALL")){
                textViewSubject.setTypeface(Typeface.DEFAULT_BOLD);
                textViewAttendance.setTypeface(Typeface.DEFAULT_BOLD);
                textViewSubject.setBackgroundResource(R.drawable.head_cell_shape);
                table.addView(row);
                continue;
            }

            // Add click listener to the row
            final String subjectName = sub[i];
            row.setOnClickListener(v -> {
                // Redirect to another activity for subject details
                openSubjectAttendance(subjectName);
            });

            // Add the row to the table
            table.addView(row);
        }
    }
    public void openSubjectAttendance(String subjectName){
        Intent intent = new Intent(StudentDashboard.this, SubjectAttendance.class);
        intent.putExtra("subjectName", subjectName);
        intent.putExtra("StudentID", id);
        startActivity(intent);
    }
    public void goToHome(View view){
        Intent intent = new Intent(StudentDashboard.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}