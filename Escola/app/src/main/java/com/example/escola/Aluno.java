package com.example.escola;

public class Aluno {
    private String id, nome, nivel, email;

    public Aluno(String id, String nome, String nivel, String email) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.email = email;
    }

    public String getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getNivel() {
        return nivel;
    }

    public String getEmail() {
        return email;
    }


}
