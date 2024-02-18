package br.edu.utfpr.redes.aula3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	
	private static Socket conexao;
	private static DataInputStream entrada;
	private static ObjectOutputStream saida;
	
	public static void main(String[] args) {
		try {
			conexao = new Socket("127.0.0.1", 55000);
			
			Pessoa pessoa = new Pessoa("Rogerio", 51);
			
			saida = new ObjectOutputStream(conexao.getOutputStream());
			saida.writeObject(pessoa);
			
			entrada = new DataInputStream(conexao.getInputStream());
			System.out.println("Resposta do servidor: " + entrada.readUTF());
			
			conexao.close();
			
		} catch (IOException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
