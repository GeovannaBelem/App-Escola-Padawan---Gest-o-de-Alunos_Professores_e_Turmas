package com.example.escola;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfessorTurma extends RecyclerView.Adapter<ProfessorTurma.ProfessorViewHolder> {
    private List<Professor> listaProfessores;
    private Context context;
    private int selectedPosition = -1; // guarda a posição selecionada

    public ProfessorTurma(Context context, List<Professor> listaProfessores) {
        this.context = context;
        this.listaProfessores = listaProfessores;
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.professor_turma, parent, false);
        return new ProfessorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor prof = listaProfessores.get(position);
        holder.radioButton.setText(prof.getNome());

        // Seleciona o botão se for o item marcado
        holder.radioButton.setChecked(position == selectedPosition);

        // Define o comportamento de clique
        holder.radioButton.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged(); // atualiza para refletir a seleção
        });
    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }

    public Professor getProfessorSelecionado() {
        if (selectedPosition != -1) {
            return listaProfessores.get(selectedPosition);
        }
        return null;
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.nomeprof);
        }
    }
}

