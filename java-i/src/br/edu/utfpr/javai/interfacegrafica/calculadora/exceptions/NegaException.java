package br.edu.utfpr.javai.interfacegrafica.calculadora.exceptions;

public class NegaException extends Exception{
	private static final long serialVersionUID = 1L;

	public NegaException() {
        super("O resultado é negativo");
    }
}
