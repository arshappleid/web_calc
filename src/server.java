
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;

public class server {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = null;
		int port = 5502; // 127.0.0.1:5502
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server Running at : " + serverSocket.toString());
			System.out.printf("Looking for incoming connections on port : %d...%n", port);
			while (true) {
				Socket clientSocket = serverSocket.accept(); // establish the connection here.
				System.out.println("Connection Established");
				new RequestProcessor(clientSocket).run();
			}

		} catch (IOException e) {
			System.out.println("Could not connect to server.");
			System.out.println(e.toString());
		}

	}

}