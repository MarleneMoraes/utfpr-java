/*
Projeto exemplo de envio de um inteiro e recebimento de um float
*/

package br.edu.utfpr.redes.aula4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static DatagramSocket conexao;
    public static DatagramPacket datagrama;
    public static InetAddress endereco;
    
    public static ByteArrayOutputStream saidaStream;
    public static DataOutputStream saida;
    
    public static ByteArrayInputStream entradaStream;
    public static DataInputStream entrada;
    
    public static void main(String[] args) {
        
        try {
            
            saidaStream = new ByteArrayOutputStream();
            saida = new DataOutputStream(saidaStream);
            
            conexao = new DatagramSocket();
            
            saida.writeInt(100);
            
            byte[] dadosServidor = saidaStream.toByteArray();
            endereco = InetAddress.getByName("127.0.0.1");
            datagrama = new DatagramPacket(dadosServidor, dadosServidor.length,endereco,55000);
            
            conexao.send(datagrama);
            
            byte[] dadosRetorno = new byte[120];
            
            datagrama = new DatagramPacket(dadosRetorno, dadosRetorno.length);
            conexao.receive(datagrama);
            
            entradaStream = new ByteArrayInputStream(datagrama.getData());
            entrada = new DataInputStream(entradaStream);
            
            float valor = entrada.readFloat();
            
            System.out.println("Resposta de ponto flutuante do servidor: "+valor);
            
            
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
