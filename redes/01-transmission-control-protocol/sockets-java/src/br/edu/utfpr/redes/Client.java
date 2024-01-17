package br.edu.utfpr.redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private static Socket conection;
	private static DataInputStream input;
	private static DataOutputStream output;

	public static void main(String[] args) {
		// REGRA DE NEGOCIO
		try {
			// conectar ao servidor
			conection = new Socket("127.0.0.1", 55000);

			// enviar o valor inteiro
			output = new DataOutputStream(conection.getOutputStream());
			int number = 10;
			output.writeInt(number);

			// receber do servidor
			input = new DataInputStream(conection.getInputStream());
			String answer = input.readUTF();
			System.out.println("Resposta do servidor: " + answer);

			// fechar conexao
			conection.close();

		} catch (IOException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
