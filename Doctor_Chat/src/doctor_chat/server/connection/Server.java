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

import doctor_chat.common.User;
import doctor_chat.common.connection.ServerMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
public class Server {
    /**
     * list of the current (active) connections to a client.
     */
    private List<ConnectedClient> clients;
    /**
     * port the server is listening.
     */
    private int port;
    
    /**
     * constructor for Server
     * @param port port the Server is listening
     */
    public Server(int port) throws IOException {
        this.port = port;
        this.clients = new ArrayList<ConnectedClient>();
        
        Thread threadConnections = new Thread(new Connection(this));
        threadConnections.start();
    }
    
    /**
     * adds a client to the list of active connections
     * @param newClient the client to be added
     * @return the client that was added
     */
    public ConnectedClient addClient(ConnectedClient newClient) {
        this.clients.add(newClient);
System.out.println("Le client " + newClient.getId() + " vient de se connecter.");
        return newClient;
    }
    /**
     * removes a client from the list of active connections
     * @param newClient the client to be disconnected
     * @return the client that was disconnected
     * @return null if the client was not found
     */
    public ConnectedClient disconnectClient(ConnectedClient discClient) throws IOException {
        this.clients.remove(discClient);
        discClient.closeClient();
System.out.println("Le client " + discClient.getId() + " vient de se déconnecter.");
        return discClient;
    }
    
    public void sendMessageToClients(ServerMessage mess, HashSet<User> users) {
        users = (HashSet<User>) users.clone();
        synchronized (clients) { //coûteux, mais évite que le message soit envoyé à un client qui n'existe plus !
            for (User user : users) {
                Iterator<ConnectedClient> iterator = clients.iterator();
                boolean clientFound = false;
                ConnectedClient client = null;
                while (!clientFound && iterator.hasNext()) {
                    client = iterator.next();
                    clientFound = client.getAccount().getNum() == user.getNum();
                }
                if (clientFound)
                    client.sendServerMessage(mess);
                else
                    System.out.println("Error: connection.Server > sendMessageToClients" +
                            "Client was not found for user " + client.getAccount().getNum()
                            + " - " + client.getAccount().getLogin());
            }
        }
    }
    

    /**
     * getter for the port of the server.
     * @return the port the server is listening
     */
    public int getPort() {
        return port;
    }
    
    
}

