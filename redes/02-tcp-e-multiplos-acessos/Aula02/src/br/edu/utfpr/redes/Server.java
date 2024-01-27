package br.edu.utfpr.redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

	private Socket conection;

	public Server() { }

	public Server(Socket conection) {
		this.conection = conection;
	}

	@Override
	public void run() {
		try {
			DataInputStream input = new DataInputStream(conection.getInputStream());
			String phrase = input.readUTF();

			String newPhrase = phrase.toUpperCase();
			DataOutputStream output = new DataOutputStream(conection.getOutputStream());
			output.writeUTF(newPhrase);

			conection.close();
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(52000)) {

			while (true) {
				System.out.println("Aguardando conexao...");

				Socket conection = server.accept();
				Server threadServer = new Server(conection);

				threadServer.start();
			}

		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
