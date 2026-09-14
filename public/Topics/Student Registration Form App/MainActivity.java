    package com.example.studentregistration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etStudentName, etStudentID, etEmail, etPhone;
    private RadioGroup rgMajors;
    private Button btnRegister, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etStudentName = findViewById(R.id.etStudentName);
        etStudentID = findViewById(R.id.etStudentID);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        rgMajors = findViewById(R.id.rgMajors);
        btnRegister = findViewById(R.id.btnRegister);
        btnClear = findViewById(R.id.btnClear);

        // Register Button Click Handler
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etStudentName.getText().toString().trim();

                // Get selected RadioButton text
                int selectedId = rgMajors.getCheckedRadioButtonId();
                String majorName = "";

                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    majorName = selectedRadioButton.getText().toString();
                }

                String toastMessage = "Hello " + userName + "! Welcome to " + majorName + " Your Registration Successful";
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });

        // Clear Button Click Handler
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all Editable Texts
                etStudentName.setText("");
                etStudentID.setText("");
                etEmail.setText("");
                etPhone.setText("");

                // Show Alert Dialog
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Alert")
                        .setMessage("Clear All Text")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}