package br.edu.utfpr.javai.atividadevi.java.exception;

public class VelocException extends Exception {
	private static final long serialVersionUID = 1L;

	public VelocException() {
        super("A velocidade máxima está fora dos limites brasileiros");
    }
}