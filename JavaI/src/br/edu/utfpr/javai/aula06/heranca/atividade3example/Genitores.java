package br.edu.utfpr.javai.aula06.heranca.atividade3example;

public abstract class Genitores {
    private int rgGens;
    private String nomeGens;
    private Filho filhoGens;

    public Genitores() {
        this.rgGens = 0;
        this.nomeGens = "";
        this.filhoGens = new Filho();
    }

    public int getRgGens() {
        return rgGens;
    }

    public void setRgGens(int rgGens) {
        this.rgGens = rgGens;
    }

    public String getNomeGens() {
        return nomeGens;
    }

    public void setNomeGens(String nomeGens) {
        this.nomeGens = nomeGens;
    }

    public Filho getFilhoGens() {
        return filhoGens;
    }

    public void setFilhoGens(Filho filhoGens) {
        this.filhoGens = filhoGens;
    }

    public abstract int calcRg();
}