package br.edu.utfpr.redes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private static Socket connection;
	private static DataInputStream input;
	private static ObjectOutputStream output;

	public static void main(String[] args) {
		try {
			connection = new Socket("127.0.0.1", 55000);
			
			Person person = new Person();
			person.setName("Rogerio");
			person.setAge(51);
			
			output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(person);
			
			input = new DataInputStream(connection.getInputStream());
			String response = input.readUTF();
			System.out.println("Resposta do servidor: " + response);
			
			connection.close();
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
