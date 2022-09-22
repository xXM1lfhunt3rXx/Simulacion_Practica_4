package com.example.practica4simulacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReActivity extends AppCompatActivity {

    TextView textViewRe;
    String caca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re);
        Intent intent = getIntent();

        caca = intent.getStringExtra("res");



        textViewRe = (TextView) findViewById(R.id.textViewRe);

        textViewRe.setText(caca);



    }
}