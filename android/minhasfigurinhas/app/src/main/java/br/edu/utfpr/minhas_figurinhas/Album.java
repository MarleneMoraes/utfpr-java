package br.edu.utfpr.minhas_figurinhas;

import androidx.annotation.NonNull;

import java.util.Comparator;

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
