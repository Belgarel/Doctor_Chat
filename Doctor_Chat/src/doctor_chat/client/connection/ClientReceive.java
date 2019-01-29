/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.client.connection;

import doctor_chat.common.connection.ServerMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1514527
 */
public class ClientReceive implements Runnable {
    
    private Client client;
    private Socket socket;
    private ObjectInputStream in;

    public ClientReceive(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
        this.in = null;
    }

    @Override
    public void run() {
        try {
            this.in = new ObjectInputStream(this.socket.getInputStream());
            
            boolean isActive = true;
            while (isActive) {
                ServerMessage mess = (ServerMessage) in.readObject();
                if (mess == null) {
                    isActive = false;
                    this.client.disconnectedServer();
                }
                else
                    this.client.messageReceived(mess);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
