package br.edu.utfpr.alexandrefeitosa.botaoup;

import java.util.Comparator;

public class Pessoa {

    public static Comparator comparator = new Comparator<Pessoa>() {
        @Override
        public int compare(Pessoa pessoa1, Pessoa pessoa2) {
            return pessoa1.getNome().compareToIgnoreCase(pessoa2.getNome());
        }
    };

    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
