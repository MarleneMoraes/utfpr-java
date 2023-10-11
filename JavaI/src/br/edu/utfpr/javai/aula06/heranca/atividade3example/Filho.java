package br.edu.utfpr.javai.aula06.heranca.atividade3example;

public class Filho {
    private int rgFilho;
    private String nomeFilho;

    public Filho() {
        this.rgFilho = 0;
        this.nomeFilho = "";
    }

    public Filho(int rgFilho, String nomeFilho) {
        this.rgFilho = rgFilho;
        this.nomeFilho = nomeFilho;
    }

    public int getRgFilho() {
        return rgFilho;
    }

    public void setRgFilho(int rgFilho) {
        this.rgFilho = rgFilho;
    }

    public String getNomeFilho() {
        return nomeFilho;
    }

    public void setNomeFilho(String nomeFilho) {
        this.nomeFilho = nomeFilho;
    }
}