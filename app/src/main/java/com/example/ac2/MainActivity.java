package com.example.ac2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ac2.service.SignAgenda;
import com.example.ac2.service.SignProfessor;
import com.example.ac2.service.ListAulas;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAbrirCadastroProfessor = findViewById(R.id.btnAbrirCadastroProfessor);
        btnAbrirCadastroProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignProfessor.class);
                startActivity(intent);
            }
        });

        Button btnPageAgenda = findViewById(R.id.btnPageAgenda);
        btnPageAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignAgenda.class);
                startActivity(intent);
            }
        });

        Button btnPageLista = findViewById(R.id.btnPageLista);
        btnPageLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListAulas.class);
                startActivity(intent);
            }
        });

    }
}