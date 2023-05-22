package com.example.ac2.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ac2.MainActivity;
import com.example.ac2.R;
import com.example.ac2.api.servicoApi;
import com.example.ac2.model.Agenda;
import com.example.ac2.model.Professor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListAulas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_aulas);

        Button voltarLista = findViewById(R.id.voltarLista);
        voltarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListAulas.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fillTable();

    }

    private void fillTable() {
        String url = "https://6462b3a97a9eead6fad4e217.mockapi.io/agenda1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicoApi servicoApi = retrofit.create(servicoApi.class);
        Call<List<Agenda>> call = servicoApi.getAgendas();
        call.enqueue(new Callback<List<Agenda>>() {
            @Override
            public void onResponse(Call<List<Agenda>> call, Response<List<Agenda>> response) {
                if (response.isSuccessful()) {
                    List<Agenda> agendas = response.body();
                    for (Agenda agenda : agendas) {
                        addRowOnTable(agenda);
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<Agenda>> call, Throwable t) {
                Log.e("API", "Erro na resposta da API: " + t.getMessage());
            }
        });
    }


    private void addRowOnTable(Agenda agenda) {
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        TableRow tableRow = new TableRow(this);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);

        TextView textViewDataInicio = new TextView(this);
        textViewDataInicio.setLayoutParams(layoutParams);
        textViewDataInicio.setText(agenda.getDataInicio());

        TextView textViewDataFim = new TextView(this);
        textViewDataFim.setLayoutParams(layoutParams);
        textViewDataFim.setText(agenda.getDataFim());

        TextView textViewIdProfessor = new TextView(this);
        textViewIdProfessor.setLayoutParams(layoutParams);


        String url = "https://6462b3a97a9eead6fad4e217.mockapi.io/agenda1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicoApi servicoApi = retrofit.create(servicoApi.class);
        Call<Professor> call = servicoApi.getProfessor(agenda.getIdProfessor());
        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {
                if (response.isSuccessful()) {
                    Professor prof = response.body();
                    textViewIdProfessor.setText(prof.getNome());
                }
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Log.e("API", "Erro ao receber resposta da API: " + t.getMessage());
            }
        });

        TextView textViewCurso = new TextView(this);
        textViewCurso.setLayoutParams(layoutParams);
        textViewCurso.setText(agenda.getCurso());

        Button button = new Button(this);
        button.setLayoutParams(layoutParams);
        button.setText("Ver resumo");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = agenda.getResumo();


                Intent intent = new Intent(ListAulas.this, resumo.class);
                intent.putExtra("id", agenda.getId());
                intent.putExtra("dataInicio", agenda.getDataInicio());
                intent.putExtra("dataFim", agenda.getDataFim());
                intent.putExtra("idProfessor", agenda.getIdProfessor());
                intent.putExtra("curso", agenda.getCurso());
                intent.putExtra("resumo", agenda.getResumo());


                startActivity(intent);
            }
        });

        tableRow.addView(textViewDataInicio);
        tableRow.addView(textViewDataFim);
        tableRow.addView(textViewIdProfessor);
        tableRow.addView(textViewCurso);
        tableRow.addView(button);

        tableLayout.addView(tableRow);
    }

}