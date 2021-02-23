import java.io.IOException;
import java.net.*;

public class ServidorPiada {

    private static final String PIADA = "O que cai em pé e corre deitado? R: A chuva!";

    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(7777);
        byte[] mensagem = new byte[128];

        DatagramPacket datagramPacket = new DatagramPacket(mensagem, mensagem.length);

        datagramSocket.receive(datagramPacket);

        String msg = new String(datagramPacket.getData());
        System.out.println("A mensagem recebida eh: " + msg);

        InetAddress inetAddress = datagramPacket.getAddress();
        int porta = datagramPacket.getPort();

        mensagem = PIADA.getBytes();

        datagramPacket = new DatagramPacket(mensagem, mensagem.length, inetAddress, porta);

        datagramSocket.send(datagramPacket);

    }
}
