package ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		int puertoServer = 49171; // Coge uno libre...

		while (true) {
			try {
				serverSocket = new ServerSocket(puertoServer, 10000);
				socket = serverSocket.accept(); // En espera...
				dataInputStream =  new DataInputStream(socket.getInputStream());
				System.out.println("Recibido" + dataInputStream.readUTF());
				
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataOutputStream.writeUTF("saludos desde el servidor al cliente");
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != dataOutputStream)
						dataOutputStream.close();
				} catch (IOException e) {
					// No importa...
				}
				try {
					if (null != dataInputStream)
						dataInputStream.close();
				} catch (IOException e) {
					// No importa...
				}
				try {
					if (null != socket)
						socket.close();
				} catch (IOException e) {
					// No importa...
				}
				try {
					if (null != serverSocket)
						serverSocket.close();
				} catch (IOException e) {
					// No importa...
				}
			}
		}
	}
}
