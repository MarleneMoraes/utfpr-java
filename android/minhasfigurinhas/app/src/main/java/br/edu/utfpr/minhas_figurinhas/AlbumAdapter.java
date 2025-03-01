package br.edu.utfpr.minhas_figurinhas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AlbumAdapter extends BaseAdapter {

    private Context context;
    private List<Album> albuns;

    private static class AlbumHolder {
        public TextView textViewTitleValue;
        public TextView textViewCountryValue;
        public TextView textViewCategoryValue;
        public TextView textViewQtdStickersValue;
        public TextView textViewShinyValue;
    }
    public AlbumAdapter(Context context, List<Album> albuns) {
        this.context = context;
        this.albuns = albuns;
    }

    @Override
    public int getCount() {
        return albuns.size();
    }

    @Override
    public Object getItem(int position) {
        return albuns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlbumHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.line_list_albuns, parent, false);

            holder = new AlbumHolder();

            holder.textViewTitleValue = convertView.findViewById(R.id.textViewTitleValue);
            holder.textViewCountryValue = convertView.findViewById(R.id.textViewCountryValue);
            holder.textViewCategoryValue = convertView.findViewById(R.id.textViewCategoryValue);
            holder.textViewQtdStickersValue = convertView.findViewById(R.id.textViewQtdStickersValue);
            holder.textViewShinyValue = convertView.findViewById(R.id.textViewShinyValue);

            convertView.setTag(holder);
        } else {
            holder = (AlbumHolder) convertView.getTag();
        }

        Album album = albuns.get(position);

        holder.textViewTitleValue.setText(album.getTitulo());
        holder.textViewCountryValue.setText(album.getPais().equals("Brasil") ?
                                                                R.string.brasil : R.string.outros);

        switch (album.getCategoria()) {
            case Esportes:
                holder.textViewCategoryValue.setText(R.string.esportes);
                break;
            case Filmes:
                holder.textViewCategoryValue.setText(R.string.filmes);
                break;
            case Series_de_TV:
                holder.textViewCategoryValue.setText(R.string.series_tv);
                break;
            case Celebridades:
                holder.textViewCategoryValue.setText(R.string.celebridades);
                break;
            case Desenhos_Animados:
                holder.textViewCategoryValue.setText(R.string.desenhos_animados);
                break;
            case Animes_Mangas:
                holder.textViewCategoryValue.setText(R.string.animes_mangas);
                break;
            case Historia_Cultura:
                holder.textViewCategoryValue.setText(R.string.historia_cultura);
                break;
            case Natureza_Ciencias:
                holder.textViewCategoryValue.setText(R.string.natureza_ciencias);
                break;
            case Espaco_Astronomia:
                holder.textViewCategoryValue.setText(R.string.espaco_astronomia);
                break;
            case Tematicos:
                holder.textViewCategoryValue.setText(R.string.tematicos);
                break;
            case Edicoes_Comemorativas:
                holder.textViewCategoryValue.setText(R.string.edicoes_comemorativas);
                break;
            case Jogos:
                holder.textViewCategoryValue.setText(R.string.jogos);
                break;
            default:
                holder.textViewCategoryValue.setText(R.string.nenhum);
                break;
        }

        holder.textViewQtdStickersValue.setText(String.valueOf(album.getQtdFigurinhas()));

        holder.textViewShinyValue.setText(album.isBrilhante() ? R.string.tem_brilhantes : R.string.nao_brilhantes);

        return convertView;
    }
}