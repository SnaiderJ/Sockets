package ejercicio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {

		Socket socket = null;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		String ipServer = "127.0.0.1";
		int puertoServer = 49171;

		try {
			socket = new Socket(ipServer, puertoServer);

			System.out.println("Conexión realizada con el servidor");
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			Persona p = (Persona) objectInputStream.readObject();
			p.setNombre("Carmen");

			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(p);
			objectOutputStream.flush();
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
		}
	}
}