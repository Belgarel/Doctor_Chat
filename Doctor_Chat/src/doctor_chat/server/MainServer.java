/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import doctor_chat.common.NotFoundException;
import doctor_chat.common.AuthentificationFailedException;
import doctor_chat.common.ExistingUserException;
import doctor_chat.common.User;
import doctor_chat.server.connection.Connection;
import doctor_chat.server.connection.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import doctor_chat.server.ContactService;

/**
 *
 * @author Home
 */
public class MainServer {
    public static void main(String[] args) {
        try {
            Server serv = new Server(8080);
            //ContactService.instance().deleteContact(4, 5);
            
        }
        catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
