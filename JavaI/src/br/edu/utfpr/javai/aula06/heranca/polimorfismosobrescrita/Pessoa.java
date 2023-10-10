package br.edu.utfpr.javai.aula06.heranca.polimorfismosobrescrita;

public abstract class Pessoa {
    private String cpf;
    private String nome; 

    public void impLocal() {
        System.out.println("\n\nEstou na classe Pessoa \n\n");
    };

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}