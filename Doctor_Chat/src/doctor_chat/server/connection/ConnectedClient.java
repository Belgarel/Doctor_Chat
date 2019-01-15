/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server.connection;

/**
 *
 * @author Home
 */

import doctor_chat.common.AuthentificationFailedException;
import doctor_chat.common.Conversation;
import doctor_chat.common.NotFoundException;
import doctor_chat.common.User;
import doctor_chat.common.connection.AuthentificationFail;
import doctor_chat.common.connection.AuthentificationOK;
import doctor_chat.common.connection.AuthentificationRequest;
import doctor_chat.common.connection.ClientMessage;
import doctor_chat.common.connection.ServerMessage;
import doctor_chat.server.ContactService;
import doctor_chat.server.ConversationService;
import doctor_chat.server.UserService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectedClient implements Runnable {
    /**
     * counter user to generate incremented ids for the connections
     */
    private static int idCounter = 0;
    /**
     * id of the connection
     */
    private int id;
    /** 
     * socket that contains information about the client's conntection
     */
    private Socket socket;
    /**
     * stream to send objects to the client
     */
    private ObjectOutputStream out;
    /**
     * stream to read objects sent by the client
     */
    private ObjectInputStream in;
    /**
     * server to which the client is connected
     */
    private Server server;
    /**
     * data of the user account (once identified)
     */
    User account;

    public ConnectedClient(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.id = idCounter++;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = null; //this stream is initialized in the proper thread (see method run)
        this.account = null;
    }
    /**
     * sends a message to the client
     * @param mess the message to send
     * @return the message that was sent
     */
    public ServerMessage sendServerMessage(ServerMessage mess) {
        try {
            this.out.writeObject(mess);
            this.out.flush();
            return mess;
        } catch (IOException ex) {
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * gets the id of the connection
     * @return the id of the connection
     */
    public int getId() {
        return id;
    }
    
    /**
     * terminates the connection by closing properly the socket and the streams
     */
    public void closeClient() throws IOException {
        if (this.in != null)
            this.in.close();
        this.out.close();
        this.socket.close();
    }
    
    
    /**
     * Listens to messages from the client
     */
    @Override
    public void run() {
        boolean isActive = true;
        ClientMessage mess = null;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            while(isActive) {
System.out.println("Listening to client " + this.id);
                try {
                    mess = (ClientMessage) in.readObject();
                } catch (SocketException se) {
                    mess = null;
                }
                if (mess == null) {//disconnects the client
                    server.disconnectClient(this);
                    isActive = false;
                }
                else {
                    messageReceived(mess);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
System.out.println("Not listening to client " + this.id + " anymore.");
        }
        
    }
        
    void messageReceived(ClientMessage mess) {
        mess.setSender("" + id); //the proper id is set
        //Depending of the type of the message, an action is decided
        if (mess instanceof AuthentificationRequest) //authentification
            identify((AuthentificationRequest) mess);
    }
    /**
     * determines wether and authentification request is valid or invalid,
     * then sends the answer to the client.
     * @param message message received by the server
     */
    private void identify(AuthentificationRequest message) {
        try {
            account = UserService.instance().findUser(message.getLogin(), message.getPassword());
        } catch (NotFoundException ex) {
            sendServerMessage(new AuthentificationFail("Utilisateur inexistant."));
        } catch (AuthentificationFailedException ex) {
            sendServerMessage(new AuthentificationFail("Mauvais mot de passe."));
        }
        //if the authentification was successfull
        //retreive the list of contacts
        HashSet<User> contacts = UserService
                .instance().findUsersFromIds(account.getContactIds());
        //retreive the list of conversations
        HashSet<Conversation> conversation = ConversationService
                .instance().findConversationsFromIds(account.getConversationIds());
        //send it to the server
        sendServerMessage(new AuthentificationOK(account, contacts, conversation));
    }
    
    
}

