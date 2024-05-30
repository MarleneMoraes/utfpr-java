package br.edu.utfpr.redes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	private static ServerSocket server;
	private static Socket connection;
	private static ObjectInputStream input;
	private static DataOutputStream output;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(55000);
			System.out.println("Aguardando conex�o...");
			connection = server.accept();
			
			input = new ObjectInputStream(connection.getInputStream());
			Person person = (Person) input.readObject();
			
			System.out.println("Nome: " + person.getName());
			System.out.println("Idade: " + person.getAge());
			
			output = new DataOutputStream(connection.getOutputStream());
			output.writeUTF("Objeto recebido");

			connection.close();
		} catch (IOException ioe) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ioe);
		} catch (ClassNotFoundException cnfe) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, cnfe);
		}
	}
}



