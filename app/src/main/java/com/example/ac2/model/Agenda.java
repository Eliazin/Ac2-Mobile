package com.example.ac2.model;

public class Agenda {

    private String id;
    private String dataInicial;
    private String dataFinal;
    private String idProfessor;
    private String curso;

    private String resumo;

    public Agenda() {
    }

    public Agenda(String id, String dataInicial, String dataFim, String idProfessor, String curso, String resumo) {
        this.id = id;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFim;
        this.idProfessor = idProfessor;
        this.curso = curso;
        this.resumo = resumo;
    }

    public String getId(){return  id;}

    public void setId(){this.id = id;}

    public String getDataInicio() {
        return dataInicial;
    }

    public void setDataInicio(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFim() {
        return dataFinal;
    }

    public void setDataFim(String dataFim) {
        this.dataFinal = dataFim;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.curso = resumo;
    }


}