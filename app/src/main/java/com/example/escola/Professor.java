package com.example.escola;

public class Professor {
    private int id;
    private String nome, celular, email;

    public Professor(int id, String nome, String celular,String email) {
        this.id = id;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }
}
