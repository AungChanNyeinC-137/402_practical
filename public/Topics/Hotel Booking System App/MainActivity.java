package com.example.hotelbookingsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etCustomerName, etMobileNumber, etNrcNumber;
    private RadioGroup rgRoomType;
    private CheckBox cbGroundFloor, cbFirstFloor, cbThirdFloor;
    private Button btnBooking, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etCustomerName = findViewById(R.id.etCustomerName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etNrcNumber = findViewById(R.id.etNrcNumber);
        rgRoomType = findViewById(R.id.rgRoomType);
        cbGroundFloor = findViewById(R.id.cbGroundFloor);
        cbFirstFloor = findViewById(R.id.cbFirstFloor);
        cbThirdFloor = findViewById(R.id.cbThirdFloor);
        btnBooking = findViewById(R.id.btnBooking);
        btnClear = findViewById(R.id.btnClear);

        // Booking Button Click Handler
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = etCustomerName.getText().toString().trim();

                // Get Selected Room Type
                int selectedRoomId = rgRoomType.getCheckedRadioButtonId();
                String roomType = "";
                if (selectedRoomId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRoomId);
                    roomType = selectedRadioButton.getText().toString();
                }

                // Get Selected Floors (supporting single or multiple values)
                List<String> selectedFloors = new ArrayList<>();
                if (cbGroundFloor.isChecked()) {
                    selectedFloors.add(cbGroundFloor.getText().toString());
                }
                if (cbFirstFloor.isChecked()) {
                    selectedFloors.add(cbFirstFloor.getText().toString());
                }
                if (cbThirdFloor.isChecked()) {
                    selectedFloors.add(cbThirdFloor.getText().toString());
                }

                String floorNo = String.join(", ", selectedFloors);

                // Build Alert Message
                String alertMessage = "Hello " + customerName + "! You choose " + roomType + " at " + floorNo;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Booking Confirmation")
                        .setMessage(alertMessage)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });

        // Clear Button Click Handler
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all Editable Texts
                etCustomerName.setText("");
                etMobileNumber.setText("");
                etNrcNumber.setText("");

                // Reset CheckBoxes & RadioGroup
                cbGroundFloor.setChecked(false);
                cbFirstFloor.setChecked(false);
                cbThirdFloor.setChecked(false);

                // Show Toast Message
                Toast.makeText(MainActivity.this, "Clear text fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}