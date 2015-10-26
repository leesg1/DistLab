import java.io.IOException;
import java.net.InetAddress;
import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	private int port;
	private boolean keepGoing;
	public ServerSocket serverSocket;

	public Server(int port) {
		this.port = port;
	}

	public void start() throws UnknownHostException, IOException {
		keepGoing = true;
			serverSocket = new ServerSocket(port,0, InetAddress.getByName("0.0.0.0"));
			while (keepGoing) {
				Socket socket = serverSocket.accept(); 
				try {
					ThreadPool.executor.execute(new Worker(socket));
					if(ThreadPool.executor.isShutdown())	// if its shut down, end the program
						keepGoing  = false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!keepGoing)
					break;
			}
		}

	public static void main(String[] args) throws UnknownHostException, IOException {
		int portNumber = Integer.parseInt(args[0]);	//get port number from program parameters
		Server server = new Server(portNumber);
		server.start();
	}
}
