package br.edu.utfpr.minhas_figurinhas.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;
import java.util.Objects;

import br.edu.utfpr.minhas_figurinhas.R;

@Entity
public class Album {

    public static Comparator<Album> sortAscending = new Comparator<Album>() {
        @Override
        public int compare(Album album1, Album album2) {
            return album1.getTitle().compareToIgnoreCase(album2.getTitle());
        }
    };

    public static Comparator<Album> sortDescending = new Comparator<Album>() {
        @Override
        public int compare(Album album1, Album album2) {
            return -1 * album1.getTitle().compareToIgnoreCase(album2.getTitle());
        }
    };

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    @ColumnInfo(index = true)
    private String title;
    private int qtdStickers;
    private String country;
    private boolean shiny;
    private Category category;

    public Album(String title, int qtdStickers, String country, boolean shiny, Category category) {
        this.title = title;
        this.qtdStickers = qtdStickers;
        this.country = country;
        this.shiny = shiny;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQtdStickers() {
        return qtdStickers;
    }

    public void setQtdStickers(int qtdStickers) {
        this.qtdStickers = qtdStickers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isShiny() {
        return shiny;
    }

    public void setShiny(boolean shiny) {
        this.shiny = shiny;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return qtdStickers == album.qtdStickers && shiny == album.shiny
                && title.equals(album.title) && country.equals(album.country)
                && category == album.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, qtdStickers, country, shiny, category);
    }

    @NonNull
    @Override
    public String toString() {
        String  shiny = isShiny() ? String.valueOf(R.string.yes) :
                                    String.valueOf(R.string.no);

        return "Titulo: " + getTitle() + '\n' +
                "Figurinhas:" + getQtdStickers() + '\n' +
                "País: " + getCountry() + '\n' +
                "Tem brilhantes? " + shiny + '\n' +
                "Categoria: " + getCategory() + '\n';
    }
}
