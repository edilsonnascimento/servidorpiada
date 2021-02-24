import java.io.*;
import java.net.*;

/*
 * @autor Edilson do Nascimento
 * Esta classe solicita uma piada ao servidor a cada conexão UDP.
 */
public class ClientePiada {

    private static boolean acabouPiadas = false;

    public static void main(String[] args) {

        while(!acabouPiadas){
            solicitaPiadaServidor();
        }

    }

    private static void solicitaPiadaServidor() {

        try {

            DatagramSocket socketCliente = new DatagramSocket();
            InetAddress endereco = InetAddress.getByName("localhost");
            int porta = 7777;
            byte[] mensagemEnvio = new byte[1024];

            //Envio de mensagemEnvio
            String mensagem = "Quero uma piada.";
            mensagemEnvio = mensagem.getBytes();
            DatagramPacket pacoteEnvio = new DatagramPacket(mensagemEnvio, mensagemEnvio.length, endereco, porta);
            socketCliente.send(pacoteEnvio);

            //Recebimento
            byte[] mensagemRecebida = new byte[1024];
             DatagramPacket pacoteRecebido = new DatagramPacket(mensagemRecebida, mensagemRecebida.length);

            socketCliente.receive(pacoteRecebido);

            String piadaRecebida = new String(pacoteRecebido.getData());

            System.out.println(piadaRecebida);

           if(piadaRecebida.contains("Sem mais piadas para enviar")){
                acabouPiadas = true;
            }

            socketCliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
