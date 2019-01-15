/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server.connection;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * listens until a new connection is required, then creates the connection
 * @author Home
 */
public class Connection implements Runnable {
    /**
     * the server that created this connection
     */
    private Server server;
    /**
     * the server socket that waits for connections
     */
    private ServerSocket serverSocket;

    /**
     * constructor
     * @param server the server to which the clients will be connected
     * @throws IOException 
     */
    public Connection(Server server) throws IOException {
        this.server = server;
        this.serverSocket = new ServerSocket(server.getPort());
    }
    
    public void run() {
        while(true) {
            try {
System.out.println("Waiting for new clients...");
                Socket sockNewClient = serverSocket.accept();
                ConnectedClient newClient = new ConnectedClient(this.server, sockNewClient);
                this.server.addClient(newClient);
                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

