package br.edu.utfpr.javai.avaliacao.entities;

import br.edu.utfpr.javai.avaliacao.exception.NumException;
import br.edu.utfpr.javai.avaliacao.interfaces.Verifica;
import br.edu.utfpr.javai.avaliacao.utils.Endereco;

public abstract class ClienteBanco implements Verifica {
	private int numeroConta = 0;
	private String nome = "";
	private Endereco ender = new Endereco();

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) throws NumException {
		if (this.numeroConta >= 0) {
			this.numeroConta = numeroConta;
		} else {
			throw new NumException();
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEnder() {
		return ender;
	}

	public void setEnder(Endereco ender) {
		this.ender = ender;
	}

	@Override
	public void validar() {
		if (this.numeroConta % 2 == 0) {
			System.out.println("Este número é par.");
		} else {
			System.out.println("Este número é ímpar.");
		}
	}

	public abstract void verifDoc();
}
