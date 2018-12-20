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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class MainServer {
    public static void main(String[] args) {
        try {
            System.out.println("USER 2 ------------------------- \n"
            + UserService.instance().findUser((long)2));
            System.out.println("CONVO 2 ------------------------ \n"
            + ConversationService.instance().toConversation((long)2));
            System.out.println("------------------\nAdding\n------------------");
            ConversationService.instance().addMemberToConversation(2, 2);
            System.out.println("USER 2 ------------------------- \n"
            + UserService.instance().findUser((long)2));
            System.out.println("CONVO 2 ------------------------ \n"
            + ConversationService.instance().toConversation((long)2));
        } catch (NotFoundException ex) {
            System.out.println("Not found");
        }
    }    
}
