/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import doctor_chat.common.NotFoundException;
import doctor_chat.common.AuthentificationFailedException;
import doctor_chat.common.ExistingUserException;
import doctor_chat.common.Message;
import doctor_chat.common.User;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class MainServer {
    public static void main(String[] args) {
        
        try {
            Message mess = MessageService.instance().findMessagesbyId(4);
            System.out.println(mess);
            System.out.println("deletion");
            MessageService.instance().delete(mess);
            System.out.println("done");
            mess = MessageService.instance().findMessagesbyId(4);
        } catch (NotFoundException ex) {
            System.out.println("success");
        }
    }    
}
