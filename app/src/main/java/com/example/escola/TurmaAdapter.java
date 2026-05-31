package com.example.escola;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {
    private Context context;
    private List<Turma> listaTurmas;

    public TurmaAdapter(Context context, List<Turma> listaTurmas) {
        this.context = context;
        this.listaTurmas = listaTurmas;
    }

    @NonNull
    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_turma, parent, false);
        return new TurmaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaViewHolder holder, int position) {
        Turma turma = listaTurmas.get(position);
        holder.nome.setText(context.getString(R.string.turma_label, turma.getNomeTurma()));
        holder.turno.setText(context.getString(R.string.turno_label, turma.getTurnoTurma()));
    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextView nome, professor, alunos, turno;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_turma);
            turno = itemView.findViewById(R.id.turno_turma);
        }
    }
}
