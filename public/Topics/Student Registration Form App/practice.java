package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
private EditText etStudentName, etEmail,etStudentID,etPhone;
private RadioGroup rgMajors;
private Button btnClear, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize UI components
        etStudentName = findViewById(R.id.etStudentName);
        etStudentID = findViewById(R.id.etStudentID);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        rgMajors = findViewById(R.id.rgMajors);
        btnRegister = findViewById(R.id.btnRegister);
        btnClear = findViewById(R.id.btnClear);
        //btnregister onclick listener
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userName = etStudentName.getText().toString();
                int selectedId = rgMajors.getCheckedRadioButtonId();
                String majorName = "";
                if(selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    majorName = selectedRadioButton.getText().toString();
                }
                String toastMessage = "Hello" + userName +"! Welcome to "+majorName +" Your 
                Registration Successful";
                Toast.makeText(MainActivity.this,toastMessage,Toast.LENGTH_LONG).show();    

            }
        }) ;

        //btn clear onclick listener
        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            //clear editable texts
            etStudentName.setText("");
            etStudentID.setText("");
            etEmail.setText("");
            etPhone.setText("");
            new AlertDialog.Builder(MainActivity.this)
            .setTitle("Alert!")
            .setMessage("Clear All Text")
            .setPositiveButton("OK")
            .show();
            }
        })
    }
}