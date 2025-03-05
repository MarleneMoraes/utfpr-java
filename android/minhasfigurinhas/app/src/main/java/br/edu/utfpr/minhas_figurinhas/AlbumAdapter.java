package br.edu.utfpr.minhas_figurinhas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.utfpr.minhas_figurinhas.model.Album;

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

        holder.textViewTitleValue.setText(album.getTitle());
        holder.textViewCountryValue.setText(album.getCountry().equals(R.string.brazil) ?
                                                                R.string.brazil : R.string.others);

        switch (album.getCategory()) {
            case Sports:
                holder.textViewCategoryValue.setText(R.string.sports);
                break;
            case Movies:
                holder.textViewCategoryValue.setText(R.string.movies);
                break;
            case TV_Shows:
                holder.textViewCategoryValue.setText(R.string.tv_shows);
                break;
            case Celebrities:
                holder.textViewCategoryValue.setText(R.string.celebrities);
                break;
            case Cartoons:
                holder.textViewCategoryValue.setText(R.string.cartoons);
                break;
            case Anime_and_Manga:
                holder.textViewCategoryValue.setText(R.string.anime_manga);
                break;
            case History_and_Culture:
                holder.textViewCategoryValue.setText(R.string.history_culture);
                break;
            case Nature_and_Science:
                holder.textViewCategoryValue.setText(R.string.nature_science);
                break;
            case Space_and_Astronomy:
                holder.textViewCategoryValue.setText(R.string.space_astronomy);
                break;
            case Themed:
                holder.textViewCategoryValue.setText(R.string.themed);
                break;
            case Commemorative_Editions:
                holder.textViewCategoryValue.setText(R.string.commemorative_editions);
                break;
            case Games:
                holder.textViewCategoryValue.setText(R.string.games);
                break;
            default:
                holder.textViewCategoryValue.setText(R.string.none);
                break;
        }

        holder.textViewQtdStickersValue.setText(String.valueOf(album.getQtdStickers()));

        holder.textViewShinyValue.setText(album.isShiny() ? R.string.yes : R.string.no);

        return convertView;
    }
}