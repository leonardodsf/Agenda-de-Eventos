package com.flores.agendadeeventos;

public class Evento {

    //criando atributos(variaveis)púbicas
    public String evento;
    public String data;
    public String descricao;
    public String email;
    private String key;

    public Evento() {
    }

    public Evento(String evento, String data, String descricao, String email) {
        this.evento = evento;
        this.data = data;
        this.descricao = descricao;
        this.email = email;
    }

    public Evento(String evento, String data, String descricao) {
        this.evento = evento;
        this.data = data;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "evento='" + evento + '\'' +
                ", data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


