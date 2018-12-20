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
    public static void main(String[] args) throws NotFoundException {
        System.out.println(
                MessageService.instance().findMessagesForConversation(
                ConversationService.instance().toConversation(1)).get(0));
        System.out.println(
                MessageService.instance().findMessagesForConversation(
                ConversationService.instance().toConversation(1)).get(0).getDateDDMMYYY());
        Message mess = new Message();
        mess.setAuthor(UserService.instance().findUser((long) 1));
        mess.setDate(new Date());
        mess.setContent("lol");
        mess.setConversation(ConversationService.instance().toConversation(2));
        mess.setFilePath("");
        MessageService.instance().createMessage(mess);
    }    
}
