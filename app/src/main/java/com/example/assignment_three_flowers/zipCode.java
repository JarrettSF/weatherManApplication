package com.example.assignment_three_flowers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class zipCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code);



        Intent one = getIntent();

        int zipC = one.getIntExtra("zip", 0);

        TextView outputZip = findViewById(R.id.textV1);
        outputZip.setText( zipC + " is a zip code in 'Baltimore' ");



//button to show local weather

        Button button3 = findViewById(R.id.btn3);

        button3.setOnClickListener ((View view) -> {
            Intent newActivityIntent = new Intent(zipCode.this, weatherActivity.class);
            startActivity(newActivityIntent);
        });



// button to show local time

        Button button4 = findViewById(R.id.btn4);

        button4.setOnClickListener ((View view) -> {
            Intent newActivityIntent = new Intent(zipCode.this, timeActivity.class);
            startActivity(newActivityIntent);
        });




// button to exit


        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}