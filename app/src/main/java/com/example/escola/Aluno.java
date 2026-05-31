package com.example.escola;

public class Aluno {
    private int id;
    private String nome, nivel, email;

    public Aluno(int id, String nome, String nivel, String email) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getNivel() { return nivel; }
    public String getEmail() { return email; }
}
