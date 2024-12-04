package ejercicio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		Socket socket = null;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		int puertoServer = 49171;

		try {
			serverSocket = new ServerSocket(puertoServer, 10000);
			socket = serverSocket.accept();
			
			Persona p = new Persona();
			p.setNif("16078123X");
			p.setNombre("Pepito");
			p.setApellido("Grillo");
			p.setGenero('M');
			
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(p);
			objectOutputStream.flush();
			
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println(objectInputStream.readObject());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != objectOutputStream)
					objectOutputStream.close();
			} catch (IOException e) {
			}
			try {
				if (null != objectInputStream)
					objectInputStream.close();
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