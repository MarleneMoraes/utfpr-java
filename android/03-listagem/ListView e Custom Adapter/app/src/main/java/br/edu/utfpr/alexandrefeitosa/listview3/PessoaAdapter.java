package br.edu.utfpr.alexandrefeitosa.listview3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PessoaAdapter extends BaseAdapter {

    private Context      context;
    private List<Pessoa> pessoas;
    private NumberFormat numberFormat;

    private static class PessoaHolder {
        public TextView textViewValorNome;
        public TextView textViewValorRendaMensal;
        public TextView textViewValorTipo;
        public TextView textViewValorBrasileiro;
    }

    public PessoaAdapter(Context context, List<Pessoa> pessoas) {
        this.context = context;
        this.pessoas = pessoas;

        /* Cria uma instância do NumberFormat para formatar valores monetários em String utilizando os
           parâmetros de localização para o Brasil.
           Isto é feito para tratar os valores que o aplicativo mostra sempre como Reais, independente
           do idioma escolhido pelo usuário no aparelho */

        numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int i) {
        return pessoas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        PessoaHolder holder;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_lista_pessoas, viewGroup, false);

            holder = new PessoaHolder();

            holder.textViewValorNome        = view.findViewById(R.id.textViewValorNome);
            holder.textViewValorRendaMensal = view.findViewById(R.id.textViewValorRendaMensal);
            holder.textViewValorTipo        = view.findViewById(R.id.textViewValorTipo);
            holder.textViewValorBrasileiro  = view.findViewById(R.id.textViewValorBrasileiro);

            view.setTag(holder);

        } else {

            holder = (PessoaHolder) view.getTag();
        }

        holder.textViewValorNome.setText(pessoas.get(i).getNome());

        // Converte o float para uma String apropiada para representar valores monetários em reais
        String rendaMensalFormatada = numberFormat.format(pessoas.get(i).getRendaMensal());

        holder.textViewValorRendaMensal.setText(rendaMensalFormatada);

        switch(pessoas.get(i).getTipo()){
            case Aluno:
                holder.textViewValorTipo.setText(R.string.aluno);
                break;
            case Tutor:
                holder.textViewValorTipo.setText(R.string.tutor);
                break;
            case Professor:
                holder.textViewValorTipo.setText(R.string.professor);
                break;
        }

        if (pessoas.get(i).isBrasileiro()){
            holder.textViewValorBrasileiro.setText(context.getResources().getString(R.string.brasileiro));
        }else{
            holder.textViewValorBrasileiro.setText(context.getResources().getString(R.string.estrangeiro));
        }

        return view;
    }
}

