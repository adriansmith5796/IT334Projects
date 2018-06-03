import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class ChatClient {
    BufferedReader in;
    PrintWriter out;
    
    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
    
    /** Construct client that has GUI layout and register
     * a listener with the textfield so that pressing return in 
     * the listener sends the textfield content to the server
     * Text Field/ Area are not editable until server sends
     * NAMEACCEPTED
     */
    public ChatClient() {
	// Layout GUI
	textField.setEditable(false);
	messageArea.setEditable(false);
	frame.getContentPane().add(textField, "North"); // For text input
	JScrollPane jPane = new JScrollPane(messageArea); 
	frame.getContentPane().add(jPane, "Center");
	frame.pack(); // Puts all components together
	
	// Add listeners
	textField.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		out.println(textField.getText()); //send field text to server
		textField.setText(""); // clear field
	    }
	    
	});
    }
    
    /*
     * prompt for the servers ip address
     */
    private String getServerAddress() {
	return JOptionPane.showInputDialog(
		frame,
		"Enter IP Address of server:",
		"Welcome to the chatter!",
		JOptionPane.QUESTION_MESSAGE
		);
    }
    
    private String getName() {
	// TODO Auto-generated method stub
	return JOptionPane.showInputDialog(
		frame,
		"Choose a screen name",
		"Screen name selection",
		JOptionPane.PLAIN_MESSAGE
		);
    }
    
    private void run() {
	// Make connection and initialize streams
	String serverAddress = getServerAddress();
	try {
	    Socket socket = new Socket(serverAddress, 10502);
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    out = new PrintWriter(socket.getOutputStream(), true);
	    boolean run = true;
	    //Process all messages from the server
	    while(run) {
		String line = in.readLine();
		if(line.startsWith("SUBMITNAME")) {
		    out.println(getName());
		}else if(line.startsWith("NAMEACCEPTED")) {
		    textField.setEditable(true);
		}else if(line.startsWith("MESSAGE")) {
		    messageArea.append(line.substring(8) + "\n");
		}
	    }
	    
	    socket.close();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    

    public static void main(String[] args) {
	ChatClient client = new ChatClient();
	client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	client.frame.setVisible(true);
	client.run();
    }
}
