package com.example.practica4simulacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadosActivity extends AppCompatActivity {

    TextView textViewTiempof;
    TextView textViewPosX;
    TextView textViewPosY;
    float[] arrX;
    float[] arrY;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent intent = getIntent();
        arrX = intent.getFloatArrayExtra("resultadosX");
        arrY = intent.getFloatArrayExtra("resultadosY");
        textViewPosX = (TextView)findViewById(R.id.textViewDistancia);
        textViewPosY = (TextView)findViewById(R.id.textViewValoresIniciales);
        int arrXSize = arrX.length;
        for (int i = 0; i < arrXSize; i++)
        {
           textViewPosX.append(arrX.toString());
        }

    }
}