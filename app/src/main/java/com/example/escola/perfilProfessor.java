package com.example.escola;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class perfilProfessor extends AppCompatActivity {

    private int profId;

    private EditText nome, cpf, cep, dataNasc, email, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_professor);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nome = findViewById(R.id.text_nome);
        cpf = findViewById(R.id.text_CPF);
        cep = findViewById(R.id.text_cep);
        dataNasc = findViewById(R.id.text_dataNasc);
        email = findViewById(R.id.text_email_perfProf);
        tel = findViewById(R.id.text_tel);

        profId = getIntent().getIntExtra("id", -1);

        carregarDados();

        Button btnCadastro = findViewById(R.id.btn_cadastro);
        btnCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(perfilProfessor.this, CadastroProfessor.class);
            startActivity(intent);
        });

        Button btneditar = findViewById(R.id.btn_editar);
        btneditar.setOnClickListener(v -> editarDados());

        Button btnexcluir = findViewById(R.id.btn_excluir);
        btnexcluir.setOnClickListener(v -> {
            excluirDados();
            Intent intent = new Intent(perfilProfessor.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void carregarDados() {
        Escola_DB dbHelper = new Escola_DB(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT nome, CPF, CEP, data_nasc, email, celular FROM professor WHERE id = ?", new String[]{String.valueOf(profId)});

        if (cursor.moveToFirst()) {
            nome.setText(cursor.getString(0));
            cpf.setText(cursor.getString(1));
            cep.setText(cursor.getString(2));
            dataNasc.setText(cursor.getString(3));
            email.setText(cursor.getString(4));
            tel.setText(cursor.getString(5));
        } else {
            nome.setText("Professor não encontrado");
        }

        cursor.close();
        db.close();
    }

    private void editarDados() {
        String novoNome = nome.getText().toString();
        String novoCpf = cpf.getText().toString();
        String novoCep = cep.getText().toString();
        String novaDataNasc = dataNasc.getText().toString();
        String novoEmail = email.getText().toString();
        String novoCelular = tel.getText().toString();

        if (novoNome.isEmpty() || novoCpf.isEmpty() || novoCep.isEmpty() || novaDataNasc.isEmpty() || novoEmail.isEmpty() || novoCelular.isEmpty()) {
            Toast.makeText(this, "Há campos vazios! Preencha todos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Escola_DB dbHelper = new Escola_DB(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", novoNome);
        values.put("CPF", novoCpf);
        values.put("CEP", novoCep);
        values.put("data_nasc", novaDataNasc);
        values.put("email", novoEmail);
        values.put("celular", novoCelular);

        int linhasAfetadas = db.update("professor", values, "id = ?", new String[]{String.valueOf(profId)});

        if (linhasAfetadas > 0) {
            Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(perfilProfessor.this, MainActivity.class);
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

        db.delete("professor", "id = ?", new String[]{String.valueOf(profId)});
        db.close();
    }
}

