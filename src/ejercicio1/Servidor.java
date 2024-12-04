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
		int puertoServer = 49171;
		int maxclientes = 3;
		
		try {
			serverSocket = new ServerSocket(puertoServer, 10000);

			for (int i = 0; i < maxclientes; i++) {
				socket = serverSocket.accept();
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataOutputStream.writeUTF("Recibido " + dataInputStream.readUTF() + "\n"
						+ "Saludos desde el servidor al cliente no: " + (i + 1));
			}
			
			Thread.sleep(1000);
			System.out.println("Demasiados clientes por hoy");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != dataOutputStream)
					dataOutputStream.close();
			} catch (IOException e) {
			}
			try {
				if (null != dataInputStream)
					dataInputStream.close();
			} catch (IOException e) {
			}
			try {
				if (null != socket)
					socket.close();
			} catch (IOException e) {
			}
			try {
				if (null != serverSocket)
					serverSocket.close();
			} catch (IOException e) {
			}
		}
	}
}