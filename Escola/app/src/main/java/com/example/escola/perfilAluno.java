package com.example.escola;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class perfilAluno extends AppCompatActivity {

    private List<Aluno> listaAlunos;

    public perfilAluno() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_aluno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView nome, cpf, cep, dataNasc, email, nomeResp, tel;
        nome =findViewById(R.id.text_nome);
        cpf =findViewById(R.id.text_CPF);
        cep =findViewById(R.id.text_cep);
        dataNasc =findViewById(R.id.text_dataNasc);
        email =findViewById(R.id.text_email);
        nomeResp =findViewById(R.id.text_nomeResp);
        tel = findViewById(R.id.text_tel);


        List<Aluno> lista = new ArrayList<>();
        Escola_DB dbHelper = new Escola_DB(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int alunoId = getIntent().getIntExtra("id", -1);
        Cursor cursor = db.rawQuery("SELECT nome, cpf, cep, data_nasc, email, nome_resp, celular FROM aluno WHERE id = ?", new String[]{String.valueOf(alunoId)});

        if (cursor.moveToFirst()) {
            nome.setText(cursor.getString(0));
            cpf.setText(cursor.getString(1));
            cep.setText(cursor.getString(2));
            dataNasc.setText(cursor.getString(3));
            email.setText(cursor.getString(4));
            nomeResp.setText(cursor.getString(5));
            tel.setText(cursor.getString(6));
        } else {
            nome.setText("Aluno não encontrado");
        }

        cursor.close();
        db.close();

    }
}