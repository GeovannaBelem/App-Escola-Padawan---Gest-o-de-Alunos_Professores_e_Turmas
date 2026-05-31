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
public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder>{
    private Context context;
    private List<Professor> listaProfessores;

    public ProfessorAdapter(Context context, List<Professor> listarProfessores) {
        this.context = context;
        this.listaProfessores = listarProfessores;
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prof, parent, false);
        return new ProfessorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorAdapter.ProfessorViewHolder holder, int position) {
        Professor prof = listaProfessores.get(position);
        holder.nome.setText(prof.getNome());
        holder.celular.setText(context.getString(R.string.celular_label, prof.getCelular()));
        holder.saibaMais.setText(context.getString(R.string.btn_saiba_mais));

        holder.saibaMais.setOnClickListener(v -> {
            Intent intent = new Intent(context, perfilProfessor.class);
            intent.putExtra("id", prof.getId());
            context.startActivity(intent);
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
            saibaMais = itemView.findViewById(R.id.btn_saiba_mais);
        }
    }
}
