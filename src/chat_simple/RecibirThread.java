package chat_simple;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RecibirThread extends Thread {
    private Socket socket;
    private DataInputStream dataInputStream;

    public RecibirThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            String mensaje;

            while (true) {
                mensaje = dataInputStream.readUTF();
                
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Conexión cerrada por el otro lado.");
                    break;
                }
                System.out.println("Recibido: " + mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

