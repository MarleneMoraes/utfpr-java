
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Cliente {
    
    public static void main(String[] args) {
        
        try {
            
            DatagramSocket dgSocket = new DatagramSocket();
            byte[] msg = new byte[128];
            
            String solicitacao = "Mande uma piada";
            msg = solicitacao.getBytes();
            
            InetAddress endereco =
                    InetAddress.getByName("localhost");
            
            DatagramPacket dgPacket =
                    new DatagramPacket(msg, msg.length, endereco, 40000);
            
            dgSocket.send(dgPacket);
            
            
            msg = new byte[128];
            
            dgPacket = new DatagramPacket(msg, msg.length);
            
            dgSocket.receive(dgPacket);
            
            String piada = new String(dgPacket.getData());
            System.out.println("A piada é: " + piada);
            
            
        } catch(Exception e) {
            
        }
        
    }
    
}
