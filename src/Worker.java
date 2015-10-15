import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class Worker implements Runnable {

	public Worker(Socket socket) throws IOException, InterruptedException {
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String message = inFromClient.readLine();	// read message from client
		//call the function to process difference types of messages
		if(message.equals("HELO text\n".trim())){
			dealWithHELO(socket);
		}
		else if(message.equals("KILL_SERVICE\n".trim())){
			dealWithKill(socket);
		}
		else{
			dealWithOtherMessages();
		}
	}

	private void dealWithOtherMessages() {
		try {
			// simulate work time
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// print out the ip, port number and the student id
	private void dealWithHELO(Socket socket) throws UnknownHostException {
		System.out.println("HELO text\nIP:" + Inet4Address.getLocalHost()+"\nPort:"
			+socket.getLocalPort()+"\nStudentID:1ee9647a04c08c2a6d5896be1df1d272d50e01606f56790c53f1d406336b1609\n");
	}
	
	// close the socket and kill the executor, ending all threads instantly
	private void dealWithKill(Socket socket) throws IOException {
		socket.close();
		ThreadPool.executor.shutdownNow();
	}

	public void run() {
	}
}