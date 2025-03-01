package br.edu.utfpr.minhas_figurinhas;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class Album {

    public static Comparator<Album> sortAscending = new Comparator<Album>() {
        @Override
        public int compare(Album album1, Album album2) {
            return album1.getTitulo().compareToIgnoreCase(album2.getTitulo());
        }
    };
    private String titulo;
    private int qtdFigurinhas;
    private String pais;
    private boolean brilhante;
    private CategoriaAlbum categoria;

    public Album(String titulo, int qtdFigurinhas, String pais, boolean brilhante, CategoriaAlbum categoria) {
        this.titulo = titulo;
        this.qtdFigurinhas = qtdFigurinhas;
        this.pais = pais;
        this.brilhante = brilhante;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQtdFigurinhas() {
        return qtdFigurinhas;
    }

    public void setQtdFigurinhas(int qtdFigurinhas) {
        this.qtdFigurinhas = qtdFigurinhas;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isBrilhante() {
        return brilhante;
    }

    public void setBrilhante(boolean brilhante) {
        this.brilhante = brilhante;
    }

    public CategoriaAlbum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAlbum categoria) {
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public String toString() {
        String  brilhante = isBrilhante() ? "Sim" : "Não";

        return "Titulo: " + getTitulo() + '\n' +
                "Figurinhas:" + getQtdFigurinhas() + '\n' +
                "País: " + getPais() + '\n' +
                "Tem brilhantes? " + brilhante + '\n' +
                "Categoria: " + getCategoria() + '\n';
    }
}
