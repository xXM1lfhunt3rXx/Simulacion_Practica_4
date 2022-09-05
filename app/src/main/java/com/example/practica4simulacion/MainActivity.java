package com.example.practica4simulacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton begginButton;
    TextView speed;
    TextView angle;
    TextView height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begginButton=(ImageButton) findViewById(R.id.begginButton);
        speed=(TextView)findViewById(R.id.speed);
        angle =(TextView)findViewById(R.id.angle);
        height =(TextView)findViewById(R.id.height);

        begginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!trueValues()){return;}
                Intent goToEntriesActivity= new Intent(MainActivity.this,EntriesActivity.class);
                goToEntriesActivity.putExtra("speed",Double.parseDouble(speed.getText().toString()));
                goToEntriesActivity.putExtra("angle",Double.parseDouble(angle.getText().toString()));
                goToEntriesActivity.putExtra("height",Double.parseDouble(height.getText().toString()));
                startActivity(goToEntriesActivity);
            }
        });
    }

    private boolean trueValues() {

        try {
            double sp=(double) Double.parseDouble(speed.getText().toString());
            double an=(double)  Double.parseDouble(angle.getText().toString());
            double hg=(double)  Double.parseDouble(height.getText().toString());
            if(sp<1){
                Toast.makeText(getApplicationContext(), "Velocidad: valor muy pequeño",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(an==90){
                Toast.makeText(getApplicationContext(), "Angulo: No debe ser 90°",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(hg<1){
                Toast.makeText(getApplicationContext(), "Altura: valor muy pequeño",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Valor(es) incorrecto(s) o nulo(s): "+e.toString(),Toast.LENGTH_LONG).show();
            return false;
        }

    }
}