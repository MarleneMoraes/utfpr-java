package br.edu.utfpr.alexandrefeitosa.internacionalizacao2;

import java.util.Comparator;

public class Pessoa {

    public static Comparator ordenacaoCrescente = new Comparator<Pessoa>() {
        @Override
        public int compare(Pessoa pessoa1, Pessoa pessoa2) {
            return pessoa1.getNome().compareToIgnoreCase(pessoa2.getNome());
        }
    };

    public static Comparator ordenacaoDecrescente = new Comparator<Pessoa>() {
        @Override
        public int compare(Pessoa pessoa1, Pessoa pessoa2) {
            return -1 * pessoa1.getNome().compareToIgnoreCase(pessoa2.getNome());
        }
    };

    private String nome;

    private int tipo;

    private boolean bolsista;

    private MaoUsada maoUsada;

    public Pessoa(String nome, int tipo, boolean bolsista, MaoUsada maoUsada) {
        this.nome     = nome;
        this.tipo     = tipo;
        this.bolsista = bolsista;
        this.maoUsada = maoUsada;
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

    public boolean isBolsita() {
        return bolsista;
    }

    public void setBolsita(boolean bolsita) {
        this.bolsista = bolsita;
    }

    public MaoUsada getMaoUsada() {
        return maoUsada;
    }

    public void setMaoUsada(MaoUsada maoUsada) {
        this.maoUsada = maoUsada;
    }

    @Override
    public String toString() {
        return nome + " - " + tipo + " - " + bolsista + " - " + maoUsada;
    }
}
