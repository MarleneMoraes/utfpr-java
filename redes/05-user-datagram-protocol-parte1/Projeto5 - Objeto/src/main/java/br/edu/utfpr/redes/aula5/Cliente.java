package br.edu.utfpr.redes.aula5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    
    private static DatagramSocket conexao;
    private static DatagramPacket datagrama;
    
    private static ByteArrayInputStream entradaStream;
    private static ObjectInputStream entrada;
    
    public static void main(String[] args) {

        try {
            conexao = new DatagramSocket();
            
            byte[] solicitação = "Solicitação de Pessoa".getBytes();
            datagrama = new DatagramPacket(solicitação, solicitação.length, InetAddress.getByName("127.0.0.1"),50000);
            conexao.send(datagrama);
            
            datagrama = new DatagramPacket(new byte[1024], 1024);
            conexao.receive(datagrama);

            // Converta os bytes recebidos de volta em uma instância da classe Pessoa
            entradaStream = new ByteArrayInputStream(datagrama.getData());
            entrada = new ObjectInputStream(entradaStream);
            Pessoa pessoaRecebida = (Pessoa) entrada.readObject();

            System.out.println("Objeto Pessoa recebido do servidor\nNome = " + pessoaRecebida.getNome() + "\nIdade = " + pessoaRecebida.getIdade());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
