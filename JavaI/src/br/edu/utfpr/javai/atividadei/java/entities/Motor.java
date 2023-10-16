package br.edu.utfpr.javai.atividadei.java.entities;

public class Motor {
	// Attributes
	private int qtdPist;
	private int potencia;
	
	// Constructors
	public Motor() { 
		this.qtdPist = 0;
		this.potencia = 0;
	}
	
	public Motor(int qtdPist, int potencia) {
		this.qtdPist = qtdPist;
		this.potencia = potencia;
	}
	
	// Getters and Setter Methods
	public int getQtdPist() {
		return qtdPist;
	}
	
	public void setQtdPist(int qtdPist) {
		this.qtdPist = qtdPist;
	}
	
	public int getPotencia() {
		return potencia;
	}
	
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
}
