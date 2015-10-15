import java.io.*;
import java.net.*;

class Client {
	
	public static int port;
	Client() {
		// which calls the common constructor with the GUI set to null
		this("localhost", port);
	}
	
	public Client(String string, int i) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] argv) throws Exception {
		int port = Integer.parseInt(argv[0]);
		String message = "HELO text\n";	// this is the message sent to the server
		Socket socket = new Socket("localhost", port);	// initialise the socket
		DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
		outToServer.writeBytes(message + "\n"); // send this to the server
		outToServer.close();
		socket.close();
	}
}
