import java.io.*;
import java.net.*;

/*
* @autor Edilson do Nascimento
* Esta classe cria um servidor UDP, lê o aquivo de piadas.txt na raiz do projeto e envia uma piada a cada solicitação do cliente.
 */
public class ServidorPiada {

    public static void main(String[] args) throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(7777);
        byte[] mensagem = new byte[1024];
        //abre arquivo de piada para leitura
        BufferedReader arquivoLeitura = new BufferedReader(new FileReader(new File("piadas.txt")));
        boolean continua = true;

        while(continua) {

            DatagramPacket pacoteReceber = new DatagramPacket(mensagem, mensagem.length);
            serverSocket.receive(pacoteReceber);

            String mensagemCliente = new String(pacoteReceber.getData());
            System.out.println("A mensagem recebida eh: " + mensagemCliente);

            InetAddress enderecoCliente = pacoteReceber.getAddress();
            int portaCliente = pacoteReceber.getPort();

            if(arquivoLeitura.ready()) {
                String piada = arquivoLeitura.readLine();
                byte[] piadaEnviar = piada.getBytes();
                DatagramPacket pacoteEnviar = new DatagramPacket(piadaEnviar, piadaEnviar.length, enderecoCliente, portaCliente);
                serverSocket.send(pacoteEnviar);
            }

        }
        arquivoLeitura.close();
        serverSocket.close();
    }

}
