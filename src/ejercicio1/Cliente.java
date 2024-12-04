package ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		Socket socket = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		String ipServer = "127.0.0.1";
		int puertoServer = 49171;

		try {
			socket = new Socket(ipServer, puertoServer);

			System.out.println("Conexión realizada con el servidor");
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataOutputStream.writeUTF("Hola servidor, soy un cliente");
			dataOutputStream.flush();
			
			dataInputStream = new DataInputStream(socket.getInputStream());
			System.out.println(dataInputStream.readUTF());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != dataInputStream)
					dataInputStream.close();
			} catch (IOException e) {}
			try {
				if (null != dataOutputStream)
					dataOutputStream.close();
			} catch (IOException e) {}
			try {
				if (null != socket)
					socket.close();
			} catch (IOException e) {}
		}
	}
}