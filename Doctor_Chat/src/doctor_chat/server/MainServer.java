/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import doctor_chat.common.NotFoundException;
import doctor_chat.common.AuthentificationFailedException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class MainServer {
    public static void main(String[] args) {
        try {
            UserService.instance().findUser("test", "123");
        } catch (NotFoundException|AuthentificationFailedException ex) {
            System.out.println("ici" + ex.getMessage());
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
