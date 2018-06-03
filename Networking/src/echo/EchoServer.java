package echo;

import java.net.*;
import java.io.*;

public class EchoServer {

	public static final int PORT = 10501;
	
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(PORT)){
			System.out.println("SERVER STARTED...");
			while(true) {
				// Listen for client connections
				Socket client = server.accept();
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				
				BufferedReader in = new BufferedReader(
						new InputStreamReader(client.getInputStream()));
				String inputFromClient;
				while((inputFromClient = in.readLine()) != null) {
					out.println(inputFromClient.toUpperCase());
				}
			}
		}catch(IOException e) {
			System.out.println("Couldn't make connection to the server.");
			e.printStackTrace();
		}
	}

}
