package com.example.attendancedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button teacherButton = findViewById(R.id.teacherButton);
        Button studentButton = findViewById(R.id.studentButton);

        // Set click listeners for buttons
        teacherButton.setOnClickListener(v -> {
            // Perform action for teacher button
            Toast.makeText(MainActivity.this, "Login as Teacher", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Login.class);
            intent.putExtra("USER", "Teacher");
            startActivity(intent);
        });

        studentButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Login as Student", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Login.class);
            intent.putExtra("USER", "Student");
            startActivity(intent);
        });
    }
}