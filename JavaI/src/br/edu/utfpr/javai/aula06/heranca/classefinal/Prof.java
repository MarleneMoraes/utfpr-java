package br.edu.utfpr.javai.aula06.heranca.classefinal;

public class Prof extends Pessoa {
    private double salario;
    private String titulo; 

    public void impLocal() {
        System.out.println("\n\nEstou na classe Professor \n\n");
        super.impLocal();
    };


    public int getSalario() {
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