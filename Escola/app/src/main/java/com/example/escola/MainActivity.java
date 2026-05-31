package com.example.escola;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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


        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Escolha_Cadastro.class);
            startActivity(intent);
        });

        RecyclerView recyclerAluno = findViewById(R.id.recycler_aluno);
        recyclerAluno.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerProf = findViewById(R.id.recycler_prof);
        recyclerProf.setLayoutManager(new LinearLayoutManager(this));

        Escola_DB db = new Escola_DB(this);
        List<Aluno> listaAluno = db.listarAlunos();
        AlunoAdapter adapterAluno = new AlunoAdapter(this,listaAluno);
        recyclerAluno.setAdapter(adapterAluno);

        List<Professor> listaProf = db.listarProfessores();
        ProfessorAdapter adapterProf = new ProfessorAdapter(listaProf);
        recyclerProf.setAdapter(adapterProf);


        btnAluno.setOnClickListener(v -> {
            recyclerProf.setVisibility(View.INVISIBLE);
            recyclerAluno.setVisibility(View.VISIBLE);
            recyclerAluno.setAdapter(adapterAluno);
        });

        btnProf.setOnClickListener(v -> {
            recyclerAluno.setVisibility(View.INVISIBLE);
            recyclerProf.setVisibility(View.VISIBLE);
            recyclerProf.setAdapter(adapterProf);
        });


    }
}




