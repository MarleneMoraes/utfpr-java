package br.edu.utfpr.alexandrefeitosa.spinner3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PaisAdapter extends BaseAdapter {

    Context    context;
    List<Pais> paises;

    private static class PaisHolder {
        public ImageView imageViewBandeira;
        public TextView textViewNome;
    }

    public PaisAdapter(Context context, List<Pais> paises) {
        this.context = context;
        this.paises  = paises;
    }

    @Override
    public int getCount() {
        return paises.size();
    }

    @Override
    public Object getItem(int i) {
        return paises.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        PaisHolder holder;

        if (view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_paises, viewGroup, false);

            holder = new PaisHolder();

            holder.imageViewBandeira = view.findViewById(R.id.imageViewBandeira);
            holder.textViewNome      = view.findViewById(R.id.textViewNome);

            view.setTag(holder);

        }else{

            holder = (PaisHolder) view.getTag();
        }

        holder.imageViewBandeira.setImageDrawable(paises.get(i).getBandeira());
        holder.textViewNome.setText(paises.get(i).getNome());

        return view;
    }
}
