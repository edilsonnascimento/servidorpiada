import java.io.IOException;
import java.net.*;

public class ServidorPiada {

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(7777);
        byte[] mensagem = new byte[128];

        DatagramPacket datagramPacket = new DatagramPacket(mensagem, mensagem.length);

        datagramSocket.receive(datagramPacket);

        String msg = new String(datagramPacket.getData());
        System.out.println("A mensagem recebida eh: " + msg);

    }
}
