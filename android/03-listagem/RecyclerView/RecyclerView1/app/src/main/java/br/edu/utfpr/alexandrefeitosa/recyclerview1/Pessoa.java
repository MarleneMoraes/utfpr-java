package br.edu.utfpr.alexandrefeitosa.recyclerview1;

public class Pessoa {

    private String nome;
    private int    idade;

    public Pessoa(String nome, int idade){
        setNome(nome);
        setIdade(idade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
