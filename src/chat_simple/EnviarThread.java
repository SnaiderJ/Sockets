package chat_simple;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EnviarThread extends Thread {

	Socket socket = null;
	DataOutputStream dataOutputStream = null;
	Scanner teclado = null;

	public EnviarThread(Socket socket) {
		this.socket = socket;
		teclado = new Scanner(System.in);
	}

	public void run() {
		try {
			String mensaje = teclado.nextLine();
			while (true) {
				if (mensaje == null || mensaje.equalsIgnoreCase("salir")) {
					System.out.println("Conexión cerrada por el usuario.");
					break;
				}
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataOutputStream.writeUTF(mensaje);
				dataOutputStream.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != dataOutputStream)
					dataOutputStream.close();
			} catch (IOException e) {
			} finally {
				try {
					if (dataOutputStream != null) {
						dataOutputStream.close();
					}
					if (teclado != null) {
						teclado.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}