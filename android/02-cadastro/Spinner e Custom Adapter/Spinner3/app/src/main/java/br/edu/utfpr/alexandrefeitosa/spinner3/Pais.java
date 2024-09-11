package br.edu.utfpr.alexandrefeitosa.spinner3;

import android.graphics.drawable.Drawable;

public class Pais {

    private String   nome;
    private Drawable bandeira;

    public Pais(String nome, Drawable bandeira){
        this.nome     = nome;
        this.bandeira = bandeira;
    }

    public Drawable getBandeira() {
        return bandeira;
    }

    public void setBandeira(Drawable bandeira) {
        this.bandeira = bandeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
