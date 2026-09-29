package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText etWeight, etHeight, etBmiResult;
    private Button btnCalculateBmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etWeighteight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        etBmiResult = findViewById(R.id.etBmiResult);
        btnCalculateBmi = findViewById(R.id.btnCalculateBmi);

        btnCalculateBmi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String weightStr, heightStr;
                weightStr = etWeight.getText().toString().trim();
                heightStr = etHeight.getText().toString().trim();
                if(weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this,"enter both weight and height", Toast.LENGTH_SHORT).show(); 
                }
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);
                try {
                    if(height <= 0 || weight <= 0){
                                            Toast.makeText(MainActivity.this, "Enter valid number", Toast.LENGTH_SHORT).show();
return;
                    }
                    double bmi = height/(weight * weight);
                    etBmiResult.setText(String.format(bmi));
                } catch(NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Enter valid number", Toast.LENGTH_SHORT).show();
                }

            }
        })
    }
}