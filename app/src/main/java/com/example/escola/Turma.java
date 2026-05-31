package com.example.escola;

public class Turma {

    private int id;
    private String nomeTurma, profTurma, alunoTurma, turnoTurma;

    public Turma(int id, String nomeT, String profT, String AlunosT , String turnoT) {
        this.id = id;
        this.nomeTurma = nomeT;
        this.profTurma = profT;
        this.alunoTurma = AlunosT;
        this.turnoTurma = turnoT;
    }

    public int getId() { return id; }
    public String getNomeTurma() { return nomeTurma; }
    public String getProfTurma() { return profTurma; }
    public String getAlunoTurma() { return alunoTurma; }
    public String getTurnoTurma() { return turnoTurma; }

}
