package com.example.ac2.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ac2.R;
import com.example.ac2.api.servicoApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class resumo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        String id = getIntent().getStringExtra("id");
        String dataInicio = getIntent().getStringExtra("dataInicio");
        String dataFim = getIntent().getStringExtra("dataFim");
        String idProfessor = getIntent().getStringExtra("idProfessor");
        String curso = getIntent().getStringExtra("curso");
        String resumo = getIntent().getStringExtra("resumo");


        TextView textViewResumo = findViewById(R.id.resume);

        textViewResumo.setText(resumo);

        Button btnEnviarResumo = findViewById(R.id.btnSalvarResumo);

        btnEnviarResumo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText Resumo = findViewById(R.id.resumo);

                String resumoEditado = Resumo.getText().toString();

                insertResumo(id, dataInicio, dataFim, idProfessor, curso, resumoEditado);
            }
        });
    }

    private void insertResumo(String id, String dataInicio, String dataFim, String idProfessor, String curso, String resumo) {
        String url = "https://6462b3a97a9eead6fad4e217.mockapi.io/agenda1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicoApi servicoApi = retrofit.create(servicoApi.class);

        Call<Void> call = servicoApi.insertResumo(id, id, dataInicio, dataFim, idProfessor, curso, resumo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("teste", resumo);
                if (response.isSuccessful()) {
                    Toast.makeText(com.example.ac2.service.resumo.this, "Inserido sem falhas", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(com.example.ac2.service.resumo.this, "Inserção falhou", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(com.example.ac2.service.resumo.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });
    }
}