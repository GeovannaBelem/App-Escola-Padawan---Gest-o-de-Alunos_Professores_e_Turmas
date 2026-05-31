package com.example.escola;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastrarAluno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_aluno);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnsalvar = findViewById(R.id.btn_editar);

        btnsalvar.setOnClickListener(v -> {
            EditText edtNome = findViewById(R.id.input_nome);
            EditText edtData = findViewById(R.id.input_data);
            EditText edtCelular = findViewById(R.id.input_tel);
            EditText edtCpf = findViewById(R.id.input_cpf);
            EditText edtCep = findViewById(R.id.input_cep);
            RadioGroup radioGroup = findViewById(R.id.radioGroupNivel);
            EditText edtEmail = findViewById(R.id.input_email);
            EditText edtResponsavel = findViewById(R.id.input_nomeResp);

            String opcaoEscolhida = "";

            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedId);
                opcaoEscolhida = selectedRadioButton.getText().toString();
            } else {
                Toast.makeText(this, "Selecione um nível!", Toast.LENGTH_SHORT).show();
                return;
            }

            Escola_DB dbHelper = new Escola_DB(this);

            String nome = edtNome.getText().toString();
            String data = edtData.getText().toString();
            String celular = edtCelular.getText().toString();
            String cpf = edtCpf.getText().toString();
            String cep = edtCep.getText().toString();
            String nivel = opcaoEscolhida;
            String email = edtEmail.getText().toString();
            String nomeResp = edtResponsavel.getText().toString();

            long result = dbHelper.inserirAluno(nome, data, celular, cpf, cep, nivel, email, nomeResp);

            if (result != -1) {
                Intent intent = new Intent(CadastrarAluno.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Erro ao salvar. CPF pode estar duplicado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}