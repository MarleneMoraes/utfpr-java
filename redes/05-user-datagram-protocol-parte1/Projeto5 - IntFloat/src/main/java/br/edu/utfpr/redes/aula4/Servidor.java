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
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    
    private static DatagramSocket conexao;
    private static DatagramPacket datagrama;    
    
    private static ByteArrayInputStream entradaStream;
    private static DataInputStream entrada;
    
    private static ByteArrayOutputStream saidaStream;
    private static DataOutputStream saida;
    
    public static void main(String[] args) {
        
        
        
        try {
            
            conexao = new DatagramSocket(55000);
            System.out.println("Aguardando conexao...");
            byte[] dados = new byte[120];
            datagrama = new DatagramPacket(dados, dados.length);
            conexao.receive(datagrama);
            
            entradaStream = new ByteArrayInputStream(datagrama.getData());
            entrada = new DataInputStream(entradaStream);
            
            int intCliente = entrada.readInt();
            
            System.out.println("Cliente enviou um inteiro: "+intCliente);
            
            float floatCliente = (float) (intCliente + 0.123456);
            
            saidaStream = new ByteArrayOutputStream();
            saida = new DataOutputStream(saidaStream);
            saida.writeFloat(floatCliente);
            
            byte[] dadosRetorno = saidaStream.toByteArray();
            
            datagrama = new DatagramPacket(dadosRetorno, dadosRetorno.length, datagrama.getAddress(),datagrama.getPort());

            conexao.send(datagrama);           
            
            
            
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
