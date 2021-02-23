import java.io.IOException;
import java.net.*;

public class ClientePiada {

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] mensagem = new byte[128];

        //Envio de mensagem
        String msg = "Quero uam piada.";
        mensagem = msg.getBytes();

        InetAddress inetAddress = InetAddress.getByName("localhost");

        DatagramPacket datagramPacket = new DatagramPacket(mensagem, mensagem.length, inetAddress, 7777);

        datagramSocket.send(datagramPacket);

        //Recebimento de mensagem
        mensagem = new byte[128];
        datagramPacket = new DatagramPacket(mensagem, mensagem.length);

        datagramSocket.receive(datagramPacket);

        String piada = new String(datagramPacket.getData());
        System.out.println(piada);
    }
}
