package com.example.escola;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROF);

        onCreate(db);
    }


    public long inserirAluno(String nome, String dataNasc, String celular, String cpf, String cep, String email, String nomeResp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOME1, nome);
        values.put(COL_DATA1, dataNasc);
        values.put(COL_CLLR1, celular);
        values.put(COL_CPF1, cpf);
        values.put(COL_CEP1, cep);
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



    public List<Aluno> listarAlunos() {
        List<Aluno> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, '' as nivel, '' as email FROM aluno", null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
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

        Cursor cursor = db.rawQuery("SELECT nome, '' as celular, '' as email FROM professor", null);

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(0);
                String celular = cursor.getString(1);
                String email = cursor.getString(2);
                lista.add(new Professor(nome, celular, email));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }


}
