package br.edu.utfpr.redes.aula4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;

public class ThreadCliente implements Runnable {
	
	private String nome;
	private int idade;
	private JTextArea jTArea;
	
	private DatagramSocket conexao;
	private DatagramPacket datagrama;
	private ByteArrayOutputStream saidaStream;
	private ObjectOutputStream saida;

	public ThreadCliente(String nome, int idade, JTextArea jTArea) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.jTArea = jTArea;
	}

	@Override
	public void run() {
		try {
			conexao = new DatagramSocket();
			
			Pessoa pessoa = new Pessoa(nome, idade);
			
			saidaStream = new ByteArrayOutputStream();
			saida = new ObjectOutputStream(saidaStream);
			saida.writeObject(pessoa);
			
			byte[] dados = saidaStream.toByteArray();
			DatagramPacket pacoteEnviado = new DatagramPacket(dados, dados.length, InetAddress.getByName("127.0.0.1"), 50000);
			
			conexao.send(pacoteEnviado);
			
			datagrama = new DatagramPacket(new byte[120], 120);
			conexao.receive(datagrama);
			
			String respostaServidor = new String(datagrama.getData());
			jTArea.append(respostaServidor + "\n");
			
			
		} catch (SocketException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
