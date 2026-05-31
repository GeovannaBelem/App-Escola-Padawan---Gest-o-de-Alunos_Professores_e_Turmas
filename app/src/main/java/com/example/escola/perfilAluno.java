package com.example.escola;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class perfilAluno extends AppCompatActivity {

    private int alunoId;

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

        carregarDados();

        Button btnCadastro = findViewById(R.id.btn_cadastro);
        btnCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(perfilAluno.this, CadastrarAluno.class);
            startActivity(intent);
        });

        Button btneditar = findViewById(R.id.btn_editar);
        btneditar.setOnClickListener(v -> editarDados());

        Button btnexcluir = findViewById(R.id.btn_excluir);
        btnexcluir.setOnClickListener(v -> {
            excluirDados();
            Intent intent = new Intent(perfilAluno.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void carregarDados() {
        EditText nome = findViewById(R.id.text_nome);
        EditText cpf = findViewById(R.id.text_CPF);
        EditText cep = findViewById(R.id.text_cep);
        EditText dataNasc = findViewById(R.id.text_dataNasc);
        EditText email = findViewById(R.id.text_email);
        EditText nomeResp = findViewById(R.id.text_nomeResp);
        EditText tel = findViewById(R.id.text_tel);

        alunoId = getIntent().getIntExtra("id", -1);

        Escola_DB dbHelper = new Escola_DB(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

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

    private void editarDados() {

        EditText nome = findViewById(R.id.text_nome);
        EditText cpf = findViewById(R.id.text_CPF);
        EditText cep = findViewById(R.id.text_cep);
        EditText dataNasc = findViewById(R.id.text_dataNasc);
        EditText email = findViewById(R.id.text_email);
        EditText nomeResp = findViewById(R.id.text_nomeResp);
        EditText tel = findViewById(R.id.text_tel);

        alunoId = getIntent().getIntExtra("id", -1);

        Escola_DB dbHelper = new Escola_DB(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String novoNome = nome.getText().toString();
        String novoCpf = cpf.getText().toString();
        String novoCep = cep.getText().toString();
        String novaDataNasc = dataNasc.getText().toString();
        String novoEmail = email.getText().toString();
        String novoNomeResp = nomeResp.getText().toString();
        String novoCelular = tel.getText().toString();

        if (novoNome.isEmpty() || novoCpf.isEmpty() || novoCep.isEmpty() || novaDataNasc.isEmpty() || novoEmail.isEmpty() || novoNomeResp.isEmpty() || novoCelular.isEmpty()) {
            Toast.makeText(this, "Há campos vazios! Preencha todos.", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("nome", novoNome);
        values.put("CPF", novoCpf);
        values.put("CEP", novoCep);
        values.put("data_nasc", novaDataNasc);
        values.put("email", novoEmail);
        values.put("nome_resp", novoNomeResp);
        values.put("celular", novoCelular);

        int linhasAfetadas = db.update("aluno", values, "id = ?", new String[]{String.valueOf(alunoId)});

        if (linhasAfetadas > 0) {
            Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(perfilAluno.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Erro ao atualizar os dados!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void excluirDados() {
        Escola_DB dbHelper = new Escola_DB(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("aluno", "id = ?", new String[]{String.valueOf(alunoId)});
        db.close();
    }

}
