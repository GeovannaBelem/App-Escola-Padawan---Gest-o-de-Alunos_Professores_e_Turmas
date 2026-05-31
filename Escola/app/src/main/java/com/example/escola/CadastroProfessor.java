package com.example.escola;

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

public class CadastroProfessor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_professor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnsalvar = findViewById(R.id.btn_editar);

        btnsalvar.setOnClickListener(v -> {
            RadioGroup radioGroupSexo = findViewById(R.id.radioGroupSexo);
            String sexo = "";

            int idSelecionado = radioGroupSexo.getCheckedRadioButtonId();
            if (idSelecionado != -1) {
                RadioButton radioButtonSelecionado = findViewById(idSelecionado);
                sexo = radioButtonSelecionado.getText().toString();
            }
            EditText edtNome = findViewById(R.id.input_email);
            EditText edtData = findViewById(R.id.input_data);
            EditText edtCelular = findViewById(R.id.input_tel);
            EditText edtCpf = findViewById(R.id.input_cpf);
            EditText edtCep = findViewById(R.id.input_cep);
            EditText edtEmail = findViewById(R.id.input_nomeResp);
            EditText edtSalario = findViewById(R.id.input_salario);
            EditText edtDataAdmissao = findViewById(R.id.input_dataAdmissao);


            Escola_DB dbHelper = new Escola_DB(this);

            String nome = edtNome.getText().toString();
            String data = edtData.getText().toString();
            String celular = edtCelular.getText().toString();
            String cpf = edtCpf.getText().toString();
            String cep = edtCep.getText().toString();
            String email = edtEmail.getText().toString();
            String salario = edtSalario.getText().toString();
            String dataAdmissao = edtDataAdmissao.getText().toString();

            long result = dbHelper.inserirProf(nome, data, celular, cpf, cep, email, sexo, salario, dataAdmissao);

            if (result != -1) {
                Toast.makeText(this, "Professor salvo com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao salvar. CPF pode estar duplicado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}