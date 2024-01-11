package br.edu.utfpr.javai.atividadevii.java.exception;

public class VeicExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public VeicExistException() {
        super("Já existe um veículo com esta placa");
    }
}