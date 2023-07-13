package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Config.ServerConfig;
import Models.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane; // import JScrollPane

public class Server{
    public ServerSocket server;
    String sentence_from_client;
    String sentence_to_client;
    int port=ServerConfig.port;
    JTextArea logArea; // a text area to display the logs
    public void run() {
        try {
            Socket socket = server.accept();
            Thread T=new Thread(()-> {
                try {
                    String sentence_from_client;
                    BufferedReader inFromClient =
                            new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    OutputStreamWriter outToClient =
                            new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                    sentence_from_client = inFromClient.readLine();

                    sentence_to_client = new UseFunction().Mapping(sentence_from_client);
                    
                    
                    outToClient.write(sentence_to_client);
                    outToClient.flush();
                    socket.close();
                    logArea.append("Received from client: " + sentence_from_client + "\n"); // append the message from client to the log area
                    logArea.append("Sent to client: " + sentence_to_client + "\n"); // append the message to client to the log area
                }
                catch (Exception e) {
                    e.printStackTrace();
                    logArea.append("Error: " + e.getMessage() + "\n"); // append the error message to the log area
                }

            });
            T.start();
        }
        catch (Exception e) {
            e.printStackTrace();
            logArea.append("Error: " + e.getMessage() + "\n"); // append the error message to the log area
        }
    }
    public Server() {
        try {
            server = new ServerSocket(port);
            logArea = new JTextArea(); // create a new text area
            logArea.setEditable(false); // make it non-editable
            JScrollPane scrollPane = new JScrollPane(logArea); // create a new scroll pane with the text area
            JFrame frame = new JFrame("Server Logs"); // create a new frame with title
            frame.add(scrollPane); // add the scroll pane to the frame
            frame.setSize(500, 500); // set the size of the frame
            frame.setVisible(true); // make the frame visible
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the default close operation
            logArea.append("Server is started\n"); // append the start message to the log area
            while (true) {
                run();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            logArea.append("Error: " + e.getMessage() + "\n"); // append the error message to the log area
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
