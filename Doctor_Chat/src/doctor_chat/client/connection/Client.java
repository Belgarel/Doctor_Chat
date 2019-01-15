/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client.connection;

import doctor_chat.client.ViewController;
import doctor_chat.common.connection.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class Client {
    private static Client instance = null;
    
    private int port;
    private String address;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    /** Constructor Initiates a connection to a server.
     * @param address the server address
     * @param port the server port
     * @throws IOException 
     */
    private Client(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        this.socket = new Socket(address, port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = null; //this stream is initialized in the proper thread (see method run of ClientReceive)
        
        Thread threadClientReceive = new Thread(new ClientReceive(this, this.socket));
        threadClientReceive.start();
    }
    /** Disconnects the client from the server, closes all streams, leaves the application.
     * 
     * @throws IOException 
     */
    public void disconnectedServer() throws IOException {
        this.out.close();
        if (this.in != null)
            this.in.close();
        this.socket.close();
        
        System.exit(0); //end of the application
    }
    public void sendMessage(ClientMessage mess) {
        try {
            out.writeObject(mess);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Processes the messages from the server according to the message type
     * @param mess the server's message
     */
    public void messageReceived(ServerMessage mess) {
        if (mess instanceof AuthentificationOK)
            ViewController.instance().getBehavior().authentificationOK((AuthentificationOK) mess);
        else if (mess instanceof AuthentificationFail)
            ViewController.instance().getBehavior().authentificationFail((AuthentificationFail) mess);
        //TODO: other types
    }
    
    
    
    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
    
    /**
     * This class is a singleton with parameters. It needs to be initialized before this function accepts to work.
     * @return
     * @throws ConnectionNotInitializedException 
     */
    public static Client instance() throws ConnectionNotInitializedException {
        if (instance == null)
            throw new ConnectionNotInitializedException();
        return instance;
    }
    /**
     * This class is a singleton with parameters. This function must be used the first time you need to establish the connection to the server
     * @param address the server's address
     * @param port the server's port
     * @return
     * @throws IOException 
     */
    public static Client instance(String address, int port) throws IOException {
        if (instance == null)
        {
            synchronized(Client.class) {
                if (instance == null)
                    instance = new Client(address, port);
            }
        }
        return instance;
    }
}
