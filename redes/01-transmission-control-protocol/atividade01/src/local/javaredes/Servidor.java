package local.javaredes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marlene Moraes
 * 
 * Atividade 01 - Redes
 */

public class Servidor {

	private static ServerSocket servidor;
	private static Socket conexao;
	private static DataInputStream input;
	private static DataOutputStream output;

	public static void main(String[] args) {
		try {
			System.out.println("Aceitando conexoes");
			servidor = new ServerSocket(55000);

			conexao = servidor.accept();

			input = new DataInputStream(conexao.getInputStream());
			String value = input.readLine();

			String result;

			if (validaCPF(value)) {
				result = "Este CPF e valido.";
			} else {
				result = "Este CPF e invalido.";
			}

			output = new DataOutputStream(conexao.getOutputStream());

			output.writeUTF(result);

			conexao.close();
		} catch (IOException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static boolean validaCPF(String cpf) {
		if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
			return false;

		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) { 
			return false;
		}

		int primeiroDigito = calcularDigito(cpf.substring(0, 9), PESO_CPF);
		int segundoDigito = calcularDigito(cpf.substring(0, 9) + primeiroDigito, PESO_CPF);
		return cpf.equals(cpf.substring(0, 9) + primeiroDigito + segundoDigito);
	}

	private static boolean isCPFPadrao(String cpf) {
		return cpf.equals("00000000000")|| cpf.equals("11111111111") 
				|| cpf.equals("22222222222") || cpf.equals("33333333333") 
				|| cpf.equals("44444444444") || cpf.equals("55555555555") 
				|| cpf.equals("66666666666") || cpf.equals("77777777777") 
				|| cpf.equals("88888888888") || cpf.equals("99999999999");
	}

	private static final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
