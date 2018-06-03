package echo;
import java.net.*;
import java.util.*;
import java.io.*;

public class EchoClient {
	private static final int PORT = 10501;
	private static final String DOMAIN = "localhost";
	
	public static void main(String[] args) {
		// create cnxn to server using IP address and port of server application
		try(Socket socket = new Socket(InetAddress.getByName(DOMAIN), PORT);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				Scanner console = new Scanner(System.in);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				) {
			String userInput;
			System.out.println(">>> ");
			while(!(userInput = console.nextLine()).equals(".")) {
				out.println(userInput);
				
				System.out.println(in.readLine());
				
				System.out.println(">>>");
			}
			
		}catch(UnknownHostException e) {
			System.out.println("Unknown host name");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("Couldn't make connection to server");
			e.printStackTrace();
		}
	}

}
