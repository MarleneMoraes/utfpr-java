
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Servidor {
    
    private static final String piada = "O que o que é: anda no escuro e corre no claro? R: A luz!";
    
    public static void main(String[] args) {
        
        try { 
        
            DatagramSocket dgSocket = new DatagramSocket(40000);
        
            byte[] msg = new byte[128];
            
            DatagramPacket dgPacket = new DatagramPacket(msg, msg.length);
            
            dgSocket.receive(dgPacket);
            
            String solicitacao = new String(dgPacket.getData());
            
            if(solicitacao.contains("Mande uma piada")) {
                
                InetAddress endereco = dgPacket.getAddress();
                int porta = dgPacket.getPort();
                
                msg = new byte[128];
                
                msg = piada.getBytes();
                
                dgPacket = new DatagramPacket(msg, msg.length, endereco, porta);
                
                dgSocket.send(dgPacket);
                
            }
            
        } catch(Exception e) {
            
        }
    }
    
}
