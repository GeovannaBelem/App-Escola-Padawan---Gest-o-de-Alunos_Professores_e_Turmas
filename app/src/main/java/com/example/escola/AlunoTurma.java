package com.example.escola;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlunoTurma extends RecyclerView.Adapter<AlunoTurma.AlunoViewHolder> {
    private Context context;
    private List<Aluno> listaAlunos;
    private List<Aluno> alunosSelecionados = new ArrayList<>();

    public AlunoTurma(Context context, List<Aluno> listaAlunos) {
        this.context = context;
        this.listaAlunos = listaAlunos;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno_turma, parent, false);
        return new AlunoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);
        holder.checkBox.setText(aluno.getNome());

        // Define o estado do CheckBox
        holder.checkBox.setChecked(alunosSelecionados.contains(aluno));

        // Ação ao clicar
        holder.checkBox.setOnClickListener(v -> {
            if (holder.checkBox.isChecked()) {
                if (!alunosSelecionados.contains(aluno)) {
                    alunosSelecionados.add(aluno);
                }
            } else {
                alunosSelecionados.remove(aluno);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public List<Aluno> getAlunosSelecionados() {
        return alunosSelecionados;
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.nomeAluno);
        }
    }
}
