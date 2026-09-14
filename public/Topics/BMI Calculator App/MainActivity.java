package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight, etHeight, etBmiResult;
    private Button btnCalculateBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        etBmiResult = findViewById(R.id.etBmiResult);
        btnCalculateBmi = findViewById(R.id.btnCalculateBmi);

        // Calculate BMI Button Listener
        btnCalculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = etWeight.getText().toString().trim();
                String heightStr = etHeight.getText().toString().trim();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double weight = Double.parseDouble(weightStr);
                    double height = Double.parseDouble(heightStr);

                    if (height <= 0) {
                        Toast.makeText(MainActivity.this, "Height must be greater than 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Formula: BMI = Weight (kg) / (Height (m) * Height (m))
                    double bmi = weight / (height * height);

                    // Display calculated BMI
                    etBmiResult.setText(String.format(Locale.getDefault(), "%.2f", bmi));

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter valid numerical values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}