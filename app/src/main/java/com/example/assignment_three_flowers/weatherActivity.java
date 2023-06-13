package com.example.assignment_three_flowers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class weatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

Neighborhood n = new Neighborhood();

        TextView neighborhood1 = findViewById(R.id.textV2);
    neighborhood1.setText("Enter Neighborhood");

        EditText hoodInput = findViewById(R.id.txt12);
     //   hoodInput.setText(n.toString());


    TextView streetName1 = findViewById(R.id.textV3);
        streetName1.setText("Enter Street Name");


        EditText streetInput = findViewById(R.id.txt13);
      //  streetInput.setText(n.tooString());


// button to go to specific weather




        // button to exit


        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}