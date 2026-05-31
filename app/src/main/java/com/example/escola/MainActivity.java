package com.example.escola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnCadastrar = findViewById(R.id.btn_cadastro);
        Button btnAluno = findViewById(R.id.btn_aluno);
        Button btnProf = findViewById(R.id.btn_prof);
        Button btnTurma = findViewById(R.id.btn_turma);

        btnCadastrar.setText(getString(R.string.btn_cadastro));
        btnAluno.setText(getString(R.string.btn_aluno));
        btnProf.setText(getString(R.string.btn_prof));
        btnTurma.setText(getString(R.string.btn_turma));


        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Escolha_Cadastro.class);
            startActivity(intent);
        });

        RecyclerView recyclerAluno = findViewById(R.id.recycler_aluno_turma);
        recyclerAluno.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerProf = findViewById(R.id.recycler_prof);
        recyclerProf.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerTurma = findViewById(R.id.recycler_turma);
        recyclerTurma.setLayoutManager(new LinearLayoutManager(this));


        Escola_DB db = new Escola_DB(this);

        List<Aluno> listaAluno = db.listarAlunos();
        AlunoAdapter adapterAluno = new AlunoAdapter(this, listaAluno);
        recyclerAluno.setAdapter(adapterAluno);

        List<Professor> listaProf = db.listarProfessores();
        ProfessorAdapter adapterProf = new ProfessorAdapter(this, listaProf);
        recyclerProf.setAdapter(adapterProf);

        List<Turma> listaTurma = db.listarTurma();
        TurmaAdapter adapterTurma = new TurmaAdapter(this, listaTurma);
        recyclerTurma.setAdapter(adapterTurma);

        recyclerAluno.setVisibility(View.VISIBLE);
        recyclerProf.setVisibility(View.GONE);
        recyclerTurma.setVisibility(View.GONE);

        btnAluno.setOnClickListener(v -> {
            recyclerAluno.setVisibility(View.VISIBLE);
            recyclerProf.setVisibility(View.GONE);
            recyclerTurma.setVisibility(View.GONE);
        });

        btnProf.setOnClickListener(v -> {
            recyclerAluno.setVisibility(View.GONE);
            recyclerProf.setVisibility(View.VISIBLE);
            recyclerTurma.setVisibility(View.GONE);
        });

        btnTurma.setOnClickListener(v -> {
            recyclerAluno.setVisibility(View.GONE);
            recyclerProf.setVisibility(View.GONE);
            recyclerTurma.setVisibility(View.VISIBLE);

    });



    }
}
