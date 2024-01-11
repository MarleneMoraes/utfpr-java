package br.edu.utfpr.javai.aula06.heranca.polimorfismosobrescrita;

public class Prof extends Pessoa {
    private final double salario = 1000;
    private String titulo;
    public String getSalario; 

    public void impLocal() {
        System.out.println("\n\nEstou na classe Professor \n\n");
        super.impLocal();
    };


    public double getSalario() {
        return salario;
    }

    /* 
    public void setSalario(double salario) {
        this.salario = salario;
    }
    */

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}