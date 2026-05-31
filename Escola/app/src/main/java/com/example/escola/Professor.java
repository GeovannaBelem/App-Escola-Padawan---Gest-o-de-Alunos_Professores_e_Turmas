package com.example.escola;

public class Professor {
    private String nome, celular, email;

    public Professor(String nome, String celular,String email) {
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

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
