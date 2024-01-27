package br.edu.utfpr.redes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
		try (ServerSocket socket = new ServerSocket(50000)) {
			Socket connection = socket.accept();
			
			ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
			User user = (User) input.readObject();
			user.setImc(user.getWeight() / (Math.pow(user.getHeight(), 2)));

			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.writeDouble(user.getImc());

			input.close();
			output.close();
			connection.close();
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}

	}
}
