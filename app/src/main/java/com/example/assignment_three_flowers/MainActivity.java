package com.example.assignment_three_flowers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
these are the two buttons that lead to other activities in the application
 */
        Button makeATrip = findViewById(R.id.plan);
        makeATrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tripPlanned.class);
                startActivity(intent);
            }
        });

        Button placesToGo = findViewById(R.id.placesToGo);
        placesToGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, placesToGo.class);
                startActivity(intent);
            }
        });

        /*
        this is the button for the zip code that first takes input
         */
        Button zip = findViewById(R.id.btn1);

        EditText zipInput = findViewById(R.id.txt1);

        zip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userInput = zipInput.getText().toString().trim();

                if ( userInput.length() == 0 ) {
                    Toast.makeText(MainActivity.this, "Type A Zip Code", Toast.LENGTH_LONG).show();
                    return;
                }

                try {

                    int code = Integer.parseInt(userInput);
                    Intent intent = new Intent(MainActivity.this, zipCode.class);

                    intent.putExtra("zip", code);
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();
                }
            }


        });












    }
}