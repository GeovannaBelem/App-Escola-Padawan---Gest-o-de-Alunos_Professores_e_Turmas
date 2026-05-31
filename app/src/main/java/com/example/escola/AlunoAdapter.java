package com.example.escola;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {
    private Context context;
    private List<Aluno> listaAlunos;

    public AlunoAdapter(Context context, List<Aluno> listaAlunos) {
        this.context = context;
        this.listaAlunos = listaAlunos;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);
        holder.nome.setText(aluno.getNome());
        holder.nivel.setText(context.getString(R.string.nivel_ensino, aluno.getNivel()));
        holder.saibaMais.setText(context.getString(R.string.btn_saiba_mais));

        holder.saibaMais.setOnClickListener(v -> {
            Intent intent = new Intent(context, perfilAluno.class);
            intent.putExtra("id", aluno.getId());
            context.startActivity(intent);
        });
    }




    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView nome, nivel;
        Button saibaMais;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_nome);
            nivel = itemView.findViewById(R.id.txt_nivel);
            saibaMais = itemView.findViewById(R.id.btn_saiba_mais);
        }
    }

}
