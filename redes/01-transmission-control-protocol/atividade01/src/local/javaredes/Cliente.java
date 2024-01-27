package local.javaredes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marlene Moraes
 * 
 *  Atividade 01 - Redes
 */

public class Cliente {

	private static Socket conexao;
	private static DataInputStream input;
	private static DataOutputStream output;

	public static void main(String[] args) {
		try {
			conexao = new Socket("127.0.0.1", 55000);

			output = new DataOutputStream(conexao.getOutputStream());

			Scanner scanner = new Scanner(System.in);
			System.out.println("Digite um CPF para verificação: ");
			String valor = scanner.nextLine();
			scanner.close();
			
			output.writeBytes(valor + "\n");

			input = new DataInputStream(conexao.getInputStream());
			String resposta = input.readUTF();
			
			System.out.println("Resposta do servidor: " + resposta);

			conexao.close();

		} catch (IOException e) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
