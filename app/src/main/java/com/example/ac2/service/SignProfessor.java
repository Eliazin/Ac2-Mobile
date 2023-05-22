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

public class SignProfessor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_professor);

        Button voltarProfessor = findViewById(R.id.voltarProfessor);
        voltarProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignProfessor.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editNome = findViewById(R.id.editNome);
                EditText editRG = findViewById(R.id.editRG);
                EditText editCPF = findViewById(R.id.editCPF);
                EditText editEndereco = findViewById(R.id.editEndereco);

                String nome = editNome.getText().toString();
                String rg = editRG.getText().toString();
                String cpf = editCPF.getText().toString();
                String endereco = editEndereco.getText().toString();

                signProfessor(nome, rg, cpf, endereco);
            }
        });

    }

    private void signProfessor(String nome, String rg, String cpf, String endereco) {
        String url = "https://6462b3a97a9eead6fad4e217.mockapi.io/agenda1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicoApi servicoApi = retrofit.create(servicoApi.class);

        Call<Void> call = servicoApi.signUser("",nome, rg, cpf, endereco);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignProfessor.this, "Professor inserido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignProfessor.this, "Falha na inserção", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignProfessor.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });
    }
}