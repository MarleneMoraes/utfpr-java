package br.edu.utfpr.redes.aula4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Marlene V. Moraes de Oliveira
 *
 */

public class Servidor {

	private static DatagramSocket conexao;
	private static DatagramPacket datagrama;

	private static ByteArrayInputStream entradaStream;
	private static ObjectInputStream entrada;

	public static void Tempo() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static void main(String[] args) {
		try {
			conexao = new DatagramSocket(50000);
			
			
			while(true) {
				System.out.println("Aguardando conexao...");
				datagrama = new DatagramPacket(new byte[1024], 1024);
				conexao.receive(datagrama);
				
				entradaStream = new ByteArrayInputStream(datagrama.getData());
				entrada = new ObjectInputStream(entradaStream);
				
				Pessoa pessoa = (Pessoa) entrada.readObject();
				System.out.println("Objeto Pessoa recebido pelo cliente\n\tNome: " + pessoa.getNome() + "\n\tIdade: " + pessoa.getIdade() + "\n");
				
				byte[] resposta = "Objeto Pessoa recebida pelo servidor".getBytes();
				
				datagrama = new DatagramPacket(resposta, resposta.length, datagrama.getAddress(), datagrama.getPort());
				
				Tempo();
				conexao.send(datagrama);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
