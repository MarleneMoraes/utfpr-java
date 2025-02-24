package br.edu.utfpr.alexandrefeitosa.sharedpreferences2;

import java.util.Comparator;

public class Pessoa {

    public static Comparator comparator = new Comparator<Pessoa>() {
        @Override
        public int compare(Pessoa pessoa1, Pessoa pessoa2) {
            return pessoa1.getNome().compareToIgnoreCase(pessoa2.getNome());
        }
    };

    private String nome;

    private int tipo;

    public Pessoa(String nome, int tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nome + " - " + tipo;
    }
}
