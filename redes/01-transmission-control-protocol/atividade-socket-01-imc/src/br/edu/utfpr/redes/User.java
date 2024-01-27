package br.edu.utfpr.redes;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private double weight;
	private double height;
	private double imc;
	
	public User() { }

	public User(double weight, double height) {
		this.weight = weight;
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}
	
}	
