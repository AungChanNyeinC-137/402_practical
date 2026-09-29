package com.example.hellotoast;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowCount = findViewById(R.id.show_count);
    }

    // Toast ခလုတ်နှိပ်လျှင် စာတိုပြရန်
    public void showToast(View view) {
        Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT).show();
    }

    // Count ခလုတ်နှိပ်လျှင် ဂဏန်း ၁ တိုးရန်
    public void countUp(View view) {
        mCount++;
        mShowCount.setText(String.valueOf(mCount));
    }
}