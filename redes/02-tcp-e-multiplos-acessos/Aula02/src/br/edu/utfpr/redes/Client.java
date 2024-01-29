package br.edu.utfpr.redes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

	private static Socket conection;

	private static DataInputStream input;
	private static DataOutputStream output;

	public static void main(String[] args) {
		try {
			conection = new Socket("localhost", 52000);
			System.out.println("Digite uma frase: ");

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String phrase = reader.readLine();
			output = new DataOutputStream(conection.getOutputStream());
			output.writeUTF(phrase);

			input = new DataInputStream(conection.getInputStream());
			String response = input.readUTF();
			System.out.println("Resposta do servidor: " + response);

			conection.close();
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
