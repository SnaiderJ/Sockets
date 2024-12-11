package chat_simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int puertoServer = 49171;

        try {
            System.out.println("Esperando una conexión...");
            serverSocket = new ServerSocket(puertoServer, 10000);
            socket = serverSocket.accept();
            System.out.println(socket.getInetAddress() + " se ha conectado");

            EnviarThread enviar = new EnviarThread(socket);
            RecibirThread recibir = new RecibirThread(socket);

            recibir.start();
            enviar.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
