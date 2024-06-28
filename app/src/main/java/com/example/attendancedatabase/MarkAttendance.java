package com.example.attendancedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MarkAttendance extends AppCompatActivity {
    TableLayout table;
    TextView sSub;
    TextView sDiv;
    TextView sClass;
    CheckBox[] cbs;
    ArrayList<StudentData> sd;
    Button bMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        table = findViewById(R.id.attendanceTable);
        sSub = findViewById(R.id.textViewMarkSub);
        sClass = findViewById(R.id.textViewMarkClass);
        sDiv = findViewById(R.id.textViewDiv);
        bMark = findViewById(R.id.buttonAddAttend);

        Intent intent = getIntent();
        String sub = intent.getStringExtra("Subject");
        String div = intent.getStringExtra("Division");
        String aClass = intent.getStringExtra("Class");
        String name = intent.getStringExtra("TeachName");

        sSub.setText(String.format("Subject : %s", sub));
        sDiv.setText(String.format("Division : %s", div));
        sClass.setText(String.format("Class : %s", aClass));
        Teacher t = new Teacher(MarkAttendance.this);
        sd = t.getStudentData(aClass,div);
        cbs = new CheckBox[sd.size()];

        for (int i = 0; i < sd.size(); i++) {
            // Create a new table row
            TableRow row = new TableRow(MarkAttendance.this);
            RelativeLayout relive = new RelativeLayout(MarkAttendance.this);
            relive.setPadding(0,0,0,0);
            relive.setGravity(Gravity.CENTER);

            // Create and add TextViews for subject and attendance percentage to the row
            TextView textViewRoll = new TextView(MarkAttendance.this);
            textViewRoll.setText(String.valueOf(sd.get(i).rollNo));
            textViewRoll.setPadding(18, 18, 18, 18);
            textViewRoll.setTextSize(16);
            textViewRoll.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewRoll.setBackgroundResource(R.drawable.attend_cell_shape);
            row.addView(textViewRoll);

            TextView textViewSName = new TextView(MarkAttendance.this);
            textViewSName.setText(sd.get(i).sName);
            textViewSName.setPadding(18, 18, 18, 18);
            textViewSName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textViewSName.setBackgroundResource(R.drawable.attend_cell_shape);
            textViewSName.setTextSize(16);
            row.addView(textViewSName);

            cbs[i] = new CheckBox(MarkAttendance.this);
            cbs[i].setGravity(View.TEXT_ALIGNMENT_CENTER);
            cbs[i].setPadding(0, 25, 18, 18);
            relive.setBackgroundResource(R.drawable.attend_cell_shape);
            relive.addView(cbs[i]);
            row.addView(relive);
            // Add the row to the table
            table.addView(row);
        }
        bMark.setOnClickListener(v -> {
            for (int i=0; i < sd.size(); i++) {
                t.addAttendance(sub, sd.get(i).ID, cbs[i].isChecked(), name, aClass);
            }
            Toast.makeText(MarkAttendance.this, "Attendance Added Successfully", Toast.LENGTH_SHORT).show();
        });
    }
}