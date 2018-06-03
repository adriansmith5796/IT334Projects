

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static final int PORT = 10502;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    
    public static void main(String[] args) {
	System.out.println("Starting chat server...");
	try(ServerSocket listener = new ServerSocket(PORT)){
	    while(true) {
		Socket client = listener.accept();
		new Handler(client).start();
	    }
	} catch (IOException e) {
	    System.out.println("Couldn't start server.");
	}
    } // end main
    
    private static class Handler extends Thread{
	// instance variables
	private Socket socket;
	private String name;
	private BufferedReader in;
	private PrintWriter out;
	
	// constructor holds client socket
	public Handler(Socket client) {
	    this.socket = client;
	}
	
	@Override
	/**
	 * SUBMITNAME is sent by server to client and client should reply 
	 * with the desired screen name.
	 */
	public void run() {
	    try{
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		while(true) {
		    out.println("SUBMITNAME");
		    name = in.readLine();
		    
		    if (name == null) {
			return;
		    }
		    
		    synchronized(names) {
			if(!names.contains(name)) {
			    names.add(name);
			    break;
			}
		    }
		    
		    
		}
	
		    out.println("NAMEACCEPTED");
		    writers.add(out);
		    
		    for(PrintWriter write: writers) {
			write.println("MESSAGE: " + name + "has joined the server!");
		    }
		    
		    while(true) {
			String input = in.readLine();
			if(input == null) {
			    return;
			}
			
			for(PrintWriter print : writers) {
			    print.println("MESSAGE: " + name + ": " + input);
			}
		    }
		
	    } catch (IOException e) {
		System.out.println(e);
	    }finally {
		if(name != null) {
		    names.remove(name);
		}
		if(out != null) {
		    writers.remove(out);
		}
		
		try {
		    socket.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
} 
