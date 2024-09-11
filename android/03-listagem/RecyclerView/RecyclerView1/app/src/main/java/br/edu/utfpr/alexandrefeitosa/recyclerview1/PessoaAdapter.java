package br.edu.utfpr.alexandrefeitosa.recyclerview1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {

    private List<Pessoa> lista;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNome, textViewIdade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome  = itemView.findViewById(R.id.textViewNome);
            textViewIdade = itemView.findViewById(R.id.textViewIdade);
        }
    }

    public PessoaAdapter(List<Pessoa> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext())
                                       .inflate(R.layout.pessoa_adapter,
                                                viewGroup,
                                                false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Pessoa pessoa = lista.get(i);

        myViewHolder.textViewNome.setText(pessoa.getNome());
        myViewHolder.textViewIdade.setText(String.valueOf(pessoa.getIdade()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}