package com.example.ac2.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ac2.MainActivity;
import com.example.ac2.R;
import com.example.ac2.api.servicoApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignAgenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_agenda);

        Button voltarAgenda = findViewById(R.id.voltarAgenda);
        voltarAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignAgenda.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnCadastraAgenda = findViewById(R.id.btnCadastraAgenda);
        btnCadastraAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editDataInicio = findViewById(R.id.dataInicio);
                EditText editDataFim = findViewById(R.id.dataFim);
                EditText editIdProfessor = findViewById(R.id.idProfessor);
                EditText editCurso = findViewById(R.id.curso);

                String dataInicio = editDataInicio.getText().toString();
                String dataFim = editDataFim.getText().toString();
                String idProfessor = editIdProfessor.getText().toString();
                String curso = editCurso.getText().toString();
                String resumo = "";

                signAgenda(dataInicio, dataFim, idProfessor, curso, resumo);
            }
        });
    }

    private void signAgenda(String dataInicio, String dataFim, String idProfessor, String curso, String resumo) {
        String url = "https://6462b3a97a9eead6fad4e217.mockapi.io/agenda1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicoApi servicoApi = retrofit.create(servicoApi.class);

        Call<Void> call = servicoApi.signAgenda("",dataInicio, dataFim, idProfessor, curso, resumo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignAgenda.this, "Agenda Inserida", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignAgenda.this, "Inserção falhou", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignAgenda.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });
    }
}