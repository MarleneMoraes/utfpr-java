package br.edu.utfpr.redes.aula6;

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

public class threadCliente implements Runnable{
    
    private String nome;
    private int idade;
    private JTextArea jTArea;
    
    private DatagramSocket conexao;
    private DatagramPacket datagrama;
    private ByteArrayOutputStream saidaStream;
    private ObjectOutputStream saida;
    
    public threadCliente(String n, int i, JTextArea j){
        this.nome = n;
        this.idade = i;
        this.jTArea = j;
    }

    @Override
    public void run() {
        try {
            conexao = new DatagramSocket();
            Pessoa p = new Pessoa();
            p.setNome(nome);
            p.setIdade(idade);

            saidaStream = new ByteArrayOutputStream();
            saida = new ObjectOutputStream(saidaStream);
            saida.writeObject(p);

            byte[] dados = saidaStream.toByteArray();
            DatagramPacket pacoteEnviado = new DatagramPacket(dados, dados.length, InetAddress.getByName("localhost"), 50000);
            conexao.send(pacoteEnviado);

            datagrama = new DatagramPacket(new byte[1024], 1024);
            conexao.receive(datagrama);
            String resposta = new String(datagrama.getData());
            jTArea.append(resposta + "\n");
        } catch (SocketException ex) {
            Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
