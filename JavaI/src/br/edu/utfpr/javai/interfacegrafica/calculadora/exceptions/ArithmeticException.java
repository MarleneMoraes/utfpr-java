package br.edu.utfpr.javai.interfacegrafica.calculadora.exceptions;

public class ArithmeticException extends Exception {
	private static final long serialVersionUID = 1L;

	public ArithmeticException() {
        super("Não existe divisão por zero.");
    }
}
