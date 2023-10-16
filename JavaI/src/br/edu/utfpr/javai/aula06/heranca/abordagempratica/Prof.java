package br.edu.utfpr.javai.aula06.heranca.abordagempratica;

public class Prof extends Pessoa {
    private double salario;
    private String titulo; 

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}