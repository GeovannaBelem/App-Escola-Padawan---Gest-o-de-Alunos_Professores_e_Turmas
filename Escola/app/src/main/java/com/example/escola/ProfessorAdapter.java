package com.example.escola;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder>{
    private List<Professor> listaProfessores;

    public ProfessorAdapter(List<Professor> listarProfessores) {
        this.listaProfessores = listarProfessores;
    }

    @NonNull
    @Override
    public ProfessorAdapter.ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prof, parent, false);
        return new ProfessorAdapter.ProfessorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorAdapter.ProfessorViewHolder holder, int position) {
        Professor prof = listaProfessores.get(position);
        holder.nome.setText(prof.getNome());
        holder.celular.setText("Celular: " + prof.getCelular());
        holder.email.setText("Email: " + prof.getEmail());

        holder.saibaMais.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Mais informações sobre " + prof.getNome(), Toast.LENGTH_SHORT).show();
        });
    }



    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextView nome, celular, email;
        Button saibaMais;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_nome);
            celular = itemView.findViewById(R.id.txt_celular);
            email = itemView.findViewById(R.id.txt_email);
            saibaMais = itemView.findViewById(R.id.btn_saiba_mais);
        }
    }
}
