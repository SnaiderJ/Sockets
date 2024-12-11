package chat_simple;

import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        Socket socket = null;
        String ipServer = "127.0.0.1";
        int puertoServer = 49171;

        try {
            socket = new Socket(ipServer, puertoServer);
            System.out.println("Conexión realizada");

            EnviarThread enviar = new EnviarThread(socket);
            RecibirThread recibir = new RecibirThread(socket);

            enviar.start();
            recibir.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
