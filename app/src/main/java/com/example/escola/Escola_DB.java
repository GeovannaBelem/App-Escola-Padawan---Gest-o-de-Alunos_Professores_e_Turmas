package com.example.escola;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Escola_DB extends SQLiteOpenHelper{
    private static final String DATABASE = "escola_database";
    private static final int VERSION = 1;
    private static final String TABLE_ALUNOS = "aluno";
    private static final String COL_ID1 = "id";
    private static final String COL_NOME1 = "nome";
    private static final String COL_DATA1 = "data_nasc";
    private static final String COL_CLLR1 = "celular";
    private static final String COL_CPF1 = "CPF";
    private static final String COL_CEP1 = "CEP";
    private static final String COL_NIVEL = "nivel";
    private static final String COL_EMAIL1 = "email";
    private static final String COL_NOMERESP1 = "nome_resp";
    private static final String TABLE_PROF = "professor";
    private static final String COL_ID2 = "id";
    private static final String COL_NOME2 = "nome";
    private static final String COL_DATA2 = "data_nasc";
    private static final String COL_CLLR2 = "celular";
    private static final String COL_CPF2 = "CPF";
    private static final String COL_CEP2 = "CEP";
    private static final String COL_EMAIL2 = "email";
    private static final String TABLE_TURMA = "turma";
    private static final String COL_ID3 = "id";
    private static final String COL_NOMETURMA = "nomeTurma";
    private static final String COL_NOMETUTOR = "profTurma";
    private static final String COL_ALUNOTURMA = "alunoTurma";
    private static final String COL_TURNO = "turnoTurma";
    public Escola_DB(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_ALUNOS + " (" +
                COL_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOME1 + " TEXT NOT NULL, " +
                COL_DATA1 + " TEXT, " +
                COL_CLLR1 + " TEXT, " +
                COL_CPF1 + " TEXT NOT NULL UNIQUE, " +
                COL_CEP1 + " TEXT, " +
                COL_NIVEL + " TEXT, " +
                COL_EMAIL1 + " TEXT, " +
                COL_NOMERESP1 + " TEXT)";
        db.execSQL(sql);

        sql ="CREATE TABLE " + TABLE_PROF + " (" +
                COL_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOME2 + " TEXT NOT NULL, " +
                COL_DATA2 + " TEXT, " +
                COL_CLLR2 + " TEXT, " +
                COL_CPF2 + " TEXT NOT NULL UNIQUE, " +
                COL_CEP2 + " TEXT, " +
                COL_EMAIL2 + " TEXT, " +
                "sexo TEXT, " +
                "salario TEXT, " +
                "data_admissao TEXT)";
        db.execSQL(sql);

        sql ="CREATE TABLE " + TABLE_TURMA + " (" +
                COL_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOMETURMA + " TEXT, " +
                COL_NOMETUTOR + " TEXT, " +
                COL_ALUNOTURMA + " TEXT NOT NULL, " +
                COL_TURNO + " TEXT) ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TURMA);

        onCreate(db);
    }


    public long inserirAluno(String nome, String dataNasc, String celular, String cpf, String cep, String nivel, String email, String nomeResp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOME1, nome);
        values.put(COL_DATA1, dataNasc);
        values.put(COL_CLLR1, celular);
        values.put(COL_CPF1, cpf);
        values.put(COL_CEP1, cep);
        values.put(COL_NIVEL, nivel);
        values.put(COL_EMAIL1, email);
        values.put(COL_NOMERESP1, nomeResp);

        return db.insert(TABLE_ALUNOS, null, values);
    }

    public long inserirProf(String nome, String dataNasc, String celular, String cpf, String cep, String email, String sexo, String salario, String dataAdmissao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOME2, nome);
        values.put(COL_DATA2, dataNasc);
        values.put(COL_CLLR2, celular);
        values.put(COL_CPF2, cpf);
        values.put(COL_CEP2, cep);
        values.put(COL_EMAIL2, email);

        return db.insert(TABLE_PROF, null, values);
    }

    public long inserirTurma(String nomeTurma, String profTurma, List<Aluno> alunos, String turnoTurma) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOMETURMA, nomeTurma);
        values.put(COL_NOMETUTOR, profTurma);

        StringBuilder alunosStr = new StringBuilder();
        for (Aluno aluno : alunos) {
            alunosStr.append(aluno.getId()).append(",");
        }

        if (alunosStr.length() > 0) {
            alunosStr.setLength(alunosStr.length() - 1);
        }

        values.put(COL_ALUNOTURMA, alunosStr.toString());
        values.put(COL_TURNO, turnoTurma);

        return db.insert(TABLE_TURMA, null, values);
    }



    public List<Aluno> listarAlunos() {
        List<Aluno> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, nivel, email FROM aluno", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                String nivel = cursor.getString(2);
                String email = cursor.getString(3);
                lista.add(new Aluno(id, nome, nivel, email));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public ArrayList<Professor> listarProfessores() {
        ArrayList<Professor> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id, nome, celular, email FROM professor", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                String celular = cursor.getString(2);
                String email = cursor.getString(3);
                lista.add(new Professor(id, nome, celular, email));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

    public ArrayList<Turma> listarTurma() {
        ArrayList<Turma> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT id, nomeTurma, profTurma, alunoTurma, turnoTurma FROM turma", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nomeTurma = cursor.getString(cursor.getColumnIndexOrThrow("nomeTurma"));
                String profTurma = cursor.getString(cursor.getColumnIndexOrThrow("profTurma"));
                String alunoTurma = cursor.getString(cursor.getColumnIndexOrThrow("alunoTurma"));
                String turnoTurma = cursor.getString(cursor.getColumnIndexOrThrow("turnoTurma"));

                Turma turma = new Turma(id, nomeTurma, profTurma, alunoTurma, turnoTurma);
                lista.add(turma);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }


    public String getRelatorioAlunosTurmas() {
        SQLiteDatabase db = this.getReadableDatabase();

        //  Usa LIKE para procurar o id do aluno dentro da lista “1,3,7…”
        String sql =
                "SELECT a." + COL_NOME1 + "  AS aluno, " +
                        "       t." + COL_NOMETURMA + " AS turma " +
                        "FROM " + TABLE_ALUNOS + " a " +
                        "JOIN " + TABLE_TURMA  + " t " +
                        "     ON ',' || t." + COL_ALUNOTURMA + " || ',' " +
                        "        LIKE '%,' || a." + COL_ID1 + " || ',%' " +
                        "ORDER BY t." + COL_NOMETURMA + ", a." + COL_NOME1;

        StringBuilder sb = new StringBuilder();
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                String aluno = c.getString(0);
                String turma = c.getString(1);
                sb.append("Aluno: ").append(aluno)
                        .append("  |  Turma: ").append(turma)
                        .append('\n');
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sb.toString();
    }



}
