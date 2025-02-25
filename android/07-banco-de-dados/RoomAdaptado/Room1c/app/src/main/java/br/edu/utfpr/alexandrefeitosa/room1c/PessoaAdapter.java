package br.edu.utfpr.alexandrefeitosa.room1c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room1c.modelo.MaoUsada;
import br.edu.utfpr.alexandrefeitosa.room1c.modelo.Pessoa;

public class PessoaAdapter extends BaseAdapter {

    private Context context;

    private List<Pessoa> pessoas;

    private String[] tipos;

    private static class PessoaHolder {
        public TextView textViewValorNome;
        public TextView textViewValorTipo;
        public TextView textViewValorBolsista;
        public TextView textViewValorMaoUsada;
    }

    public PessoaAdapter(Context context, List<Pessoa> pessoas){
        this.context = context;
        this.pessoas = pessoas;

        tipos = context.getResources().getStringArray(R.array.tipos);
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PessoaHolder holder;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.linha_lista_pessoas, parent, false);

            holder = new PessoaHolder();

            holder.textViewValorNome     = convertView.findViewById(R.id.textViewValorNome);
            holder.textViewValorTipo     = convertView.findViewById(R.id.textViewValorTipo);
            holder.textViewValorBolsista = convertView.findViewById(R.id.textViewValorBolsista);
            holder.textViewValorMaoUsada = convertView.findViewById(R.id.textViewValorMaoUsada);

            convertView.setTag(holder);

        }else{

            holder = (PessoaHolder) convertView.getTag();

        }

        Pessoa pessoa = pessoas.get(position);

        holder.textViewValorNome.setText(pessoa.getNome());

        holder.textViewValorTipo.setText(tipos[pessoa.getTipo()]);

        if (pessoa.isBolsista()){
            holder.textViewValorBolsista.setText(R.string.possui_bolsa);
        }else{
            holder.textViewValorBolsista.setText(R.string.nao_possui_bolsa);
        }

        if (pessoa.getMaoUsada() == MaoUsada.Direita){
            holder.textViewValorMaoUsada.setText(R.string.direita);
        }else
            if (pessoa.getMaoUsada() == MaoUsada.Esquerda){
                holder.textViewValorMaoUsada.setText(R.string.esquerda);
            }else
                if (pessoa.getMaoUsada() == MaoUsada.Ambas){
                    holder.textViewValorMaoUsada.setText(R.string.ambas);
                }

        return convertView;
    }
}
