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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Cadastrar_Turmas extends AppCompatActivity {

    private ProfessorTurma adapterProfessorTurma;
    private AlunoTurma adapterAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_turmas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerProf = findViewById(R.id.recycler_prof_Turma);
        recyclerProf.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerAluno = findViewById(R.id.recycler_aluno_turma);
        recyclerAluno.setLayoutManager(new LinearLayoutManager(this));

        Escola_DB db = new Escola_DB(this);
        List<Professor> listaProf = db.listarProfessores();
        List<Aluno> listaAluno = db.listarAlunos();

        adapterProfessorTurma = new ProfessorTurma(this, listaProf);
        adapterAluno = new AlunoTurma(this, listaAluno);

        recyclerProf.setAdapter(adapterProfessorTurma);
        recyclerAluno.setAdapter(adapterAluno);

        Button btnsalvar = findViewById(R.id.btn_editar);
        btnsalvar.setOnClickListener(v -> {
            EditText nomeTurma = findViewById(R.id.input_nome);
            RadioGroup turnoTurma = findViewById(R.id.radioGroupTurno);

            String nome = nomeTurma.getText().toString().trim();
            int selectedId = turnoTurma.getCheckedRadioButtonId();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite o nome da turma!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedId == -1) {
                Toast.makeText(this, "Selecione um turno!", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadioButton = findViewById(selectedId);
            String turno = selectedRadioButton.getText().toString();

            Professor professor = adapterProfessorTurma.getProfessorSelecionado();
            if (professor == null) {
                Toast.makeText(this, "Selecione um professor!", Toast.LENGTH_SHORT).show();
                return;
            }

            String professorId = String.valueOf(professor.getId());

            List<Aluno> alunosSelecionados = adapterAluno.getAlunosSelecionados();
            if (alunosSelecionados.isEmpty()) {
                Toast.makeText(this, "Selecione ao menos um aluno!", Toast.LENGTH_SHORT).show();
                return;
            }

            Escola_DB dbHelper = new Escola_DB(this);
            long result = dbHelper.inserirTurma(nome, professorId, alunosSelecionados, turno);

            if (result != -1) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Erro ao salvar turma!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
