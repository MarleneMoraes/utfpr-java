package br.edu.utfpr.javai.atividadev.java.entities;

public class Pessoa {
	private String nome;
	private short idade;
	private double salario;
	private boolean maior_18;

	public Pessoa() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public short getIdade() {
		return idade;
	}

	public void setIdade(short idade) {
		this.idade = idade;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isMaior_18() {
		return maior_18;
	}

	public void setMaior_18(boolean maior_18) {
		this.maior_18 = maior_18;
	}

	@Override
	public String toString() {
		return "[Pessoa]\nNome: " + getNome() + "\nIdade: " + getIdade() + "\nSalario: " + getSalario()
				+ "\nMaior de idade: " + isMaior_18();
	}
}
