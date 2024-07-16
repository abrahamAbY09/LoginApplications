package com.example.loginapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DisplayActivity extends AppCompatActivity {

    private TextView tvName, tvAge, tvGmail, tvPassword;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvGmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String gmail = intent.getStringExtra("gmail");
        String password = intent.getStringExtra("password");

        // Set the string as text in the TextViews
        tvName.setText("Name: " + name);
        tvAge.setText("Age: " + age);
        tvGmail.setText("Gmail ID: " + gmail);
        tvPassword.setText("Password: " + password);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Save data to Firestore
        saveUserData(name, age, gmail, password);
    }

    private void saveUserData(String name, String age, String gmail, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("age", age);
        user.put("gmail", gmail);
        user.put("password", password);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    // Document successfully written
                    //No action currently performed
                })
                .addOnFailureListener(e -> {
                    // Error writing document
                });
    }
}
