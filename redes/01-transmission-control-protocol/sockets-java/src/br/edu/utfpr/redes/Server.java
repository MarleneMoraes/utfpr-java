package br.edu.utfpr.redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marlene Moraes
 * @date 2024-01-17
 * 
 * Aula Sockets UTFPR-CP
 */

public class Server {
	
	private static ServerSocket server;
	private static Socket conection;
	private static DataInputStream input;
	private static DataOutputStream output;
	
	public static void main(String[] args) {
		// REGRA DE NEGOCIO
		try {
			System.out.println("Aguardando conexao...");
			// especificar uma porta
			server = new ServerSocket(55000);
			
			// aguardar conexao
			conection = server.accept();
			
			// receber dados do cliente (tipo int)
			int value;
			
			input = new DataInputStream(conection.getInputStream());
			value = input.readInt();
			
			// realizar a verificacao do valor
			String result = "";
			
			if(value > 0) {
				result = "O valor e maior que zero.";
			} else {
				result = "O valor e menor ou igual a zero.";
			}
						
			// retornar os dados pro cliente
			output = new DataOutputStream(conection.getOutputStream());
			
			output.writeUTF(result);
			
			// fechar a conexao
			conection.close();
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
