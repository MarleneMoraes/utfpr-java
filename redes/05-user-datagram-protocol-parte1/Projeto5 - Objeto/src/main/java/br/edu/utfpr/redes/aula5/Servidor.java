package br.edu.utfpr.redes.aula5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Servidor {
    
    private static DatagramSocket conexao;
    private static DatagramPacket datagrama;
    
    private static ByteArrayOutputStream saidaStream;
    private static ObjectOutputStream saida;
    
    public static void main(String[] args) {        

        try {
            conexao = new DatagramSocket(50000);
            System.out.println("Aguardando conexoes... ");

            while (true) {
                
                Pessoa pessoaParaEnviar = new Pessoa();
                pessoaParaEnviar.setNome("Rogerio");
                pessoaParaEnviar.setIdade(51);
                
                saidaStream = new ByteArrayOutputStream();
                saida = new ObjectOutputStream(saidaStream);
                saida.writeObject(pessoaParaEnviar);

                byte[] dados = saidaStream.toByteArray();

                
                datagrama = new DatagramPacket(new byte[1024], 1024);
                conexao.receive(datagrama);

                DatagramPacket pacoteEnviado = new DatagramPacket(dados, dados.length, datagrama.getAddress(), datagrama.getPort());
                conexao.send(pacoteEnviado);

                System.out.println("Objeto pessoa enviado para o cliente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

