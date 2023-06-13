package com.example.assignment_three_flowers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class timeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        EditText userInput = findViewById(R.id.timeAppInput);
        String UI = userInput.getText().toString().trim();

// I tried a few different activities such as methods to check the time
        //which were in another class
        // nothing seemed to be working how i wanted so I deleted the code




        // button to exit


        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}