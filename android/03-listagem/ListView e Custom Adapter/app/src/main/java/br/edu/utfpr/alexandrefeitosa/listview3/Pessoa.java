package br.edu.utfpr.alexandrefeitosa.listview3;

public class Pessoa {

    private String  nome;
    private float   rendaMensal;
    private Tipo    tipo;
    private boolean brasileiro;

    public Pessoa(String nome){
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(float rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean isBrasileiro() {
        return brasileiro;
    }

    public void setBrasileiro(boolean brasileiro) {
        this.brasileiro = brasileiro;
    }
}
