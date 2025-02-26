package br.edu.utfpr.minhas_figurinhas;

import androidx.annotation.NonNull;

public class Album {
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

    public int getQtdFigurinhas() {
        return qtdFigurinhas;
    }

    public String getPais() {
        return pais;
    }

    public boolean isBrilhante() {
        return brilhante;
    }

    public CategoriaAlbum getCategoria() {
        return categoria;
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
