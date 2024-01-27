package br.edu.utfpr.redes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Marlene
 * 
 * List's exercise from Socket text
 *
 */

public class Server {
	public static void main(String[] args) throws ClassNotFoundException {
		try (ServerSocket socket = new ServerSocket(51000)) {
			Socket connection = socket.accept();
			ObjectInputStream input;
			
			//List<Person> people = new ArrayList<>(); 

			// while
			input = new ObjectInputStream(connection.getInputStream());
			Person person = (Person) input.readObject();
			
			/*if (person.getAge() >= 18) {
				people.add(person);
			}*/
			
			// final while
			
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(person);
			
			input.close();
			output.close();
			connection.close();
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
