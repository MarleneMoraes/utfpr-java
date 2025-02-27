<<<<<<< HEAD
package chatUDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class BatePapo extends Thread {

	private static String usuario = null;
	private static InetAddress endereco;
	private static int porta;
	private MulticastSocket conexao;
	private static MulticastSocket conexaoStatic;

	public void run() {
		try {
			byte[] msg = new byte[128];
			
			conexao = new MulticastSocket(porta);
			conexao.joinGroup(new InetSocketAddress(endereco, porta), NetworkInterface.getByInetAddress(endereco));
			
			// Recebimento das mensagens dos usuarios
			while(true) {
				DatagramPacket datagrama = new DatagramPacket(msg, msg.length);
				conexao.receive(datagrama);
				
				String mensagem = new String(datagrama.getData());
				
				if(!mensagem.contains(usuario + " diz: ")) {
					System.out.println("\n" + mensagem);
					System.out.print("Digite a mensagem: ");
				}
				
				msg = new byte[128];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Par�metros est�o incorretos!");
			System.out.println("java BatePapo <endereco_multicast> <porta>");
			System.exit(1);
		}

		try {
			endereco = InetAddress.getByName(args[0]);
			porta = Integer.parseInt(args[1]);

			BatePapo bp = new BatePapo();
			bp.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Digite seu nome: ");
			usuario = reader.readLine();

			conexaoStatic = new MulticastSocket();
			conexaoStatic.joinGroup(new InetSocketAddress(endereco, porta), NetworkInterface.getByInetAddress(endereco));
			
			byte[] msg = new byte[128];
			
			while(true) {
				System.out.print("Digite a mensagem: ");
				
				String mensagem = reader.readLine();
				
				if(mensagem.equals("sair")) {
					System.exit(0);
				}
				
				mensagem = usuario + " diz: " + mensagem;
				
				msg = mensagem.getBytes();
				DatagramPacket datagrama = new DatagramPacket(msg, msg.length, endereco, porta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
=======
package chatUDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class BatePapo extends Thread {

	private static String usuario = null;
	private static InetAddress endereco;
	private static int porta;
	private MulticastSocket conexao;
	private static MulticastSocket conexaoStatic;

	public void run() {
		try {
			byte[] msg = new byte[128];
			
			conexao = new MulticastSocket(porta);
			conexao.joinGroup(new InetSocketAddress(endereco, porta), NetworkInterface.getByInetAddress(endereco));
			
			// Recebimento das mensagens dos usuarios
			while(true) {
				DatagramPacket datagrama = new DatagramPacket(msg, msg.length);
				conexao.receive(datagrama);
				
				String mensagem = new String(datagrama.getData());
				
				if(!mensagem.contains(usuario + " diz: ")) {
					System.out.println("\n" + mensagem);
					System.out.print("Digite a mensagem: ");
				}
				
				msg = new byte[128];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Par�metros est�o incorretos!");
			System.out.println("java BatePapo <endereco_multicast> <porta>");
			System.exit(1);
		}

		try {
			endereco = InetAddress.getByName(args[0]);
			porta = Integer.parseInt(args[1]);

			BatePapo bp = new BatePapo();
			bp.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Digite seu nome: ");
			usuario = reader.readLine();

			conexaoStatic = new MulticastSocket();
			conexaoStatic.joinGroup(new InetSocketAddress(endereco, porta), NetworkInterface.getByInetAddress(endereco));
			
			byte[] msg = new byte[128];
			
			while(true) {
				System.out.print("Digite a mensagem: ");
				
				String mensagem = reader.readLine();
				
				if(mensagem.equals("sair")) {
					System.exit(0);
				}
				
				mensagem = usuario + " diz: " + mensagem;
				
				msg = mensagem.getBytes();
				DatagramPacket datagrama = new DatagramPacket(msg, msg.length, endereco, porta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
>>>>>>> 579da954772a5bc049611ab4f3755427f1b88666
}