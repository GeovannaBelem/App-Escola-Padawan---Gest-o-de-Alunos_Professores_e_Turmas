package com.example.escola;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PesquisaJoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);          //  opcional, se estiver usando
        setContentView(R.layout.activity_pesquisajoin);

        TextView tv = findViewById(R.id.textjoin);

        Escola_DB db = new Escola_DB(this);
        String relatorio = db.getRelatorioAlunosTurmas();
        tv.setText(relatorio.isEmpty()
                ? "Nenhum aluno/turma encontrada."
                : relatorio);

        Button voltar = findViewById(R.id.voltarjoin);
        voltar.setOnClickListener(v -> {
            Intent intent = new Intent(PesquisaJoin.this, MainActivity.class);
            startActivity(intent);
        });
    }

}
