package com.example.ac2.api;

import com.example.ac2.model.Agenda;
import com.example.ac2.model.Professor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface servicoApi {

    @FormUrlEncoded
    @POST("/Agenda")
    Call<Void> signAgenda(
            @Field("id") String id,
            @Field("dataInicial") String dataInicial,
            @Field("dataFinal") String dataFinal,
            @Field("idProfessor") String idProfessor,
            @Field("curso") String curso,
            @Field("ResumoAula") String resumo
    );

    @FormUrlEncoded
    @PUT("/Agenda/{slug}")
    Call<Void> insertResumo(
            @Path("slug") String slug,
            @Field("id") String id,
            @Field("dataInicial") String dataInicial,
            @Field("dataFinal") String dataFinal,
            @Field("idProfessor") String idProfessor,
            @Field("curso") String curso,
            @Field("resumo") String resumo
    );


    @GET("/Agenda")
    Call<List<Agenda>> getAgendas();

    @FormUrlEncoded
    @POST("/Professor")
    Call<Void> signUser(
            @Field("id") String id,
            @Field("nome") String nome,
            @Field("rg") String rg,
            @Field("cpf") String cpf,
            @Field("endereco") String endereco
    );

    @GET("Professor/{id}")
    Call<Professor> getProfessor(@Path("id") String id);

}